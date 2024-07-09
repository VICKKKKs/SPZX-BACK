package com.atguigu.spzx.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.model.dto.user.UserLoginDto;
import com.atguigu.spzx.model.dto.user.UserRegisterDto;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.vo.user.UserInfoVo;
import com.atguigu.spzx.user.controller.UserInfoController;
import com.atguigu.spzx.user.mapper.UserInfoMapper;
import com.atguigu.spzx.user.service.UserInfoService;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserInfoServiceImpl implements UserInfoService {


    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public void register(UserRegisterDto userRegisterDto) {

        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String nickName = userRegisterDto.getNickName();
        String code = userRegisterDto.getCode();

        // 验证码是否正确
        Assert.notNull(code, "验证码为空");
        String codeCache = redisTemplate.opsForValue().get("phone:code:" + username);
        Assert.isTrue(code.equals(codeCache), "验证码不正确");

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

    @Override
    public Object login(UserLoginDto userLoginDto, String ipAddress) {
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();

        // 用户验证
        UserInfo userInfo = userInfoMapper.selectByName(username);
        Assert.notNull(userInfo, "用户名不存在");
        Assert.isTrue(userInfo.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes())), "密码不正确");

        // 用户登录成功时，根据token将用户信息存入redis，作后续身份认证使用
        String token = UUID.randomUUID().toString().replace("-", "");
        // 把userInfo转为JSON格式放到redis中
        redisTemplate.opsForValue().set("user:login:" + token, JSON.toJSONString(userInfo), 30, TimeUnit.DAYS);
        userInfo.setLastLoginTime(new Date());
        userInfo.setLastLoginIp(ipAddress);
        userInfoMapper.updateById(userInfo);
        return token;
    }

    @Override
    public UserInfoVo getCurrentUserInfo(String token) {
        // 获取用户的头像和昵称信息
        String userInfoJSON = redisTemplate.opsForValue().get("user:login:" + token);
        Assert.hasText(userInfoJSON, "登录过期");
        UserInfo userInfo = JSON.parseObject(userInfoJSON, UserInfo.class);

        String nickName = userInfo.getNickName();
        String avatar = userInfo.getAvatar();
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setNickName(nickName);
        userInfoVo.setAvatar(avatar);
        return userInfoVo;
    }

    @Override
    public UserInfo checkToken(String token) {
        String userInfoJSON = redisTemplate.opsForValue().get("user:login:" + token);
        UserInfo userInfo = null;
        if (StringUtils.hasText(userInfoJSON)){
            userInfo = JSON.parseObject(userInfoJSON, UserInfo.class);
        }
        return userInfo;
    }
}
