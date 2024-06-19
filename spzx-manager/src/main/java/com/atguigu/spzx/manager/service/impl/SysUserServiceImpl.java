package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.SysUserMapper;
import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public LoginVo login(LoginDto loginDto) {
        // 根据用户名查询用户
        SysUser sysUser = sysUserMapper.selectSysUserByName(loginDto.getUsername());
        if(sysUser==null){
            throw new RuntimeException("用户名或密码为空");
        }

        // 验证密码是否正确
        String loginDtoPassword = loginDto.getPassword();
        String password = DigestUtils.md5DigestAsHex(loginDtoPassword.getBytes());

        // 校验通过，生成token信息
        LoginVo loginVo = new LoginVo();
        return loginVo;
    }
}
