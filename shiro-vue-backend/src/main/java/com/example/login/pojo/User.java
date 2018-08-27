package com.example.login.pojo;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;

@Table(name = "SYS_USER")
public class User {
    @Id
    @Column(name = "U_ID")
    private String uId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "STATE")
    private Integer state;

    @Column(name = "SALT")
    private String salt;

    @Column(name = "IS_SYSTEM")
    private Integer isSystem;

    @Column(name = "CRTD_DATE")
    private LocalDateTime crtdDate;

    @Column(name = "UPDTD_DATE")
    private LocalDateTime updtdDate;

    // Transient本地化 不做序列化处理 这样就不会映射到数据库
    @Transient
    private String credentialsSalt;
    /**
     * @return U_ID
     */
    public String getuId() {
        return uId;
    }

    /**
     * @param uId
     */
    public void setuId(String uId) {
        this.uId = uId == null ? null : uId.trim();
    }

    /**
     * @return USERNAME
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return PASSWORD
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return STATE
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @return SALT
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * @return IS_SYSTEM
     */
    public Integer getIsSystem() {
        return isSystem;
    }

    /**
     * @param isSystem
     */
    public void setIsSystem(Integer isSystem) {
        this.isSystem = isSystem;
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

    public String getCredentialsSalt() {
        return this.username+this.salt;
    }
}