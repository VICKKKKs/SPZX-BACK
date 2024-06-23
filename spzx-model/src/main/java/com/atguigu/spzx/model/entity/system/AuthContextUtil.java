package com.atguigu.spzx.model.entity.system;

public class AuthContextUtil {

    public static ThreadLocal<SysUser> sysUserThreadLocal = new ThreadLocal<>();

    public static void  setSysUser(SysUser sysUser){
        sysUserThreadLocal.set(sysUser);
    }

    public static SysUser getSysUser(){
        SysUser sysUser = sysUserThreadLocal.get();
        return sysUser;
    }
}
