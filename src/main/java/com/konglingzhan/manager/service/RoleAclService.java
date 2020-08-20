package com.konglingzhan.manager.service;

import java.util.List;

public interface RoleAclService {
    void changeRoleAcls(int roleId, List<Integer> aclIdList);
}
