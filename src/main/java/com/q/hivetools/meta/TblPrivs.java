package com.q.hivetools.meta;


public class TblPrivs {

    private Long tbl_grant_id;
    private Long create_time;
    private Integer grant_option;
    private String grantor;
    private String grantor_type;
    private String principal_name;
    private String principal_type;
    private String tbl_priv;
    private Long tbl_id;

    public void TblPrivs() {
    }

    public Long getTblGrantId() {
        return tbl_grant_id;
    }

    public void setTblGrantId(Long tblGrantId_) {
        tbl_grant_id = tblGrantId_;
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

    public String getTblPriv() {
        return tbl_priv;
    }

    public void setTblPriv(String tblPriv_) {
        tbl_priv = tblPriv_;
    }

    public Long getTblId() {
        return tbl_id;
    }

    public void setTblId(Long tblId_) {
        tbl_id = tblId_;
    }

}
