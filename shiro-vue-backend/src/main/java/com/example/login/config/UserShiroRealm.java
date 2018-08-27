package com.example.login.config;


import com.example.login.pojo.User;
import com.example.login.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * shiro 重要组成部分
 * 进行身份认证、登入
 * 1.首先要继承AuthorizingRealm 需要实现doGetAuthorizationInfo 和 doGetAuthorizationInfo
 *
 */
public class UserShiroRealm extends AuthorizingRealm {

   @Autowired
   private UserService userService;


    /**
     *  权限的验证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }


    /**
     *  * 登入的认证
     * 何时调用
     * 当主业务中使用currentUser.login(token)时,进行调用
     * @param authenticationToken 内含有user信息
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String username = (String)authenticationToken.getPrincipal();

        try {

            User user = userService.getOneByUserName(username);
            if(user == null) {
                throw new UnknownAccountException();
            }

            // 形成数据库中认证数据  然后交给shiro进行验证
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                    user,
                    user.getPassword(),
                    ByteSource.Util.bytes(user.getCredentialsSalt()),user.getUsername()
            );

            return simpleAuthenticationInfo;
        } catch (Exception e) {
            throw new UnknownAccountException();
        }
    }
}
