package com.atguigu.spzx.model.vo.common;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    SUCCESS(200 , "操作成功") ,
    LOGIN_ERROR(201 , "用户名或者密码错误"),
    COMMON_ERROR(500 , "发生了未知错误"),
    SOURCE_ERROR(400 , "请求资源错误"),
    ARGS_ERROR(403 , "参数错误"),
    NOTFOUND_ERROR(404 , "资源不存在");

    private Integer code ;      // 业务状态码
    private String message ;    // 响应消息

    private ResultCodeEnum(Integer code , String message) {
        this.code = code ;
        this.message = message ;
    }

}