package com.q.hivetools.meta;


public class PartitionParams {

    private Long part_id;
    private String param_key;
    private String param_value;

    public void PartitionParams() {
    }

    public Long getPartId() {
        return part_id;
    }

    public void setPartId(Long partId_) {
        part_id = partId_;
    }

    public String getParamKey() {
        return param_key;
    }

    public void setParamKey(String paramKey_) {
        param_key = paramKey_;
    }

    public String getParamValue() {
        return param_value;
    }

    public void setParamValue(String paramValue_) {
        param_value = paramValue_;
    }

}
