#shiro + vue 登入模块脚手架

原理和实现会依次加入
##实现过程
 ###搭建一个使用spring boot + ssm的框架
 注意点：1.spring boot2.0.4版本 与 druid版本兼容问题
        2.mybatis对数据库timestamp映射成localDateTime的jar包
 
 ###登入模块的实现
 1.实现logincontroller类
   logincontroller中的login登入方法，发现subject.login(token)报错，原因未加shiro配置
   
 2、实现登入时，用户名和密码的验证功能
   加入配置shiroConfig配置文件。
   第一步：加入shiroFilter配置，引入securityManager配置。
   第二步：实现securityManager配置，需要配置cache、Session、realm.
   第三步：编写自定义的realm。
   
 3、加入AOP，用户权限登入验证。
   