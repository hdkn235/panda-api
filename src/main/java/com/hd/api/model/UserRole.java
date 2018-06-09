package com.hd.api.model;

import javax.persistence.*;

public class UserRole {
    @Id
    @Column(name = "UserRoleId")
    private String userRoleId;

    @Column(name = "UserId")
    private String userId;

    @Column(name = "RoleId")
    private String roleId;

    public UserRole(String userRoleId, String userId, String roleId) {
        this.userRoleId = userRoleId;
        this.userId = userId;
        this.roleId = roleId;
    }

    public UserRole() {
        super();
    }

    /**
     * @return UserRoleId
     */
    public String getUserRoleId() {
        return userRoleId;
    }

    /**
     * @param userRoleId
     */
    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId == null ? null : userRoleId.trim();
    }

    /**
     * @return UserId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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
}