package com.atguigu.spzx.user.service;

import com.atguigu.spzx.model.dto.user.UserLoginDto;
import com.atguigu.spzx.model.dto.user.UserRegisterDto;
import com.atguigu.spzx.model.vo.user.UserInfoVo;

public interface UserInfoService {
    void register(UserRegisterDto userRegisterDto);

    Object login(UserLoginDto userLoginDto, String ipAddress);

    UserInfoVo getCurrentUserInfo(String token);
}
