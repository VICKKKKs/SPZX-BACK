package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/system/sysMenu")
@CrossOrigin(allowCredentials = "true" , originPatterns = "*" , allowedHeaders = "*")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("/findNodes/{parentId}")
    public Result<SysMenu> findNodes(@PathVariable(value = "parentId") Long parentId) {
        List<SysMenu> sysMenuList = sysMenuService.findNodes(parentId);
        return Result.ok(sysMenuList);
    }

    @PostMapping(value = "saveSysMenu")
    public Result<SysMenu> saveSysMenu(@RequestBody SysMenu sysMenu) {
        sysMenuService.saveSysMenu(sysMenu);
        return Result.ok(null);
    }

    @PutMapping(value = "updateSysMenu")
    public Result<SysMenu> updateSysMenu(@RequestBody SysMenu sysMenu) {
        sysMenuService.updateSysMenu(sysMenu);
        return Result.ok(null);
    }
}
