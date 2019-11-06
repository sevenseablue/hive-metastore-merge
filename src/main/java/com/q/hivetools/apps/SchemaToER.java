package com.q.hivetools.apps;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLTableElement;
import com.alibaba.druid.sql.dialect.mysql.ast.MysqlForeignKey;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlCreateTableStatement;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.util.JdbcConstants;
import com.q.hivetools.service.SchemaUtils;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by hzliuxun on 16/10/20.
 */
public class SchemaToER {
    private static final Logger logger = Logger.getLogger(SchemaToER.class.getName());

    public Map<String, Set<String>> reference = new HashMap<>();
    public Map<String, Set<String>> referencedBy = new HashMap<>();

    public static void main(String[] args) {
        String fileContext = SchemaUtils.readSchemaFile(System.getProperty("user.dir") + "/src/main/resources/hive-schema-2.3.0.mysql.sql");
        List<String> createStatement = new ArrayList<>();
        String regex = "CREATE TABLE.*?ENGINE=InnoDB DEFAULT CHARSET=latin1;";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fileContext);
        Map<String, String> tbFk = new HashMap<>();
        SchemaToER er = new SchemaToER();
        while (matcher.find()) {
            String statement = matcher.group();
            createStatement.add(statement);
            er.schemaToER(statement);
        }
    }


    public void schemaToER(String statement) {
        try {
            String result = SQLUtils.format(statement, JdbcConstants.MYSQL);
            logger.info(result);
            List<SQLStatement> stmtList = SQLUtils.parseStatements(statement, JdbcConstants.MYSQL);

            for (int i = 0; i < stmtList.size(); i++) {
                SQLStatement sqlStatement = stmtList.get(i);

                MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
                sqlStatement.accept(visitor);

                MySqlCreateTableStatement mySqlCreateTableStatement = (MySqlCreateTableStatement) sqlStatement;

                String tableName = mySqlCreateTableStatement.getTableSource().toString().toUpperCase().replaceAll("`", "");
                tableName = SchemaUtils.formatTableColumnName(tableName, true);

                ArrayList<SQLTableElement> tableElementList = (ArrayList<SQLTableElement>) mySqlCreateTableStatement.getTableElementList();
                for (SQLTableElement element : tableElementList) {
//					System.out.println(element.getClass().getName());
                    if (!(element instanceof MysqlForeignKey)) {
                        continue;
                    }
                    MysqlForeignKey foreignKey = ((MysqlForeignKey) element);
                    String referecedTable = foreignKey.getReferencedTableName().getSimpleName();
//                    String referecedCol = foreignKey.getReferencedColumns();
//                    System.out.println( refereced+ "\t" + foreignKey.getReferencedColumns());


                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
