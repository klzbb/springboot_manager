package com.konglingzhan.manager.service;

import java.util.List;

public interface RoleMenuService {
//    void changeRoleAcls(int roleId, List<Integer> aclIdList);

    /**
     * 保存角色权限菜单
     **/
    void save(List<Integer> menuIds,int roleId);
}
