package com.q.hivetools.meta;


public class FuncRu {

    private Long func_id;
    private Long resource_type;
    private String resource_uri;
    private Long integer_idx;

    public void FuncRu() {
    }

    public Long getFuncId() {
        return func_id;
    }

    public void setFuncId(Long funcId_) {
        func_id = funcId_;
    }

    public Long getResourceType() {
        return resource_type;
    }

    public void setResourceType(Long resourceType_) {
        resource_type = resourceType_;
    }

    public String getResourceUri() {
        return resource_uri;
    }

    public void setResourceUri(String resourceUri_) {
        resource_uri = resourceUri_;
    }

    public Long getIntegerIdx() {
        return integer_idx;
    }

    public void setIntegerIdx(Long integerIdx_) {
        integer_idx = integerIdx_;
    }

}
