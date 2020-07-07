package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.Role;
import com.konglingzhan.manager.dao.RoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Resource
    private RoleMapper roleMapper;

    @Override
    public int insertRole(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public List<Role> selectAllRole() {
        return roleMapper.selectAllRole();
    }

    @Override
    public List<Role> selectRoleByname(String name) {
        return roleMapper.selectRoleByname(name);
    }
}
