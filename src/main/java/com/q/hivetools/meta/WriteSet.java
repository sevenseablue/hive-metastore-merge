package com.q.hivetools.meta;


public class WriteSet {

    private String ws_database;
    private String ws_table;
    private String ws_partition;
    private Long ws_txnid;
    private Long ws_commit_id;
    private String ws_operation_type;

    public void WriteSet() {
    }

    public String getWsDatabase() {
        return ws_database;
    }

    public void setWsDatabase(String wsDatabase_) {
        ws_database = wsDatabase_;
    }

    public String getWsTable() {
        return ws_table;
    }

    public void setWsTable(String wsTable_) {
        ws_table = wsTable_;
    }

    public String getWsPartition() {
        return ws_partition;
    }

    public void setWsPartition(String wsPartition_) {
        ws_partition = wsPartition_;
    }

    public Long getWsTxnid() {
        return ws_txnid;
    }

    public void setWsTxnid(Long wsTxnid_) {
        ws_txnid = wsTxnid_;
    }

    public Long getWsCommitId() {
        return ws_commit_id;
    }

    public void setWsCommitId(Long wsCommitId_) {
        ws_commit_id = wsCommitId_;
    }

    public String getWsOperationType() {
        return ws_operation_type;
    }

    public void setWsOperationType(String wsOperationType_) {
        ws_operation_type = wsOperationType_;
    }

}
