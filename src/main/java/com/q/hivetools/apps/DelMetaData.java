package com.q.hivetools.apps;

import com.q.hivetools.mappers.MetaDataMapper;
import com.q.hivetools.meta.Dbs;
import com.q.hivetools.meta.Tbls;
import com.q.hivetools.service.MyBatisUtil;
import org.apache.commons.cli.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.util.*;

/**
 * Created by hzliuxun on 17/1/22.
 */
public class DelMetaData {
    private static final Logger logger = Logger.getLogger(MetaDataMerge.class.getName());

    private static String del_database_name = "";
    private static String del_table_name = "";
    private static Set<String> del_tbname_set = new HashSet<>();

    public static void main(String[] args) {
        try {
            PropertyConfigurator.configure(Resources.getResourceAsStream("log4j.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        cliCommond(args);

        Scanner sc = new Scanner(System.in);
        String useInput = "";
        while (!useInput.equals("Y")) {
            System.err.println("请先备份数据库!");
            System.err.println("删除数据源 " + MyBatisUtil.sourceName + " 中的数据库 " + del_database_name + ", 表 " + del_table_name + " 请输入[Y/n] : ");

            useInput = sc.nextLine();
            if (useInput.equalsIgnoreCase("n")) {
                System.exit(1);
            }
        }

        String[] delDbNames = del_database_name.split(",");
        for (String delDbName : delDbNames) {
            deleteMetaDataSess(delDbName);
        }
    }

    static void deleteMetaData(String delDbName) {
        System.out.println("==> deleteMetaData(" + delDbName + ")");
        MetaDataMapper sourceMetaData = new MetaDataMapper(MyBatisUtil.sourceName);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("database_name", delDbName);
        List<Object> dbs = (List) sourceMetaData.getTableRecords("DBS", params);
        for (Object object : dbs) {
            params.put("db_id", ((Dbs) object).getDbId().toString());
            List<Object> tables = (List) sourceMetaData.getTableRecords("TBLS", params);
            for (Object table : tables) {
                if (del_table_name.isEmpty()) {
                    // delete all table
                    System.out.println("删除表名 = " + ((Tbls) table).getTblName() + ", ID = " + +((Tbls) table).getTblId());
                    sourceMetaData.deleteTable((Tbls) table);
                } else if (del_table_name.equalsIgnoreCase(((Tbls) table).getTblName())) {
                    // delete match table
                    System.out.println("删除表名 = " + ((Tbls) table).getTblName() + ", ID = " + +((Tbls) table).getTblId());
                    sourceMetaData.deleteTable((Tbls) table);
                    break;
                }
            }
            sourceMetaData.deleteDatabase((Dbs) object);
        }
        System.out.println("<== deleteMetaData(" + delDbName + ")");
    }

    static void deleteMetaDataSess(String delDbName) {
        System.out.println("==> deleteMetaData(" + delDbName + ")");
        MetaDataMapper sourceMetaData = new MetaDataMapper(MyBatisUtil.sourceName);


        Map<String, Object> params = new HashMap<String, Object>();
        params.put("database_name", delDbName);
        List<Object> dbs = (List) sourceMetaData.getTableRecords("DBS", params);

        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory(MyBatisUtil.sourceName).openSession();
        try {
            for (Object object : dbs) {
                params.put("db_id", ((Dbs) object).getDbId().toString());
                List<Object> tables = (List) sourceMetaData.getTableRecordsSess("TBLS", params, sqlSession);
                for (Object table : tables) {
                    if (del_table_name.isEmpty()) {
                        // delete all table
                        System.out.println("删除表名 = " + ((Tbls) table).getTblName() + ", ID = " + +((Tbls) table).getTblId());
                        sourceMetaData.deleteTableSess((Tbls) table, sqlSession);
                    } else if (del_tbname_set.contains(((Tbls) table).getTblName().toLowerCase())) {
                        // delete match table
                        System.out.println("删除表名 = " + ((Tbls) table).getTblName() + ", ID = " + +((Tbls) table).getTblId());
                        sourceMetaData.deleteTableSess((Tbls) table, sqlSession);
                    }
                }
                sourceMetaData.deleteDatabaseSess((Dbs) object, sqlSession);
            }
            System.out.println("<== deleteMetaData(" + delDbName + ")");
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            logger.error(e.getMessage());
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    static private void cliCommond(String[] args) {
        Options opt = new Options();
        opt.addOption("h", "help", false, "打印命令行帮助");
        opt.addOption(OptionBuilder.withLongOpt("p")
                .withDescription("处理函数名称")
                .withValueSeparator('=')
                .hasArg()
                .create());
        opt.addOption(OptionBuilder.withLongOpt("s")
                .withDescription("元数据库")
                .withValueSeparator('=')
                .hasArg()
                .create());
        opt.addOption(OptionBuilder.withLongOpt("d")
                .withDescription("数据库名")
                .withValueSeparator('=')
                .hasArg()
                .create());
        opt.addOption(OptionBuilder.withLongOpt("t")
                .withDescription("表名")
                .withValueSeparator('=')
                .hasArg()
                .create());

        String formatstr = "DelMetaData --s=<arg> --d=<arg> --t=<arg> [-h/--help]";

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
            String tempDb = cl.getOptionValue("s");
            if (!tempDb.equalsIgnoreCase("exchange_db")) {
//        System.out.println("错误! 待删除的数据源名称不是 exchange_db ??");
//        System.exit(1);
            }
            MyBatisUtil.sourceName = tempDb;
        } else {
            System.out.println("missing --s arg");
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp(formatstr, "", opt, "");
            System.exit(1);
        }

        if (cl.hasOption("d")) {
            del_database_name = cl.getOptionValue("d");
        } else {
            System.out.println("missing --d arg");
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp(formatstr, "", opt, "");
            System.exit(1);
        }

        if (cl.hasOption("t")) {
            del_table_name = cl.getOptionValue("t");
            del_tbname_set.addAll(Arrays.asList(del_table_name.toLowerCase().split(",")));
        } else {
            System.out.println("missing --t arg");
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp(formatstr, "", opt, "");
            System.exit(1);
        }

        logger.debug("MyBatisUtil.sourceName : " + MyBatisUtil.sourceName + ", del_database_name: " + del_database_name + ", del_table_name: " + del_table_name + ", del_tbname_size: " + del_tbname_set.size());
    }
}
