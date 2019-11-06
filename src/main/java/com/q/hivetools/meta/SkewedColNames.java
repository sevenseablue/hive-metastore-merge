package com.q.hivetools.meta;


public class SkewedColNames {

    private Long sd_id;
    private String skewed_col_name;
    private Long integer_idx;

    public void SkewedColNames() {
    }

    public Long getSdId() {
        return sd_id;
    }

    public void setSdId(Long sdId_) {
        sd_id = sdId_;
    }

    public String getSkewedColName() {
        return skewed_col_name;
    }

    public void setSkewedColName(String skewedColName_) {
        skewed_col_name = skewedColName_;
    }

    public Long getIntegerIdx() {
        return integer_idx;
    }

    public void setIntegerIdx(Long integerIdx_) {
        integer_idx = integerIdx_;
    }

}
