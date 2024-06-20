package com.atguigu.spzx.model.vo.common;

import lombok.Data;

@Data
public class Result<T> {

    private Integer code;
    private String message;

    private T data;

    // 返回数据
    public static <T> Result<T> build(T body, Integer code, String message) {
        Result<T> result = new Result<>();
        result.setData(body);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setCode(200);
        result.setMessage("success");
        return result;
    }
    public static <T> Result fail(String message) {
        Result<T> result = new Result<>();
        result.setMessage(message);
        result.setCode(500);
        result.setData(null);
        return result;
    }

    public static <T> Result<T> build(T body,ResultCodeEnum resultCodeEnum) {
        Result<T> result   = new Result<>() ;

        result.setData(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        result.setData(null);
        return result;
    }
}
