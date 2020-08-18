package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.Dept;
import com.konglingzhan.manager.bean.Role;
import com.konglingzhan.manager.common.RequestHolder;
import com.konglingzhan.manager.dao.RoleMapper;
import com.konglingzhan.manager.exception.ParamException;
import com.konglingzhan.manager.param.RoleParam;
import com.konglingzhan.manager.util.BeanValidator;
import org.assertj.core.util.Preconditions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Resource
    private RoleMapper roleMapper;

    @Override
    public void insertRole(RoleParam param) {
        BeanValidator.check(param);
        if(checkExist(param.getName(),param.getId())){
            throw new ParamException("角色名称已存在");
        }
        Role role = Role.builder().name(param.getName()).type(param.getType()).status(param.getStatus()).remark(param.getRemark()).build();
        role.setOperator(RequestHolder.getCurrentUser().getUsername());
        role.setOperate_ip("127.0.0.1");
        role.setOperate_time(new Date());
        roleMapper.insert(role);
    }

    @Override
    public void update(RoleParam param) {
        BeanValidator.check(param);
        if(checkExist(param.getName(),param.getId())){
            throw new ParamException("角色名称已存在");
        }
        Role before = roleMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before,"待更新的角色不存在");

        Role role = Role.builder().id(param.getId()).name(param.getName()).type(param.getType()).status(param.getStatus()).remark(param.getRemark()).build();
        role.setOperator(RequestHolder.getCurrentUser().getUsername());
        role.setOperate_ip("127.0.0.1");
        role.setOperate_time(new Date());
        roleMapper.update(role);
    }

    public boolean checkExist(String name , Integer id){
        return roleMapper.countByname(name,id) > 0;
    }
    @Override
    public List<Role> selectAllRole() {
        return roleMapper.selectAllRole();
    }

    @Override
    public List<Role> selectRoleByName(String name) {
        return roleMapper.selectRoleByName(name);
    }

    @Override
    public void del(int id) {
        roleMapper.del(id);
    }
}
