package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/system/index")
@CrossOrigin(allowCredentials = "true" , originPatterns = "*" , allowedHeaders = "*")
public class IndexController {

    @GetMapping("test1")
    public Result test1(){
        return Result.build("test1",200,"success");
    }

    @GetMapping("test2")
    public Result test2(){
        return Result.ok("test2");
    }

    @GetMapping("test3")
    public Result test3(){
        return Result.ok("test3");
    }

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/login")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto) throws Exception {
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.ok(loginVo);
    }

}
