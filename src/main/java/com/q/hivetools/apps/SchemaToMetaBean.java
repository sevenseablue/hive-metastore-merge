package com.q.hivetools.apps;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLDataType;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLColumnDefinition;
import com.alibaba.druid.sql.ast.statement.SQLTableElement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlCreateTableStatement;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.util.JdbcConstants;
import com.q.hivetools.service.SchemaUtils;
import com.sun.codemodel.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hzliuxun on 16/10/20.
 */
public class SchemaToMetaBean {
    private static final Logger logger = Logger.getLogger(SchemaToMetaBean.class.getName());

    public static void main(String[] args) {

        String fileContext = SchemaUtils.readSchemaFile(System.getProperty("user.dir") + "/src/main/resources/hive-schema-2.3.0.mysql.sql");

        List<String> createStatement = new ArrayList<>();

        String regex = "CREATE TABLE(\\d+)/(\\d+)ENGINE=InnoDB DEFAULT CHARSET=latin1;";

        regex = "CREATE TABLE.*?ENGINE=InnoDB DEFAULT CHARSET=latin1;";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fileContext);
        while (matcher.find()) {
            String statement = matcher.group();
            createStatement.add(statement);
            schemaToJavaBean(statement);
        }
    }


    static public void schemaToJavaBean(String statement) {
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

                JCodeModel jCodeModel = new JCodeModel();
                File destDir = new File(System.getProperty("user.dir") + "/src/main/java/");
                JPackage jPackage = jCodeModel._package("com.q.hivetools.meta");
                JDefinedClass jDefinedClass = jPackage._class(JMod.PUBLIC, tableName, ClassType.CLASS);

                // init 方法
                JMethod initMethod = jDefinedClass.method(JMod.PUBLIC, jCodeModel.VOID, tableName);

                ArrayList<SQLTableElement> tableElementList = (ArrayList<SQLTableElement>) mySqlCreateTableStatement.getTableElementList();
                for (SQLTableElement element : tableElementList) {
                    if (!(element instanceof SQLColumnDefinition)) {
                        continue;
                    }

                    String eleName = ((SQLColumnDefinition) element).getName().toString().replaceAll("`", "").toLowerCase();
                    String colName = SchemaUtils.formatTableColumnName(eleName, false);
                    String ColName = SchemaUtils.formatTableColumnName(eleName, true);
                    SQLDataType colDataType = ((SQLColumnDefinition) element).getDataType();

                    Class dataTypeClass = null;
                    String typeNameLower = colDataType.getName().toLowerCase();
                    if (true == typeNameLower.equals("string")
                            || true == typeNameLower.equals("varchar")
                            || true == typeNameLower.equals("mediumtext")
                            || true == typeNameLower.equals("longtext")
                            || true == typeNameLower.equals("text")
                            || true == typeNameLower.equals("char")) {
//						jPrimitiveType = jCodeModel.BYTE;;//new JPrimitiveType(jCodeModel, "string", String.class);
                        dataTypeClass = String.class;
                    } else if (true == typeNameLower.equals("blob")) {
                        dataTypeClass = Blob.class;
                    } else if (true == typeNameLower.equals("int") || true == typeNameLower.equals("integer")) {
                        dataTypeClass = Long.class;
                    } else if (true == typeNameLower.equals("tinyint")
                            || true == typeNameLower.equals("mediumint")
                            || true == typeNameLower.equals("smallint")) {
                        dataTypeClass = Integer.class;
                    } else if (true == typeNameLower.equals("bit")) {
                        dataTypeClass = Boolean.class;
                    } else if (true == typeNameLower.equals("bigint")) {
                        dataTypeClass = Long.class;
                    } else if (true == typeNameLower.equals("float")) {
                        dataTypeClass = Float.class;
                    } else if (true == typeNameLower.equals("double")) {
                        dataTypeClass = Double.class;
                    } else if (true == typeNameLower.equals("mediumtext")) {
                        dataTypeClass = String.class;
                    } else {
                        System.out.println(typeNameLower);
                        System.out.println("unknown data type : " + colDataType.toString());
                        continue;
                    }

                    // 字段定义
                    JFieldVar jFieldVar = jDefinedClass.field(JMod.PRIVATE, dataTypeClass, eleName);

                    // set方法
                    JMethod setMethod = jDefinedClass.method(JMod.PUBLIC, jCodeModel.VOID, "set" + ColName);
                    setMethod.param(dataTypeClass, colName + "_");
                    JBlock setBlock = setMethod.body();
                    JFieldRef setFieldRef = JExpr.ref(colName + "_");
                    setBlock.assign(jFieldVar, setFieldRef);

                    // get方法
                    JMethod getMethod = jDefinedClass.method(JMod.PUBLIC, dataTypeClass, "get" + ColName);
                    JBlock getBlock = getMethod.body();
                    JFieldRef getFieldRef = JExpr.ref(eleName);
                    getBlock._return(getFieldRef);
                }
                jCodeModel.build(destDir);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
