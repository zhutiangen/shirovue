package com.example.login.pojo;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;

@Table(name = "SYS_ROLE")
public class Role {
    @Id
    @Column(name = "ROLE_ID")
    private String roleId;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @Column(name = "ROLE_DESC")
    private String roleDesc;

    @Column(name = "ENABLE")
    private Integer enable;

    @Column(name = "CRTD_DATE")
    private LocalDateTime crtdDate;

    @Column(name = "UPDTD_DATE")
    private LocalDateTime updtdDate;

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

    /**
     * @return ROLE_NAME
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return ROLE_DESC
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * @param roleDesc
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    /**
     * @return ENABLE
     */
    public Integer getEnable() {
        return enable;
    }

    /**
     * @param enable
     */
    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    /**
     * @return CRTD_DATE
     */
    public LocalDateTime getCrtdDate() {
        return crtdDate;
    }

    /**
     * @param crtdDate
     */
    public void setCrtdDate(LocalDateTime crtdDate) {
        this.crtdDate = crtdDate;
    }

    /**
     * @return UPDTD_DATE
     */
    public LocalDateTime getUpdtdDate() {
        return updtdDate;
    }

    /**
     * @param updtdDate
     */
    public void setUpdtdDate(LocalDateTime updtdDate) {
        this.updtdDate = updtdDate;
    }
}