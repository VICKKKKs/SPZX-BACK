package com.atguigu.spzx.model.entity.system;

public class SysUserAuthContextUtil {

    private static final ThreadLocal<SysUser> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(SysUser sysUser){
        THREAD_LOCAL.set(sysUser);
    }

    public static SysUser get(){
        SysUser sysUser = THREAD_LOCAL.get();
        return sysUser;
    }

    public void remove(){
        THREAD_LOCAL.remove();
    }


}
