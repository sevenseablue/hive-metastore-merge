package com.q.hivetools.meta;


public class PartitionKeys {

    private Long tbl_id;
    private String pkey_comment;
    private String pkey_name;
    private String pkey_type;
    private Long integer_idx;

    public void PartitionKeys() {
    }

    public Long getTblId() {
        return tbl_id;
    }

    public void setTblId(Long tblId_) {
        tbl_id = tblId_;
    }

    public String getPkeyComment() {
        return pkey_comment;
    }

    public void setPkeyComment(String pkeyComment_) {
        pkey_comment = pkeyComment_;
    }

    public String getPkeyName() {
        return pkey_name;
    }

    public void setPkeyName(String pkeyName_) {
        pkey_name = pkeyName_;
    }

    public String getPkeyType() {
        return pkey_type;
    }

    public void setPkeyType(String pkeyType_) {
        pkey_type = pkeyType_;
    }

    public Long getIntegerIdx() {
        return integer_idx;
    }

    public void setIntegerIdx(Long integerIdx_) {
        integer_idx = integerIdx_;
    }

}
