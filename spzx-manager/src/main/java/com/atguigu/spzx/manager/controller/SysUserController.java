package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/system/sysUser")
@CrossOrigin(allowCredentials = "true" , originPatterns = "*" , allowedHeaders = "*")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping(value = "/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<SysUser>> findByPage(SysUserDto sysUserDto,
                                                @PathVariable Integer pageNum,
                                                @PathVariable Integer pageSize) {
        PageInfo<SysUser> pageInfo = sysUserService.findByPage(sysUserDto,pageNum,pageSize );

        return Result.ok(pageInfo);
    }

    @PostMapping(value = "/saveSysUser")
    public Result saveSysUser(@RequestBody SysUser sysUser) {
        sysUserService.saveSysUser(sysUser);
//        System.out.println("ok");
        return Result.ok(null);
//        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @PutMapping(value = "/updateSysUser")
    public Result updateSysUser(@RequestBody SysUser sysUser) {
//        System.out.println("good");
        sysUserService.updateSysUser(sysUser);
        return Result.ok(null);
    }

    @DeleteMapping("/deleteById/{userId}")
    public Result deleteById(@PathVariable Long userId) {
        sysUserService.deleteById(userId);
        return Result.ok(null);
    }

}
