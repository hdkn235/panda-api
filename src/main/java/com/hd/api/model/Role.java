package com.hd.api.model;

import javax.persistence.*;

public class Role {
    @Id
    @Column(name = "RoleId")
    private String roleId;

    @Column(name = "RoleName")
    private String roleName;

    @Column(name = "Level")
    private String level;

    @Column(name = "Status")
    private Integer status;

    @Column(name = "Remark")
    private String remark;

    public Role(String roleId, String roleName, String level, Integer status, String remark) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.level = level;
        this.status = status;
        this.remark = remark;
    }

    public Role() {
        super();
    }

    /**
     * @return RoleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     * @return RoleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * @return Level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level
     */
    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    /**
     * @return Status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return Remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}