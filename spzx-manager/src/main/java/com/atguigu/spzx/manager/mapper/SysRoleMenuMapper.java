package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.AssginMenuDto;

import java.util.List;
import java.util.Map;

public interface SysRoleMenuMapper {
    List<Long> selectSysRoleMenuById(Long roleId);

    void deletedByRoleId(Long roleId);

    void insertByMenuIdList(AssginMenuDto assginMenuDto);
}
