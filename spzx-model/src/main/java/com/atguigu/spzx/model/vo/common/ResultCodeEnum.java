package com.atguigu.spzx.model.vo.common;

import lombok.Getter;

// 只能得到getter方法，因为是enum，不能修改值
@Getter
public enum ResultCodeEnum {

    SUCCESS(200, "操作成功"),
    LOGIN_ERROR(201, "用户名或者密码错误"),
    COMMON_ERROR(500, "发生了未知错误"),
    SOURCE_ERROR(400, "请求资源错误"),
    ARGS_ERROR(403, "参数错误"),
    NOTFOUND_ERROR(404, "资源不存在");

    private int code;
    private String msg;

    private ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
