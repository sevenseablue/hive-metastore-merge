
package com.q.hivetools.meta;


public class KeyConstraints {

    private Long child_cd_id;
    private Long child_integer_idx;
    private Long child_tbl_id;
    private Long parent_cd_id;
    private Long parent_integer_idx;
    private Long parent_tbl_id;
    private Long position;
    private String constraint_name;
    private Integer constraint_type;
    private Integer update_rule;
    private Integer delete_rule;
    private Integer enable_validate_rely;

    public void KeyConstraints() {
    }

    public void setChildCdId(Long childCdId_) {
        child_cd_id = childCdId_;
    }

    public Long getChildCdId() {
        return child_cd_id;
    }

    public void setChildIntegerIdx(Long childIntegerIdx_) {
        child_integer_idx = childIntegerIdx_;
    }

    public Long getChildIntegerIdx() {
        return child_integer_idx;
    }

    public void setChildTblId(Long childTblId_) {
        child_tbl_id = childTblId_;
    }

    public Long getChildTblId() {
        return child_tbl_id;
    }

    public void setParentCdId(Long parentCdId_) {
        parent_cd_id = parentCdId_;
    }

    public Long getParentCdId() {
        return parent_cd_id;
    }

    public void setParentIntegerIdx(Long parentIntegerIdx_) {
        parent_integer_idx = parentIntegerIdx_;
    }

    public Long getParentIntegerIdx() {
        return parent_integer_idx;
    }

    public void setParentTblId(Long parentTblId_) {
        parent_tbl_id = parentTblId_;
    }

    public Long getParentTblId() {
        return parent_tbl_id;
    }

    public void setPosition(Long position_) {
        position = position_;
    }

    public Long getPosition() {
        return position;
    }

    public void setConstraintName(String constraintName_) {
        constraint_name = constraintName_;
    }

    public String getConstraintName() {
        return constraint_name;
    }

    public void setConstraintType(Integer constraintType_) {
        constraint_type = constraintType_;
    }

    public Integer getConstraintType() {
        return constraint_type;
    }

    public void setUpdateRule(Integer updateRule_) {
        update_rule = updateRule_;
    }

    public Integer getUpdateRule() {
        return update_rule;
    }

    public void setDeleteRule(Integer deleteRule_) {
        delete_rule = deleteRule_;
    }

    public Integer getDeleteRule() {
        return delete_rule;
    }

    public void setEnableValidateRely(Integer enableValidateRely_) {
        enable_validate_rely = enableValidateRely_;
    }

    public Integer getEnableValidateRely() {
        return enable_validate_rely;
    }

}
