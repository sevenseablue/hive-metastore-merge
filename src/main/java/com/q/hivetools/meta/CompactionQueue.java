package com.q.hivetools.meta;


public class CompactionQueue {

    private Long cq_id;
    private String cq_database;
    private String cq_table;
    private String cq_partition;
    private String cq_state;
    private String cq_type;
    private String cq_tblproperties;
    private String cq_worker_id;
    private Long cq_start;
    private String cq_run_as;
    private Long cq_highest_txn_id;
    private String cq_hadoop_job_id;

    public void CompactionQueue() {
    }

    public Long getCqId() {
        return cq_id;
    }

    public void setCqId(Long cqId_) {
        cq_id = cqId_;
    }

    public String getCqDatabase() {
        return cq_database;
    }

    public void setCqDatabase(String cqDatabase_) {
        cq_database = cqDatabase_;
    }

    public String getCqTable() {
        return cq_table;
    }

    public void setCqTable(String cqTable_) {
        cq_table = cqTable_;
    }

    public String getCqPartition() {
        return cq_partition;
    }

    public void setCqPartition(String cqPartition_) {
        cq_partition = cqPartition_;
    }

    public String getCqState() {
        return cq_state;
    }

    public void setCqState(String cqState_) {
        cq_state = cqState_;
    }

    public String getCqType() {
        return cq_type;
    }

    public void setCqType(String cqType_) {
        cq_type = cqType_;
    }

    public String getCqTblproperties() {
        return cq_tblproperties;
    }

    public void setCqTblproperties(String cqTblproperties_) {
        cq_tblproperties = cqTblproperties_;
    }

    public String getCqWorkerId() {
        return cq_worker_id;
    }

    public void setCqWorkerId(String cqWorkerId_) {
        cq_worker_id = cqWorkerId_;
    }

    public Long getCqStart() {
        return cq_start;
    }

    public void setCqStart(Long cqStart_) {
        cq_start = cqStart_;
    }

    public String getCqRunAs() {
        return cq_run_as;
    }

    public void setCqRunAs(String cqRunAs_) {
        cq_run_as = cqRunAs_;
    }

    public Long getCqHighestTxnId() {
        return cq_highest_txn_id;
    }

    public void setCqHighestTxnId(Long cqHighestTxnId_) {
        cq_highest_txn_id = cqHighestTxnId_;
    }

    public String getCqHadoopJobId() {
        return cq_hadoop_job_id;
    }

    public void setCqHadoopJobId(String cqHadoopJobId_) {
        cq_hadoop_job_id = cqHadoopJobId_;
    }

}
