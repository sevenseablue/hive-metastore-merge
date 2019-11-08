package com.q.hivetools.apps;

import com.q.hivetools.mappers.MetaDataMapper;
import com.q.hivetools.meta.Dbs;
import com.q.hivetools.meta.Roles;
import com.q.hivetools.meta.Types;
import com.q.hivetools.meta.Version;
import com.q.hivetools.service.MyBatisUtil;
import com.q.hivetools.service.ReflectUtils;
import com.q.hivetools.service.SchemaUtils;
import org.apache.commons.cli.*;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.io.Resources;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by hzliuxun on 16/10/26.
 */
public class MetaDataMergeTbls {
    private static final Logger logger = Logger.getLogger(MetaDataMergeTbls.class.getName());

    public static void main(String[] args) throws IOException {
        try {
            PropertyConfigurator.configure(Resources.getResourceAsStream("log4j.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        cliCommond(args);

//		MyBatisUtil.sourceName = "hive_haitao";
//		MyBatisUtil.destName = "hive_merge";

        logger.info("=====================================================");
        System.out.println("第一步:备份所有数据库");
        System.out.println("第二步:清空数据库中的 exchange_db");
        System.out.println("第三步:使用用户提供的元数据 Mysql IP 和用户名密码，将数据库通过 mysqldump 出数据库(申请RDS权限)");
        System.out.println("第四步:将 用户的 元数据导入 exchange_db");
        System.out.println("第五步:检查是否存着和 DEST 数据库存着数据库重名,联系业务方将重名的数据库该名");
        System.out.println("第六步:在 exchange_db 中删除多余的 DB");
        System.out.println("修改脚本中的 DEL_DB（多个库之间用逗号分割,default必须删除）参数和 DEL_TBL（为空则删除所有表）");
        System.out.println("./delMetaData.sh");
        System.out.println("第七步:重新执行元数据合并脚本");
        System.out.println("第八步:检查合并日志，通过hive进行测试是否导入成功");
        logger.info("-----------------------------------------------------");
        logger.info("将元数据 " + MyBatisUtil.sourceName + " 合并到 " + MyBatisUtil.destName);
        logger.info("=====================================================");

        MetaDataMapper sourceMetaData = new MetaDataMapper(MyBatisUtil.sourceName);
        MetaDataMapper destMetaData = new MetaDataMapper(MyBatisUtil.destName);

        MetaDataMapper staticOnlineMetaData = destMetaData; // new MetaDataMapper(MyBatisUtil.onlneName);

        List<String> tables = new ArrayList<String>();
        HashMap<String, Object> mapPlusId = new HashMap<String, Object>();
        HashMap<String, Object> pagingProc = new HashMap<String, Object>();

        File selFile = Resources.getResourceAsFile("SELECTTABLESMan.txt");
        File insFile = Resources.getResourceAsFile("INSERTMAN.txt");
        File tabColFile = Resources.getResourceAsFile("SELECTTABLESCols.txt");
        List<String> selTables = FileUtils.readLines(selFile).stream().map(x->x.split(":")[0]).collect(Collectors.toList());
        List<String> insTables = FileUtils.readLines(insFile).stream().map(x->x.split(":")[0]).collect(Collectors.toList());
        List<String> tabCols = FileUtils.readLines(tabColFile).stream().collect(Collectors.toList());

		/* not merge
		tables.add("ROLES");
		tables.add("ROLE_MAP");
		tables.add("GLOBAL_PRIVS");
		tables.add("DELEGATION_TOKENS");
		tables.add("SEQUENCE_TABLE");
		tables.add("VERSION");
		*/

        pagingProc.put("PARTITIONS", "");
        pagingProc.put("PARTITION_PARAMS", "");
        pagingProc.put("PARTITION_KEY_VALS", "");
        pagingProc.put("SDS", "");
        pagingProc.put("SERDES", "");
        pagingProc.put("SERDE_PARAMS", "");

        boolean conflict = checkConflict();
//		checkHdfsCluster();

        Scanner sc = new Scanner(System.in);
        String useInput = "";
        while (!useInput.equals("Y")) {
            System.out.println("请先备份数据库!");
            System.out.print("将元数据 " + MyBatisUtil.sourceName + " 合并到 " + MyBatisUtil.destName + " 操作请输入[Y/n] : ");

            useInput = sc.nextLine();
            if (useInput.equalsIgnoreCase("n")) {
                System.exit(1);
            }
        }

        boolean mergeSuccess = true;
        final int offset = 10000;
        for (String tabName : insTables) {
            logger.info("计算表" + tabName + "的maxId");
            int destMaxDestId = staticOnlineMetaData.getTableMaxId(tabName) + offset;
            logger.info("表" + tabName + "的maxId=" + destMaxDestId);
            mapPlusId.put(tabName, destMaxDestId);
        }

        String checkInput = "";
        while (!checkInput.equals("Y")) {
            for (String tabName : insTables) {
                System.out.println("元数据表" + tabName + "偏移量为" + mapPlusId.get(tabName));
            }
            System.out.println("请确认数据库的偏移量");
            System.out.print("将元数据 " + MyBatisUtil.sourceName + " 合并到 " + MyBatisUtil.destName + " 操作请输入[Y/n] : ");
            checkInput = sc.nextLine();
            if (checkInput.equalsIgnoreCase("n")) {
                System.exit(1);
            }
        }

        mapPlusId.put("sourceName", MyBatisUtil.sourceName);
        mapPlusId.put("destName", MyBatisUtil.destName);
        Map<String, String> tab2Col = new HashMap<>();
        tabCols.stream().map(x->x.split(":")).forEach(x->{
            tab2Col.put(x[0], x[1]);
        });
        Map<String, Object> params = new HashMap<>();
        params.put("dbname", MyBatisUtil.db);
        params.put("TbnameList", Arrays.asList(MyBatisUtil.tb.split(",")));
        String pkgName = "com.q.hivetools.meta.";
        for (String tabName: selTables){
            List<Object> listRecords = (List) sourceMetaData.getTableRecordsCondition(tabName, params);
            String tabNameCls = SchemaUtils.formatTableColumnName(tabName, true);
            System.out.println(tabName+"\t"+listRecords.size());
            if(tab2Col.containsKey(tabName)) {
                try {
                    listRecords.add(ReflectUtils.reflectSetInit(pkgName + tabNameCls, tab2Col.get(tabName).toLowerCase()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
            params.put(tabNameCls+"List", listRecords);
        }

        for(String tabName: insTables){
            String tabNameCls = SchemaUtils.formatTableColumnName(tabName, true);
            List<Object> listRecords = (List)params.get(tabNameCls+"List");
            if(tab2Col.containsKey(tabName)) {
                listRecords.remove(listRecords.size()-1);
            }
            logger.info("从[数据源:" + MyBatisUtil.sourceName + "].[表:" + tabName + "] 中, 获取到 " + listRecords.size() + " 条记录.");
            int numResult = destMetaData.batchInsert(tabName, listRecords, mapPlusId);
            if (numResult < 0) {
                mergeSuccess = false;
                break;
            }
            logger.info("插入到[数据源:" + MyBatisUtil.destName + "].[表:" + tabName + "] 中 " + numResult + " 条记录.");
        }

        if (true == mergeSuccess) {
            logger.info("更新 SEQUENCE_TABLE 中的流水号.");
            mergeSuccess = destMetaData.updateSequenceTable();
        }
        if (true == mergeSuccess) {
            logger.info("合并 " + MyBatisUtil.sourceName + " 到 " + MyBatisUtil.destName + " 成功! [√]");
//		logger.info("[数据源:" + MyBatisUtil.destName + "].[表:DBS]中数据库名称全部进行了自动调整,请根据需要手工修改.");
//		logger.info("SELECT * FROM DBS WHERE NAME LIKE '" + MyBatisUtil.sourceName + "%'");
//		logger.info("UPDATE DBS SET NAME = ? WHERE DB_ID = ?");
            logger.info("============= " + MyBatisUtil.sourceName + " 表ID跳号信息 =============");
            logger.info(mapPlusId);
            logger.info("========================================================");
        } else {
            logger.error("合并 " + MyBatisUtil.sourceName + " 到 " + MyBatisUtil.destName + " 失败! [×]");
            logger.error("============= " + MyBatisUtil.sourceName + " 表ID跳号信息 =============");
            logger.error(mapPlusId);
            logger.error("========================================================");
        }

    }

    static private void cliCommond(String[] args) {
        Options opt = new Options();
        opt.addOption("c", "check", false, "检查二个元数据库是否存在数据冲突");
        opt.addOption("h", "help", false, "打印命令行帮助");
        opt.addOption(OptionBuilder.withLongOpt("p")
                .withDescription("处理函数名称")
                .withValueSeparator('=')
                .hasArg()
                .create());
        opt.addOption(OptionBuilder.withLongOpt("s")
                .withDescription("迁出的元数据库")
                .withValueSeparator('=')
                .hasArg()
                .create());
        opt.addOption(OptionBuilder.withLongOpt("d")
                .withDescription("迁入的元数据库")
                .withValueSeparator('=')
                .hasArg()
                .create());
        opt.addOption(OptionBuilder.withLongOpt("db")
                .withDescription("迁出的数据库")
                .withValueSeparator('=')
                .hasArg()
                .create());
        opt.addOption(OptionBuilder.withLongOpt("tb")
                .withDescription("迁出的表名,分割")
                .withValueSeparator('=')
                .hasArg()
                .create());
        opt.addOption(OptionBuilder.withLongOpt("o")
                .withDescription("线上的元数据静态库")
                .withValueSeparator('=')
                .hasArg()
                .create());

        String formatstr = "MetaDataMerga --s=<arg> --d=<arg> --db=<args> --tb=<args> --o=<arg> [-h/--help]";

        HelpFormatter formatter = new HelpFormatter();
        CommandLineParser parser = new PosixParser();
        CommandLine cl = null;
        try {
            // 处理Options和参数
            cl = parser.parse(opt, args);
        } catch (ParseException e) {
            formatter.printHelp(formatstr, opt); // 如果发生异常，则打印出帮助信息
        }
        // 如果包含有-h或--help，则打印出帮助信息
        if (cl.hasOption("h")) {
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp(formatstr, "", opt, "");
            System.exit(1);
        }
        if (cl.hasOption("s")) {
            MyBatisUtil.sourceName = cl.getOptionValue("s");
        } else {
            System.out.println("missing --s arg");
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp(formatstr, "", opt, "");
            System.exit(1);
        }
        if (cl.hasOption("d")) {
            MyBatisUtil.destName = cl.getOptionValue("d");
        } else {
            System.out.println("missing --d arg");
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp(formatstr, "", opt, "");
            System.exit(1);
        }
        if (cl.hasOption("db")) {
            MyBatisUtil.db = cl.getOptionValue("db");
        } else {
            System.out.println("missing --db arg");
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp(formatstr, "", opt, "");
            System.exit(1);
        }
        if (cl.hasOption("tb")) {
            MyBatisUtil.tb = cl.getOptionValue("tb");
        } else {
            System.out.println("missing --tb arg");
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp(formatstr, "", opt, "");
            System.exit(1);
        }

    }



    static private boolean checkConflict() {
        MetaDataMapper sourceMetaData = new MetaDataMapper(MyBatisUtil.sourceName);
        MetaDataMapper destMetaData = new MetaDataMapper(MyBatisUtil.destName);

        boolean conflict = false;

        List<String> dbs = new ArrayList<String>();
        dbs.add("VERSION");
        dbs.add("DBS");
//        dbs.add("TYPES");
		/*
		dbs.add("ROLES");
		*/

        for (String dbName : dbs) {
            logger.info(">>> 检查 [数据源:" + MyBatisUtil.sourceName + "].[数据库:" + dbName + "] 和 [数据源:" + MyBatisUtil.destName + "].[数据库:" + dbName + "] 数据是否存在冲突?");

            List<Object> listRecords = (List) sourceMetaData.getTableRecordsCondition(dbName, null);
            System.out.println("#########"+listRecords);

            List<Object> listUniqueKey = destMetaData.checkUniqueKey(dbName, listRecords);
            if (listUniqueKey.size() > 0) {
                conflict = true;
                for (Object object : listUniqueKey) {
                    if (object instanceof Dbs) {
                        logger.error("数据库名 " + ((Dbs) object).getName() + " 在2个数据源中同时存在! 存在数据冲突!!! [×]");
                    } else if (object instanceof Version) {
                        logger.error("2个数据源的元数据版本号 " + ((Version) object).getSchemaVersion() + " 不一致! [×]");
                    } else if (object instanceof Roles) {
                        logger.error(((Roles) object).getRoleName() + " 冲突!!! [×]");
                    } else if (object instanceof Types) {
                        logger.error(((Types) object).getTypeName() + " 冲突!!! [×]");
                    } else {
                        logger.error(object + " 冲突!!! [×]");
                    }
                }
            } else {
                logger.info("<<< [数据源:" + MyBatisUtil.sourceName + "].[数据库:" + dbName + "] 和 [数据源:" + MyBatisUtil.destName + "].[数据库:" + dbName + "] 不存在冲突. [√]");
            }
        }

        if (conflict == true) {
            logger.error("检查完毕 [数据源:" + MyBatisUtil.sourceName + "] 和 [数据源:" + MyBatisUtil.destName + "] 存在数据冲突! 程序退出!! [×]");
            System.exit(1);
        } else {
            logger.info("检查完毕 [数据源:" + MyBatisUtil.sourceName + "] 和 [数据源:" + MyBatisUtil.destName + "] 没有数据冲突. [√]");
        }

        return conflict;
    }

}
