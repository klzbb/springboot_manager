package com.konglingzhan.manager.service;

import java.util.List;

public interface RoleMenuService {

    /**
     * 保存角色权限菜单
     **/
    void save(List<Integer> menuIds,int roleId);

    void delByRoleId(int roleId);
}
