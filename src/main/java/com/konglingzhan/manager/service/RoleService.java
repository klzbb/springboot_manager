package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.Role;
import com.konglingzhan.manager.param.RoleParam;

import java.util.List;

public interface RoleService {
    void insertRole(RoleParam param);

    void update(RoleParam param);

    List<Role> selectAllRole();

    List<Role> selectRoleByName(String name);

    int del(int id);
}
