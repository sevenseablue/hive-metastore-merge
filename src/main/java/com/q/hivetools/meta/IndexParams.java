package com.q.hivetools.meta;


public class IndexParams {

    private Long index_id;
    private String param_key;
    private String param_value;

    public void IndexParams() {
    }

    public Long getIndexId() {
        return index_id;
    }

    public void setIndexId(Long indexId_) {
        index_id = indexId_;
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
