package com.example.login.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotEmpty;

/**
 * 接受前端的user
 */
public class UserModel {
    private Logger logger = LoggerFactory.getLogger(UserModel.class);

    @NotEmpty(message = "用户名不可为空")
    private String userName;

    @NotEmpty(message = "密码不可为空")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
