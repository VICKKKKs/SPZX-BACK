package com.atguigu.common.anno;

import com.atguigu.common.interceptor.UserTokenFeignInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({UserTokenFeignInterceptor.class})
public @interface EnableUserTokenFeignInterceptor {
}
