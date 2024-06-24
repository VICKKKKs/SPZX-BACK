package com.atguigu.spzx.model.vo.common;


import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String message;
    T data;

    // 返回数据
    public static <T> Result<T> build(T body, Integer code, String message) {
        Result<T> result = new Result<>();
        result.setData(body);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result ok(T data) {
        Result<T> result = new Result<>();
        result.setMessage("success");
        result.setCode(200);
        result.setData(data);
        return result;
    }

    public static <T> Result fail(String message) {
        Result<T> result = new Result<>();
        result.setMessage(message);
        result.setCode(500);
        result.setData(null);
        return result;
    }

    public static <T> Result build(T body, ResultCodeEnum resultCodeEnum) {
        Result<T> result = new Result<>();
        result.setMessage(resultCodeEnum.getMessage());
        result.setCode(resultCodeEnum.getCode());
        // result.setData(null);
        result.setData(body);
        return result;
    }
}
