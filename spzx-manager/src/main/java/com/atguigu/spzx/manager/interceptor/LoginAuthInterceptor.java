package com.atguigu.spzx.manager.interceptor;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.entity.system.SysUserAuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Struct;

@Component
public class LoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("前置拦截器");
        String method = request.getMethod();
        System.out.println(method);
        if (method.equals("OPTIONS")) {
            return true;
        }
        // 判断用户是否已经登录，如果没有登录则跳转到登录页面
        String token = request.getHeader("token");
        String userJson = redisTemplate.opsForValue().get("user:login:" + token);
//        Assert.hasText(userJson,"用户未登录");
        if (StringUtils.hasText(userJson)) {
            SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
            // 将认证结果传递给接口
            SysUserAuthContextUtil.set(sysUser);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
