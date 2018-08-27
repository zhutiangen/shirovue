package com.example.login.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 密码生成工具类
 */
public class PasswordUtil {

    public static String createAdminPwd(String password, String salt){
        return new SimpleHash("md5",password,ByteSource.Util.bytes(salt),2).toHex();
    }

    public static String createCustomPwd(String password, String salt){
        return new SimpleHash("md5",password,ByteSource.Util.bytes(salt),1).toHex();
    }
    
    public static void main(String[] args) throws Exception {
    	System.out.println(createAdminPwd("admin","1ab6d62faa91ae7deec76d6f13ef1600"));
	}
}
