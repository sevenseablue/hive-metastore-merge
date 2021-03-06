package com.q.hivetools.meta;


public class HiveLocks {

    private Long hl_lock_ext_id;
    private Long hl_lock_int_id;
    private Long hl_txnid;
    private String hl_db;
    private String hl_table;
    private String hl_partition;
    private String hl_lock_state;
    private String hl_lock_type;
    private Long hl_last_heartbeat;
    private Long hl_acquired_at;
    private String hl_user;
    private String hl_host;
    private Long hl_heartbeat_count;
    private String hl_agent_info;
    private Long hl_blockedby_ext_id;
    private Long hl_blockedby_int_id;

    public void HiveLocks() {
    }

    public Long getHlLockExtId() {
        return hl_lock_ext_id;
    }

    public void setHlLockExtId(Long hlLockExtId_) {
        hl_lock_ext_id = hlLockExtId_;
    }

    public Long getHlLockIntId() {
        return hl_lock_int_id;
    }

    public void setHlLockIntId(Long hlLockIntId_) {
        hl_lock_int_id = hlLockIntId_;
    }

    public Long getHlTxnid() {
        return hl_txnid;
    }

    public void setHlTxnid(Long hlTxnid_) {
        hl_txnid = hlTxnid_;
    }

    public String getHlDb() {
        return hl_db;
    }

    public void setHlDb(String hlDb_) {
        hl_db = hlDb_;
    }

    public String getHlTable() {
        return hl_table;
    }

    public void setHlTable(String hlTable_) {
        hl_table = hlTable_;
    }

    public String getHlPartition() {
        return hl_partition;
    }

    public void setHlPartition(String hlPartition_) {
        hl_partition = hlPartition_;
    }

    public String getHlLockState() {
        return hl_lock_state;
    }

    public void setHlLockState(String hlLockState_) {
        hl_lock_state = hlLockState_;
    }

    public String getHlLockType() {
        return hl_lock_type;
    }

    public void setHlLockType(String hlLockType_) {
        hl_lock_type = hlLockType_;
    }

    public Long getHlLastHeartbeat() {
        return hl_last_heartbeat;
    }

    public void setHlLastHeartbeat(Long hlLastHeartbeat_) {
        hl_last_heartbeat = hlLastHeartbeat_;
    }

    public Long getHlAcquiredAt() {
        return hl_acquired_at;
    }

    public void setHlAcquiredAt(Long hlAcquiredAt_) {
        hl_acquired_at = hlAcquiredAt_;
    }

    public String getHlUser() {
        return hl_user;
    }

    public void setHlUser(String hlUser_) {
        hl_user = hlUser_;
    }

    public String getHlHost() {
        return hl_host;
    }

    public void setHlHost(String hlHost_) {
        hl_host = hlHost_;
    }

    public Long getHlHeartbeatCount() {
        return hl_heartbeat_count;
    }

    public void setHlHeartbeatCount(Long hlHeartbeatCount_) {
        hl_heartbeat_count = hlHeartbeatCount_;
    }

    public String getHlAgentInfo() {
        return hl_agent_info;
    }

    public void setHlAgentInfo(String hlAgentInfo_) {
        hl_agent_info = hlAgentInfo_;
    }

    public Long getHlBlockedbyExtId() {
        return hl_blockedby_ext_id;
    }

    public void setHlBlockedbyExtId(Long hlBlockedbyExtId_) {
        hl_blockedby_ext_id = hlBlockedbyExtId_;
    }

    public Long getHlBlockedbyIntId() {
        return hl_blockedby_int_id;
    }

    public void setHlBlockedbyIntId(Long hlBlockedbyIntId_) {
        hl_blockedby_int_id = hlBlockedbyIntId_;
    }

}
