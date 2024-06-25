package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper {

    public static List<SysMenu> buildTree(List<SysMenu> allNodes) {
        List<SysMenu> treeNodes = new ArrayList<>();
        for (SysMenu sysMenu : allNodes) {
            if (sysMenu.getParentId().longValue() == 0) {
            treeNodes.add(findChildren(sysMenu,allNodes));
            }
        }
        return treeNodes;
    }

    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> allNodes) {
        for (SysMenu sysMenuChild : allNodes) {
            if(sysMenuChild.getParentId().longValue() == sysMenu.getId().longValue()) {
                sysMenu.getChildren().add(findChildren(sysMenuChild, allNodes));
            }
        }
        return sysMenu;
    }
}
