package com.q.hivetools.meta;


public class Version {

    private Long ver_id;
    private String schema_version;
    private String version_comment;

    public void Version() {
    }

    public Long getVerId() {
        return ver_id;
    }

    public void setVerId(Long verId_) {
        ver_id = verId_;
    }

    public String getSchemaVersion() {
        return schema_version;
    }

    public void setSchemaVersion(String schemaVersion_) {
        schema_version = schemaVersion_;
    }

    public String getVersionComment() {
        return version_comment;
    }

    public void setVersionComment(String versionComment_) {
        version_comment = versionComment_;
    }

}
