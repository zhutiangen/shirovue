package com.example.login.config;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义token数据
 * 如果有两种登入token，就可以继承UsernamePasswordToken
 */
public class CustomerAuthenticationToken extends UsernamePasswordToken {
    private Logger logger = LoggerFactory.getLogger(CustomerAuthenticationToken.class);

    /**
     * 用来标识不同的用户
     * 分为前台用户、后台管理用户
     */
    private Integer userType;

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
