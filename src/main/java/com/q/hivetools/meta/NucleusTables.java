package com.q.hivetools.meta;


public class NucleusTables {

    private String class_name;
    private String table_name;
    private String type;
    private String owner;
    private String version;
    private String interface_name;

    public void NucleusTables() {
    }

    public String getClassName() {
        return class_name;
    }

    public void setClassName(String className_) {
        class_name = className_;
    }

    public String getTableName() {
        return table_name;
    }

    public void setTableName(String tableName_) {
        table_name = tableName_;
    }

    public String getType() {
        return type;
    }

    public void setType(String type_) {
        type = type_;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner_) {
        owner = owner_;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version_) {
        version = version_;
    }

    public String getInterfaceName() {
        return interface_name;
    }

    public void setInterfaceName(String interfaceName_) {
        interface_name = interfaceName_;
    }

}
