package com.q.hivetools.meta;


public class Serdes {

    private Long serde_id;
    private String name;
    private String slib;

    public void Serdes() {
    }

    public Long getSerdeId() {
        return serde_id;
    }

    public void setSerdeId(Long serdeId_) {
        serde_id = serdeId_;
    }

    public String getName() {
        return name;
    }

    public void setName(String name_) {
        name = name_;
    }

    public String getSlib() {
        return slib;
    }

    public void setSlib(String slib_) {
        slib = slib_;
    }

}
