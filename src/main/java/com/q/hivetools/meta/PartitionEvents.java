package com.q.hivetools.meta;


public class PartitionEvents {

    private Long part_name_id;
    private String db_name;
    private Long event_time;
    private Long event_type;
    private String partition_name;
    private String tbl_name;

    public void PartitionEvents() {
    }

    public Long getPartNameId() {
        return part_name_id;
    }

    public void setPartNameId(Long partNameId_) {
        part_name_id = partNameId_;
    }

    public String getDbName() {
        return db_name;
    }

    public void setDbName(String dbName_) {
        db_name = dbName_;
    }

    public Long getEventTime() {
        return event_time;
    }

    public void setEventTime(Long eventTime_) {
        event_time = eventTime_;
    }

    public Long getEventType() {
        return event_type;
    }

    public void setEventType(Long eventType_) {
        event_type = eventType_;
    }

    public String getPartitionName() {
        return partition_name;
    }

    public void setPartitionName(String partitionName_) {
        partition_name = partitionName_;
    }

    public String getTblName() {
        return tbl_name;
    }

    public void setTblName(String tblName_) {
        tbl_name = tblName_;
    }

}
