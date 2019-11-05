
package com.q.hivetools.meta;


public class CompletedCompactions {

    private Long cc_id;
    private String cc_database;
    private String cc_table;
    private String cc_partition;
    private String cc_state;
    private String cc_type;
    private String cc_tblproperties;
    private String cc_worker_id;
    private Long cc_start;
    private Long cc_end;
    private String cc_run_as;
    private Long cc_highest_txn_id;
    private String cc_hadoop_job_id;

    public void CompletedCompactions() {
    }

    public void setCcId(Long ccId_) {
        cc_id = ccId_;
    }

    public Long getCcId() {
        return cc_id;
    }

    public void setCcDatabase(String ccDatabase_) {
        cc_database = ccDatabase_;
    }

    public String getCcDatabase() {
        return cc_database;
    }

    public void setCcTable(String ccTable_) {
        cc_table = ccTable_;
    }

    public String getCcTable() {
        return cc_table;
    }

    public void setCcPartition(String ccPartition_) {
        cc_partition = ccPartition_;
    }

    public String getCcPartition() {
        return cc_partition;
    }

    public void setCcState(String ccState_) {
        cc_state = ccState_;
    }

    public String getCcState() {
        return cc_state;
    }

    public void setCcType(String ccType_) {
        cc_type = ccType_;
    }

    public String getCcType() {
        return cc_type;
    }

    public void setCcTblproperties(String ccTblproperties_) {
        cc_tblproperties = ccTblproperties_;
    }

    public String getCcTblproperties() {
        return cc_tblproperties;
    }

    public void setCcWorkerId(String ccWorkerId_) {
        cc_worker_id = ccWorkerId_;
    }

    public String getCcWorkerId() {
        return cc_worker_id;
    }

    public void setCcStart(Long ccStart_) {
        cc_start = ccStart_;
    }

    public Long getCcStart() {
        return cc_start;
    }

    public void setCcEnd(Long ccEnd_) {
        cc_end = ccEnd_;
    }

    public Long getCcEnd() {
        return cc_end;
    }

    public void setCcRunAs(String ccRunAs_) {
        cc_run_as = ccRunAs_;
    }

    public String getCcRunAs() {
        return cc_run_as;
    }

    public void setCcHighestTxnId(Long ccHighestTxnId_) {
        cc_highest_txn_id = ccHighestTxnId_;
    }

    public Long getCcHighestTxnId() {
        return cc_highest_txn_id;
    }

    public void setCcHadoopJobId(String ccHadoopJobId_) {
        cc_hadoop_job_id = ccHadoopJobId_;
    }

    public String getCcHadoopJobId() {
        return cc_hadoop_job_id;
    }

}
