package com.example.login.controller;

import com.example.login.pojo.User;
import com.example.login.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/console/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;


    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public void getUser() {
        String username = "admin";
        User user = userService.getOneByUserName(username);
        System.out.println(user.toString());
    }
}
