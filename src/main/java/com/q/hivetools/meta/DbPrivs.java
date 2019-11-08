package com.q.hivetools.meta;


public class DbPrivs {

    private Long db_grant_id;
    private Long create_time;
    private Long db_id;
    private Integer grant_option;
    private String grantor;
    private String grantor_type;
    private String principal_name;
    private String principal_type;
    private String db_priv;

    public void DbPrivs() {
    }

    public Long getDbGrantId() {
        return db_grant_id;
    }

    public void setDbGrantId(Long dbGrantId_) {
        db_grant_id = dbGrantId_;
    }

    public Long getCreateTime() {
        return create_time;
    }

    public void setCreateTime(Long createTime_) {
        create_time = createTime_;
    }

    public Long getDbId() {
        return db_id;
    }

    public void setDbId(Long dbId_) {
        db_id = dbId_;
    }

    public Integer getGrantOption() {
        return grant_option;
    }

    public void setGrantOption(Integer grantOption_) {
        grant_option = grantOption_;
    }

    public String getGrantor() {
        return grantor;
    }

    public void setGrantor(String grantor_) {
        grantor = grantor_;
    }

    public String getGrantorType() {
        return grantor_type;
    }

    public void setGrantorType(String grantorType_) {
        grantor_type = grantorType_;
    }

    public String getPrincipalName() {
        return principal_name;
    }

    public void setPrincipalName(String principalName_) {
        principal_name = principalName_;
    }

    public String getPrincipalType() {
        return principal_type;
    }

    public void setPrincipalType(String principalType_) {
        principal_type = principalType_;
    }

    public String getDbPriv() {
        return db_priv;
    }

    public void setDbPriv(String dbPriv_) {
        db_priv = dbPriv_;
    }

    @Override
    public String toString() {
        return "DbPrivs{" +
                "db_grant_id=" + db_grant_id +
                ", create_time=" + create_time +
                ", db_id=" + db_id +
                ", grant_option=" + grant_option +
                ", grantor='" + grantor + '\'' +
                ", grantor_type='" + grantor_type + '\'' +
                ", principal_name='" + principal_name + '\'' +
                ", principal_type='" + principal_type + '\'' +
                ", db_priv='" + db_priv + '\'' +
                '}';
    }
}
