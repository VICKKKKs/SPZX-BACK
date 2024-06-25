package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.vo.system.SysMenuVo;

import java.util.ArrayList;
import java.util.List;

public class MenuVoHelper {
    public static List<SysMenuVo> buildTree(List<SysMenu> sysMenusTree) {
        List<SysMenuVo> voTree = new ArrayList<>();

        for (SysMenu sysMenu : sysMenusTree) {
            SysMenuVo vo = new SysMenuVo();
            vo.setName(sysMenu.getComponent());
            vo.setTitle(sysMenu.getTitle());
            // 转换vo
            if(sysMenu.getChildren()!=null&&sysMenu.getChildren().size()>0){
            vo.setChildren(buildTree(sysMenu.getChildren()));
            }
            voTree.add(vo);
        }
        return null;
    }
}
