package com.example.login.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;

/**
 * shiro主要两个工作
 *  1.身份认证/登入
 *  2.权限验证
 */
@Configuration
public class ShiroConfig {

    private Logger logger = LoggerFactory.getLogger(ShiroConfig.class);


    @Bean("shiroFilter")
    public ShiroFilterFactoryBean ShiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 可以自定义过滤器
        Map<String, Filter> filterMap = new HashMap();
        shiroFilterFactoryBean.setFilters(filterMap);

        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        filterChainDefinitionMap.put("*/login", "anon");
//        filterChainDefinitionMap.put("*/unauthorized", "anon");
//        filterChainDefinitionMap.put("*/logout", "logout"); //logout是shiro提供的过滤器
//        filterChainDefinitionMap.put("/**", "authc"); // 其他都要验证

        return shiroFilterFactoryBean;
    }

    /**
     * 核心配置
     * @return
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 配置缓存
        securityManager.setCacheManager(getMemoryConstrainedCacheManager());
        // 配置session管理
        securityManager.setSessionManager(getSessionManager());
//        securityManager.setAuthenticator();

        // 配置登入认证
        securityManager.setRealm(getShiroRealm());
        return securityManager;
    }

    @Bean("userShiroRealm")
    public UserShiroRealm getShiroRealm() {
        UserShiroRealm userShiroRealm = new UserShiroRealm();

        // 自定义密码比较器
        userShiroRealm.setCredentialsMatcher(adminHashedCredentialsMatcher());
        return userShiroRealm;
    }

    // 可以自定义密码比较器  这里用的是shiro的密码比较器
    @Bean("adminHashedCredentialsMatcher")
    public HashedCredentialsMatcher adminHashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数,相当于d5(md5(""));
        return hashedCredentialsMatcher;
    }

    /**
     * shiro 自带的缓存处理
     * 缓存的作用：shiro在验证权限，会先匹配缓存中是否有sessionid 然后比对权限 如果没有匹配到sessionid就会到数据库进行比对
     * @return
     */
    @Bean("shiroCacheManager")
    public MemoryConstrainedCacheManager getMemoryConstrainedCacheManager() {
      return new MemoryConstrainedCacheManager();
    }

    /**
     * shiro 使用ehcache缓存管理
     */
    @Bean("ehcacheCacheManager")
    public EhCacheManager getEhCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return ehCacheManager;
    }

    /**
     * shiro 使用redis缓存管理
     * 为了完成分布式中单点登入的问题
     */
//    @Bean("redisCacheManager")
//    public RedisSessionDAO redisSessionDAO() {
//        return new RedisSessionDAO();
//    }

    /**
     * session 的配置 例如 设置过期时间等
     * @return
     */
    @Bean("sessionManager")
    public DefaultWebSessionManager getSessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();

        // 如果想监听session执行过程的 可以加入监听
        Collection<SessionListener> sessionListeners = new ArrayList<>();
        sessionListeners.add(new AdminSessionLister());
        sessionManager.setSessionListeners(sessionListeners);

        // 设置shiro 返回的token
        Cookie cookie = new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
        cookie.setName("AUTH");
        cookie.setHttpOnly(true);
        sessionManager.setSessionIdCookie(cookie);

        sessionManager.setSessionValidationInterval(1000 * 60 * 60 * 2);
        sessionManager.setGlobalSessionTimeout(1000 * 60 * 60 * 2);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        // 定时清理失效会话, 清理用户直接关闭浏览器造成的孤立会话
        sessionManager.setSessionValidationInterval(1000 * 60 * 60);
        return sessionManager;
    }

//    @Bean("redisSessionDao")
//    public RedisSessionDao redisSessionDao(){
//        return new RedisSessionDAO();
//    }

    /**
     * shiro 支持AOP
     * 使用注解方式进行验证用户
     * @param securityManager
     * @return
     */
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
