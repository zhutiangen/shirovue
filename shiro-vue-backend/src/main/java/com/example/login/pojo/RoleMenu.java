package com.example.login.pojo;

import javax.persistence.*;

@Table(name = "SYS_ROLE_MENU")
public class RoleMenu {
    @Id
    @Column(name = "ROLE_ID")
    private String roleId;

    @Id
    @Column(name = "MENU_ID")
    private String menuId;

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
     * @return MENU_ID
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * @param menuId
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }
}