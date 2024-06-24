package com.atguigu.spzx.manager.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.manager.mapper.SysUserMapper;
import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.atguigu.util.MyAssert;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    RedisTemplate<String, String> redisTemplate;


    @SneakyThrows
    @Override
    public LoginVo login(LoginDto loginDto) throws Exception {

        String captcha = loginDto.getCaptcha();
        String codeKey = loginDto.getCodeKey();


        String username = loginDto.getUserName();
        MyAssert.hasText(username,"用户名不能为空");

        String password = loginDto.getPassword();
        MyAssert.hasText(password,"密码不能为空");

        // 根据用户名查询用户
        SysUser sysUser = sysUserMapper.selectSysUserByName(username);
        MyAssert.notNull(sysUser,"用户为空");


        // 验证密码是否正确
        String InputPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        String passwordDb = sysUser.getPassword();
        MyAssert.isTrue(passwordDb.equals(InputPassword),"密码错误");

        // 校验验证码
        Assert.hasText(captcha,"验证码不能为空");
        String redisCaptcha = redisTemplate.opsForValue().get("user:login:validatecode:" + codeKey);
        Assert.hasText(redisCaptcha,"验证码过期");
        Assert.isTrue(redisCaptcha.equals(captcha),"验证码错误");

        // 生成令牌，保存数据到Redis中
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set("user:login:" + token, JSON.toJSONString(sysUser), 30, TimeUnit.MINUTES);

        // 构建响应结果对象
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setRefresh_token("");
        //返回
        return loginVo;
    }

    @Override
    public SysUser getUserInfo(String token) {
        String userJson =  redisTemplate.opsForValue().get("user:login:" + token);
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);

        return sysUser;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login:" + token);
    }

    @Override
    public PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> sysUserList = sysUserMapper.findByPage(sysUserDto);
        PageInfo pageInfo = new PageInfo(sysUserList);
        return pageInfo;
    }

    @Override
    public void saveSysUser(SysUser sysUser) {
        String password = DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes());
        sysUser.setPassword(password);
        sysUser.setStatus(1);
        sysUserMapper.insertSysUser(sysUser);
       /* String password = sysUser.getPassword();
        String md5 = DigestUtils.md5DigestAsHex(password.getBytes());
        sysUser.setPassword(md5);
        sysUser.setStatus(1);
        sysUserMapper.insertSysUser(sysUser);*/
    }

    @Override
    public void updateSysUser(SysUser sysUser) {
        sysUserMapper.updateSysUser(sysUser);
    }

    @Override
    public void deleteById(Long userId) {
        sysUserMapper.deleteByid(userId);
    }
}
