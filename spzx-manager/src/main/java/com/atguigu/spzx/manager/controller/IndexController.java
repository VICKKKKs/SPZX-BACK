package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.manager.service.ValidateCodeService;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.entity.system.SysUserAuthContextUtil;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.atguigu.spzx.model.vo.system.SysMenuVo;
import com.atguigu.spzx.model.vo.system.ValidateCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

//    @GetMapping(value = "/getUserInfo")
//    public Result<SysUser> getUserInfo(@RequestHeader(name = "token") String token) {
//        SysUser sysUser =  sysUserService.getUserInfo(token);
//        return Result.build(sysUser, ResultCodeEnum.SUCCESS);
//    }

    @GetMapping(value = "/getUserInfo")
    public Result<SysUser> getUserInfo() {      // 通过SysUserAuthContextUtil.get()来获取token
        return Result.ok(SysUserAuthContextUtil.get());
    }

    @Autowired
    private ValidateCodeService validateCodeService;

    @GetMapping(value = "/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode() {
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.ok(validateCodeVo);
    }

    @GetMapping(value = "/logout")
    public Result logout(@RequestHeader(name = "token") String token) {
        sysUserService.logout(token);
        return Result.ok(ResultCodeEnum.SUCCESS);
    }

    @Autowired
    private SysMenuService sysMenuService;

    // com.atguigu.spzx.system.controller#IndexController
    @GetMapping("/menus")
    public Result menus() {
        List<SysMenuVo> sysMenuVoList =  sysMenuService.findUserMenuList() ;
        return Result.build(sysMenuVoList , ResultCodeEnum.SUCCESS) ;
    }

}
