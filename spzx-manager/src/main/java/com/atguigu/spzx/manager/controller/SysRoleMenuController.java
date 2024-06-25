package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.SysRoleMenuService;
import com.atguigu.spzx.model.dto.system.AssginMenuDto;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/admin/system/sysRoleMenu")
@CrossOrigin(allowCredentials = "true" , originPatterns = "*" , allowedHeaders = "*")
public class SysRoleMenuController {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @GetMapping(value = "/getSysRoleMenuIds/{roleId}")
    public Result<Map<String,Object>> getSysRoleMenuIds(@PathVariable("roleId") Long roleId) {
        Map<String ,Object> map = sysRoleMenuService.getSysRoleMenuIds(roleId);
        return Result.build(map, ResultCodeEnum.SUCCESS);
    }

    @PostMapping(value = "/doAssignMenuIdToSysRole")
    public Result doAssignMenuIdToSysRole(@RequestBody AssginMenuDto assginMenuDto) {
        sysRoleMenuService.doAssignMenuIdToSysRole(assginMenuDto);
        return Result.ok(null);
    }
}
