package com.atguigu.common.exception;

import com.atguigu.spzx.model.vo.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception e){
        System.out.println("通用异常提醒：");
        e.printStackTrace();
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result illegalArgumentExceptionHandler(IllegalArgumentException e){
        System.out.println("参数异常处理：");
        e.printStackTrace();
        return Result.fail(e.getMessage());
    }
}
