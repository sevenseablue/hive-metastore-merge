package com.q.hivetools.meta;


public class PartitionKeyVals {

    private Long part_id;
    private String part_key_val;
    private Long integer_idx;

    public void PartitionKeyVals() {
    }

    public Long getPartId() {
        return part_id;
    }

    public void setPartId(Long partId_) {
        part_id = partId_;
    }

    public String getPartKeyVal() {
        return part_key_val;
    }

    public void setPartKeyVal(String partKeyVal_) {
        part_key_val = partKeyVal_;
    }

    public Long getIntegerIdx() {
        return integer_idx;
    }

    public void setIntegerIdx(Long integerIdx_) {
        integer_idx = integerIdx_;
    }

}
