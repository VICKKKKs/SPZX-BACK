package com.atguigu.spzx.user.mapper;

import com.atguigu.spzx.model.entity.user.UserInfo;

public interface UserInfoMapper {
    void insertUser(UserInfo userInfo);

    UserInfo selectByName(String username);

    void updateById(UserInfo userInfo);

}
