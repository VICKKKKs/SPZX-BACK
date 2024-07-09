package com.atguigu.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.entity.user.UserInfoAuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserLoginAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("前置拦截器");
        String method = request.getMethod();
        System.out.println(method);

        if (method.equals("OPTIONS")) {
            return true;
        }
        // 判断用户是否已经登录，如果没有登录则跳转到登录页面
        String userInfoJson = request.getHeader("userInfoJson");
//        Assert.hasText(userInfoJson,"用户未登录");
        if (StringUtils.hasText(userInfoJson)) {
            UserInfo userInfo = JSON.parseObject(userInfoJson, UserInfo.class);
            // 将认证结果传递给接口
            UserInfoAuthContextUtil.set(userInfo);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
