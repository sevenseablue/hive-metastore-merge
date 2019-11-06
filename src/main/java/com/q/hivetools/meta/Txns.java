package com.q.hivetools.meta;


public class Txns {

    private Long txn_id;
    private String txn_state;
    private Long txn_started;
    private Long txn_last_heartbeat;
    private String txn_user;
    private String txn_host;
    private String txn_agent_info;
    private String txn_meta_info;
    private Long txn_heartbeat_count;

    public void Txns() {
    }

    public Long getTxnId() {
        return txn_id;
    }

    public void setTxnId(Long txnId_) {
        txn_id = txnId_;
    }

    public String getTxnState() {
        return txn_state;
    }

    public void setTxnState(String txnState_) {
        txn_state = txnState_;
    }

    public Long getTxnStarted() {
        return txn_started;
    }

    public void setTxnStarted(Long txnStarted_) {
        txn_started = txnStarted_;
    }

    public Long getTxnLastHeartbeat() {
        return txn_last_heartbeat;
    }

    public void setTxnLastHeartbeat(Long txnLastHeartbeat_) {
        txn_last_heartbeat = txnLastHeartbeat_;
    }

    public String getTxnUser() {
        return txn_user;
    }

    public void setTxnUser(String txnUser_) {
        txn_user = txnUser_;
    }

    public String getTxnHost() {
        return txn_host;
    }

    public void setTxnHost(String txnHost_) {
        txn_host = txnHost_;
    }

    public String getTxnAgentInfo() {
        return txn_agent_info;
    }

    public void setTxnAgentInfo(String txnAgentInfo_) {
        txn_agent_info = txnAgentInfo_;
    }

    public String getTxnMetaInfo() {
        return txn_meta_info;
    }

    public void setTxnMetaInfo(String txnMetaInfo_) {
        txn_meta_info = txnMetaInfo_;
    }

    public Long getTxnHeartbeatCount() {
        return txn_heartbeat_count;
    }

    public void setTxnHeartbeatCount(Long txnHeartbeatCount_) {
        txn_heartbeat_count = txnHeartbeatCount_;
    }

}
