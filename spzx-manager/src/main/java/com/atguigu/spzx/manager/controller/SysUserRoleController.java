package com.atguigu.spzx.manager.controller;


import com.atguigu.spzx.manager.service.SysUserRoleService;
import com.atguigu.spzx.model.dto.system.AssginRoleDto;
import com.atguigu.spzx.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/admin/system/sysUserRole")
@CrossOrigin(allowCredentials = "true" , originPatterns = "*" , allowedHeaders = "*")
public class SysUserRoleController {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @PostMapping(value = "doAssign")
    public Result doAssign(@RequestBody AssginRoleDto assginRoleDto) {
        sysUserRoleService.doAssign(assginRoleDto);
        return Result.ok(null);
    }

    @GetMapping("getUserRoleIdList/{currentUserId}")
    public Result<Map<String,Object>> getUserRoleIdList(@PathVariable Long currentUserId){
        Map<String,Object> map = sysUserRoleService.getUserRoleIdList(currentUserId);
        return Result.ok(map);

    }

}
