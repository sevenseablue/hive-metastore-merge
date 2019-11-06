package com.q.hivetools.meta;


public class Partitions {

    private Long part_id;
    private Long create_time;
    private Long last_access_time;
    private String part_name;
    private Long sd_id;
    private Long tbl_id;

    public void Partitions() {
    }

    public Long getPartId() {
        return part_id;
    }

    public void setPartId(Long partId_) {
        part_id = partId_;
    }

    public Long getCreateTime() {
        return create_time;
    }

    public void setCreateTime(Long createTime_) {
        create_time = createTime_;
    }

    public Long getLastAccessTime() {
        return last_access_time;
    }

    public void setLastAccessTime(Long lastAccessTime_) {
        last_access_time = lastAccessTime_;
    }

    public String getPartName() {
        return part_name;
    }

    public void setPartName(String partName_) {
        part_name = partName_;
    }

    public Long getSdId() {
        return sd_id;
    }

    public void setSdId(Long sdId_) {
        sd_id = sdId_;
    }

    public Long getTblId() {
        return tbl_id;
    }

    public void setTblId(Long tblId_) {
        tbl_id = tblId_;
    }

}
