package com.atguigu.spzx.user.service.impl;

import com.atguigu.spzx.model.dto.user.UserRegisterDto;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.user.controller.UserInfoController;
import com.atguigu.spzx.user.mapper.UserInfoMapper;
import com.atguigu.spzx.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

@Service
public class UserInfoServiceImpl implements UserInfoService {


    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    @Override
    public void register(UserRegisterDto userRegisterDto) {

        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String nickName = userRegisterDto.getNickName();
        String code = userRegisterDto.getCode();

        // 验证码是否正确
        Assert.notNull(code,"验证码为空");
        String codeCache = redisTemplate.opsForValue().get("phone:code:" + username);
        Assert.isTrue(code.equals(codeCache),"验证码不正确");

        // 保存数据
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userInfo.setNickName(nickName);
        userInfo.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        userInfo.setSex(0);
        userInfo.setStatus(1);
        userInfoMapper.insertUser(userInfo);
        //删除验证码
        redisTemplate.delete("phone:code:" + username);
    }
}
