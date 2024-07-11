package com.atguigu.common.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collection;

public class UserTokenFeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 获取当前请求的上下文
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        // 从请求头中获取 "userInfoJson"
        String userInfoJson = request.getHeader("userInfoJson");
        if (userInfoJson != null) {
            // 将 "userInfoJson" 添加到 Feign 请求的头信息中
            requestTemplate.header("userInfoJson", userInfoJson);
        }
    }
}
