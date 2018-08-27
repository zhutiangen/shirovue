package com.example.login.pojo;

import javax.persistence.*;

@Table(name = "SYS_USER_ROLE")
public class UserRole {
    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Id
    @Column(name = "ROLE_ID")
    private String roleId;

    /**
     * @return USER_ID
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
     * @return ROLE_ID
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