package com.example.login.pojo;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;

@Table(name = "SYS_MENU")
public class Menu {
    @Id
    @Column(name = "MENU_ID")
    private String menuId;

    @Column(name = "MENU_NAME")
    private String menuName;

    @Column(name = "MENU_TYPE")
    private String menuType;

    @Column(name = "MENU_URL")
    private String menuUrl;

    @Column(name = "MENU_CODE")
    private String menuCode;

    @Column(name = "PARENT_ID")
    private String parentId;

    @Column(name = "PARENT_IDS")
    private String parentIds;

    @Column(name = "CHILD_NUM")
    private Integer childNum;

    @Column(name = "LISTORDER")
    private Integer listorder;

    @Column(name = "CRTD_DATE")
    private LocalDateTime crtdDate;

    @Column(name = "UPDTD_DATE")
    private LocalDateTime updtdDate;

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

    /**
     * @return MENU_NAME
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * @param menuName
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * @return MENU_TYPE
     */
    public String getMenuType() {
        return menuType;
    }

    /**
     * @param menuType
     */
    public void setMenuType(String menuType) {
        this.menuType = menuType == null ? null : menuType.trim();
    }

    /**
     * @return MENU_URL
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * @param menuUrl
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    /**
     * @return MENU_CODE
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * @param menuCode
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

    /**
     * @return PARENT_ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * @return PARENT_IDS
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * @param parentIds
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds == null ? null : parentIds.trim();
    }

    /**
     * @return CHILD_NUM
     */
    public Integer getChildNum() {
        return childNum;
    }

    /**
     * @param childNum
     */
    public void setChildNum(Integer childNum) {
        this.childNum = childNum;
    }

    /**
     * @return LISTORDER
     */
    public Integer getListorder() {
        return listorder;
    }

    /**
     * @param listorder
     */
    public void setListorder(Integer listorder) {
        this.listorder = listorder;
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