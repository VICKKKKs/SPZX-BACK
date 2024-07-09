package com.atguigu.spzx.model.entity.user;

public class UserInfoAuthContextUtil {

    private static final ThreadLocal<UserInfo> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(UserInfo userInfo){
        THREAD_LOCAL.set(userInfo);
    }

    public static UserInfo get(){
        return THREAD_LOCAL.get();
    }

    public void remove(){
        THREAD_LOCAL.remove();
    }


}
