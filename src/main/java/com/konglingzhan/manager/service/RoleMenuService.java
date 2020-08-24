package com.konglingzhan.manager.service;

import java.util.List;

public interface RoleMenuService {
    void changeRoleAcls(int roleId, List<Integer> aclIdList);
}
