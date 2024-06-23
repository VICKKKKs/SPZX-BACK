package com.atguigu.spzx.model.entity.user;

public class AuthContextUtil {

    public static ThreadLocal<UserInfo> sysUserThreadLocal = new ThreadLocal<>();

    public static void  setUser(UserInfo user){
        sysUserThreadLocal.set(user);
    }

    public static UserInfo getUser(){
        UserInfo userInfo = sysUserThreadLocal.get();
        return userInfo;
    }
}