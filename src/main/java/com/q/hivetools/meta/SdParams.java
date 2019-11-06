package com.q.hivetools.meta;


public class SdParams {

    private Long sd_id;
    private String param_key;
    private String param_value;

    public void SdParams() {
    }

    public Long getSdId() {
        return sd_id;
    }

    public void setSdId(Long sdId_) {
        sd_id = sdId_;
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
