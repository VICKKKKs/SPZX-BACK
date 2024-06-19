package com.atguigu.spzx.model.vo.common;

import lombok.Data;

@Data
public class Result<T> {

    private Integer code;
    private String msg;

    private T data;

    // 返回数据
    public static <T> Result<T> build(T body, Integer code, String msg) {
        Result<T> result = new Result<>();
        result.setData(body);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setCode(200);
        result.setMsg("success");
        return result;
    }
    public static <T> Result fail(String message) {
        Result<T> result = new Result<>();
        result.setMsg(message);
        result.setCode(500);
        result.setData(null);
        return result;
    }

    public static <T> Result<T> build(T body,ResultCodeEnum resultCodeEnum) {
        Result<T> result   = new Result<>() ;

        result.setData(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMsg(result.getMsg());
        result.setData(null);
        return result;
    }
}
