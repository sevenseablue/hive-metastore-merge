package com.q.hivetools.meta;


public class SerdeParams {

    private Long serde_id;
    private String param_key;
    private String param_value;

    public void SerdeParams() {
    }

    public Long getSerdeId() {
        return serde_id;
    }

    public void setSerdeId(Long serdeId_) {
        serde_id = serdeId_;
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
