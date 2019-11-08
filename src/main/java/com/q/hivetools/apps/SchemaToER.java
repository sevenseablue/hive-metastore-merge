package com.q.hivetools.apps;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLColumnDefinition;
import com.alibaba.druid.sql.ast.statement.SQLTableElement;
import com.alibaba.druid.sql.dialect.mysql.ast.MysqlForeignKey;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlCreateTableStatement;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.util.JdbcConstants;
import com.q.hivetools.service.SchemaUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * Created by hzliuxun on 16/10/20.
 */
public class SchemaToER {
    private static final Logger logger = Logger.getLogger(SchemaToER.class.getName());

    public Map<String, Set<String>> reference = new HashMap<>();
    public Map<String, Set<String>> referencedBy = new HashMap<>();

    public static void main(String[] args) throws IOException {
        String fileContext = SchemaUtils.readSchemaFile(System.getProperty("user.dir") + "/src/main/resources/hive-schema-2.3.0.mysql.sql");
        List<String> createStatement = new ArrayList<>();
        String regex = "CREATE TABLE.*?ENGINE=InnoDB DEFAULT CHARSET=latin1;";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fileContext);
        SchemaToER er = new SchemaToER();
        List<String> tbs = new LinkedList<>();
        while (matcher.find()) {
            String statement = matcher.group();
            createStatement.add(statement);
            String res = er.schemaToER(statement);
//            String res = er.schemaToT2T(statement);
            tbs.add(res);
        }
        System.out.println(String.join("\n",tbs));
        FileUtils.writeLines(new File("/home/wangdawei/github/hive/hive-metastore-merge/src/main/resources/TBLS_FK.txt"), tbs);
//        FileUtils.writeLines(new File("/home/wangdawei/github/hive/hive-metastore-merge/src/main/resources/TBLS_FK_TBLS.txt"), tbs);
    }


    public String schemaToER(String statement) {
        List<String> cols = new LinkedList<>();
        List<String> fks = new LinkedList<>();
        String tableName = null;
        String tableNameClass = null;
        try {
            String result = SQLUtils.format(statement, JdbcConstants.MYSQL);
            logger.info(result);
            List<SQLStatement> stmtList = SQLUtils.parseStatements(statement, JdbcConstants.MYSQL);

            for (int i = 0; i < stmtList.size(); i++) {
                SQLStatement sqlStatement = stmtList.get(i);
                MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
                sqlStatement.accept(visitor);
                MySqlCreateTableStatement mySqlCreateTableStatement = (MySqlCreateTableStatement) sqlStatement;

                tableName = mySqlCreateTableStatement.getTableSource().toString().toUpperCase().replaceAll("`", "");
                tableNameClass = SchemaUtils.formatTableColumnName(tableName, true);
                ArrayList<SQLTableElement> tableElementList = (ArrayList<SQLTableElement>) mySqlCreateTableStatement.getTableElementList();
                for (SQLTableElement element : tableElementList) {
                    if ((element instanceof SQLColumnDefinition)) {
                        SQLColumnDefinition sqlColumnDefinition = (SQLColumnDefinition) element;
                        cols.add(sqlColumnDefinition.getNameAsString().replaceAll("`", ""));
                    }
                    if ((element instanceof MysqlForeignKey)) {
                        MysqlForeignKey foreignKey = ((MysqlForeignKey) element);
                        String str1 = String.join(" ",foreignKey.getReferencingColumns().stream().map(x->x.getSimpleName()).collect(Collectors.toList())).replaceAll("`", "");
                        String str2 = foreignKey.getReferencedTableName().getSimpleName().replaceAll("`", "");
                        str2 = SchemaUtils.formatTableColumnName(str2, true);
                        String str3 = String.join(" ",foreignKey.getReferencedColumns().stream().map(x->x.getSimpleName()).collect(Collectors.toList())).replaceAll("`", "");
                        fks.add(String.join(",", Arrays.asList(str1, str2, str3)));
                    }
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return String.join(";", Arrays.asList(tableName, tableNameClass, "", String.join(":", fks)));
    }

    public String schemaToT2T(String statement) {
        List<String> cols = new LinkedList<>();
        Set<String> fks = new LinkedHashSet<>();
        String tableName = null;
        String tableNameClass = null;
        try {
            String result = SQLUtils.format(statement, JdbcConstants.MYSQL);
            logger.info(result);
            List<SQLStatement> stmtList = SQLUtils.parseStatements(statement, JdbcConstants.MYSQL);

            for (int i = 0; i < stmtList.size(); i++) {
                SQLStatement sqlStatement = stmtList.get(i);
                MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
                sqlStatement.accept(visitor);
                MySqlCreateTableStatement mySqlCreateTableStatement = (MySqlCreateTableStatement) sqlStatement;

                tableName = mySqlCreateTableStatement.getTableSource().toString().toUpperCase().replaceAll("`", "");
                tableNameClass = SchemaUtils.formatTableColumnName(tableName, true);
                ArrayList<SQLTableElement> tableElementList = (ArrayList<SQLTableElement>) mySqlCreateTableStatement.getTableElementList();
                for (SQLTableElement element : tableElementList) {
                    if ((element instanceof SQLColumnDefinition)) {
                        SQLColumnDefinition sqlColumnDefinition = (SQLColumnDefinition) element;
                        cols.add(sqlColumnDefinition.getNameAsString().replaceAll("`", ""));
                    }
                    if ((element instanceof MysqlForeignKey)) {
                        MysqlForeignKey foreignKey = ((MysqlForeignKey) element);
                        String str1 = String.join(" ",foreignKey.getReferencingColumns().stream().map(x->x.getSimpleName()).collect(Collectors.toList())).replaceAll("`", "");
                        String str2 = foreignKey.getReferencedTableName().getSimpleName().replaceAll("`", "");
//                        str2 = SchemaUtils.formatTableColumnName(str2, true);
                        String str3 = String.join(" ",foreignKey.getReferencedColumns().stream().map(x->x.getSimpleName()).collect(Collectors.toList())).replaceAll("`", "");
                        fks.add(str2);
                    }
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return String.join(":", Arrays.asList(tableName, String.join(",", fks)));
    }

}
