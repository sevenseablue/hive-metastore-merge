package com.q.hivetools.meta;


public class GlobalPrivs {

    private Long user_grant_id;
    private Long create_time;
    private Integer grant_option;
    private String grantor;
    private String grantor_type;
    private String principal_name;
    private String principal_type;
    private String user_priv;

    public void GlobalPrivs() {
    }

    public Long getUserGrantId() {
        return user_grant_id;
    }

    public void setUserGrantId(Long userGrantId_) {
        user_grant_id = userGrantId_;
    }

    public Long getCreateTime() {
        return create_time;
    }

    public void setCreateTime(Long createTime_) {
        create_time = createTime_;
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

    public String getUserPriv() {
        return user_priv;
    }

    public void setUserPriv(String userPriv_) {
        user_priv = userPriv_;
    }

}
