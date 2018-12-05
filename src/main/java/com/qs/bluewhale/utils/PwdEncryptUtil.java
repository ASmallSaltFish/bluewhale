package com.qs.bluewhale.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PwdEncryptUtil {

    //加盐参数
    public final static String hashAlgorithmName = "MD5";

    //循环次数
    public final static int hashIterations = 1024;

    /**
     * shiro密码加密工具类
     *
     * @param credentials 密码
     * @param saltSource  密码盐
     * @return
     */
    public static String getPassword(String credentials, String saltSource) {
        ByteSource salt = new Md5Hash(saltSource);
        return new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations).toString();
    }


    public static void main(String[] args) {
        String userId = "1069915463289114625";
        String userName = "admin";
        String salt = userId + userName;
        String encryptPassword = getPassword("admin", salt);
        //905816fe1799bb76b0d6d866374bbca8
        System.out.println("--->>>password=" + encryptPassword);
    }
}
