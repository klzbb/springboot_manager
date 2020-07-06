package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.Role;

import java.util.List;

public interface RoleService {
    int insertRole(Role role);

    List<Role> selectAllRole();

    List<Role> selectRoleByname(String name);


}
