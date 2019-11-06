package com.q.hivetools.meta;


public class Tbls {

    private Long tbl_id;
    private Long create_time;
    private Long db_id;
    private Long last_access_time;
    private String owner;
    private Long retention;
    private Long sd_id;
    private String tbl_name;
    private String tbl_type;
    private String view_expanded_text;
    private String view_original_text;
    private Boolean is_rewrite_enabled;

    public void Tbls() {
    }

    public Long getTblId() {
        return tbl_id;
    }

    public void setTblId(Long tblId_) {
        tbl_id = tblId_;
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

    public Long getLastAccessTime() {
        return last_access_time;
    }

    public void setLastAccessTime(Long lastAccessTime_) {
        last_access_time = lastAccessTime_;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner_) {
        owner = owner_;
    }

    public Long getRetention() {
        return retention;
    }

    public void setRetention(Long retention_) {
        retention = retention_;
    }

    public Long getSdId() {
        return sd_id;
    }

    public void setSdId(Long sdId_) {
        sd_id = sdId_;
    }

    public String getTblName() {
        return tbl_name;
    }

    public void setTblName(String tblName_) {
        tbl_name = tblName_;
    }

    public String getTblType() {
        return tbl_type;
    }

    public void setTblType(String tblType_) {
        tbl_type = tblType_;
    }

    public String getViewExpandedText() {
        return view_expanded_text;
    }

    public void setViewExpandedText(String viewExpandedText_) {
        view_expanded_text = viewExpandedText_;
    }

    public String getViewOriginalText() {
        return view_original_text;
    }

    public void setViewOriginalText(String viewOriginalText_) {
        view_original_text = viewOriginalText_;
    }

    public Boolean getIsRewriteEnabled() {
        return is_rewrite_enabled;
    }

    public void setIsRewriteEnabled(Boolean isRewriteEnabled_) {
        is_rewrite_enabled = isRewriteEnabled_;
    }

}
