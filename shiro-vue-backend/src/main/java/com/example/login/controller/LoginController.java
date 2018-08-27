package com.example.login.controller;

import com.example.login.model.UserModel;
import com.example.login.pojo.User;
import com.example.login.service.UserService;
import com.example.login.util.PasswordUtil;
import com.example.login.util.UuidUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/console")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    /**
     * 登入模块
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestBody @Valid UserModel userModel, BindingResult bindingResult) {
        Map<String, Object> reMap  = new HashMap<>();
        try {
            if (bindingResult.hasErrors()) {
                bindingResult.getAllErrors().forEach(error -> {
                    logger.error(error.toString());
                });
                reMap.put("status", "false");
                return reMap;
            }
            String username = userModel.getUserName();
            // 生成 shiro自定的对象token
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, userModel.getPassword(), false);

            Subject subject = SecurityUtils.getSubject();

            //会调用Realm类的认证 传入一个界面用户 然后与数据库存在的用户进行比对。
            subject.login(usernamePasswordToken);
            logger.info("用户" + username + "验证成功登入成功");
            reMap.put("status", "true");

        }catch (Exception e) {
            e.printStackTrace();
        }
        return reMap;
    }

    /**
     * 用户注册
     * @param userModel
     * @param result
     */
    @RequestMapping(value="register", method = RequestMethod.POST)
    public void register(@Valid UserModel userModel, BindingResult result) {
        try {
            if (result.hasErrors()) return;

            User user = new User();
            user.setuId(UuidUtil.getUUID());
            user.setUsername(userModel.getUserName());
            // SecureRandomNumberGenerator 用的是shiro的
            user.setSalt(new SecureRandomNumberGenerator().nextBytes().toHex());
            // 对密码进行MD5加密
            user.setPassword(PasswordUtil.createAdminPwd(userModel.getPassword(), user.getSalt()));
            user.setCrtdDate(LocalDateTime.now());
            user.setUpdtdDate(LocalDateTime.now());
            user.setIsSystem(0);

            userService.save(user);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String loginout() {
        String a = "ss";
        System.out.println("user loginout");
        return a;
    }
}
