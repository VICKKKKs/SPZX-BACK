package com.atguigu.spzx.user.controller;


import com.atguigu.spzx.model.dto.user.UserLoginDto;
import com.atguigu.spzx.model.dto.user.UserRegisterDto;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.user.UserInfoVo;
import com.atguigu.spzx.user.service.UserInfoService;
import com.atguigu.spzx.user.service.impl.IpUtil;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;

@RestController
@RequestMapping("api/user/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("register")
    public Result register(@RequestBody UserRegisterDto userRegisterDto) {
        userInfoService.register(userRegisterDto);
        return Result.ok(null);
    }


    @PostMapping("login")
    public Result login(@RequestBody UserLoginDto userLoginDto, HttpServletRequest request) {
        String ipAddress = IpUtil.getIpAddress(request);
        return Result.ok(userInfoService.login(userLoginDto,ipAddress));
    }

//    /api/user/userInfo/auth/getCurrentUserInfo
    @GetMapping("auth/getCurrentUserInfo")
    public Result<UserInfoVo> getCurrentUserInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        UserInfoVo userInfoVo = userInfoService.getCurrentUserInfo(token);
        return Result.ok(userInfoVo);
    }

    @GetMapping("/inner/checkToken/{token}")
    public UserInfo checkToken(@PathVariable String token){
        UserInfo userInfo = userInfoService.checkToken(token);
        return userInfo;
    }
}
