package com.atguigu.common.anno;


import com.atguigu.common.config.WebMvcConfiguration;
import com.atguigu.common.exception.GlobalExceptionHandler;
import com.atguigu.common.interceptor.UserLoginAuthInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({UserLoginAuthInterceptor.class, WebMvcConfiguration.class})
public @interface EnableUserLoginAuthInterceptor {
}
