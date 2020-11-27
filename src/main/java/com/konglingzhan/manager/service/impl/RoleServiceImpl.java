package com.konglingzhan.manager.service.impl;

import com.konglingzhan.manager.dao.MenuMapper;
import com.konglingzhan.manager.dao.RoleMenuMapper;
import com.konglingzhan.manager.entity.Role;
import com.konglingzhan.manager.common.RequestHolder;
import com.konglingzhan.manager.dao.RoleMapper;
import com.konglingzhan.manager.common.exception.ParamException;
import com.konglingzhan.manager.param.RoleParam;
import com.konglingzhan.manager.service.RoleMenuService;
import com.konglingzhan.manager.service.RoleService;
import com.konglingzhan.manager.util.BeanValidator;
import com.konglingzhan.manager.util.StringUtil;
import com.konglingzhan.manager.util.UserUtil;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private RoleMenuService roleMenuService;

    @Override
    @Transactional
    public void insertRole(RoleParam param) {
        // 业务校验
        if(checkExist(param.getName(),param.getId())){
            throw new ParamException("角色名称已存在");
        }
        // 插入role
        Role role = Role.builder().name(param.getName()).type(param.getType()).status(param.getStatus()).remark(param.getRemark()).build();
        role.setOperator("admin");
        role.setOperate_ip("127.0.0.1");
        role.setOperate_time(new Date());
        roleMapper.insert(role);

        // 角色、权限关联
        List<Integer> menuIds = StringUtil.splitToListInt(param.getMenuIds());
        roleMenuService.save(menuIds,role.getId());

    }

    @Override
    @Transactional
    public void update(RoleParam param) {
        BeanValidator.check(param);
        Role before = roleMapper.selectByPrimaryKey(param.getId());
        Objects.requireNonNull(before, "待更新的角色不存在");

        if(!param.getName().equals(before.getName())){
            if(checkExist(param.getName(),param.getId())){
                throw new ParamException("角色名称已存在");
            }
        }

        Role role = Role.builder().id(param.getId()).name(param.getName()).type(param.getType()).status(param.getStatus()).remark(param.getRemark()).build();
        role.setOperator(UserUtil.getLoginUser().getUsername());
        role.setOperate_ip("127.0.0.1");
        role.setOperate_time(new Date());
        roleMapper.update(role);

        // 角色、权限关联
        List<Integer> menuIds = StringUtil.splitToListInt(param.getMenuIds());
        roleMenuService.save(menuIds,role.getId());
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
    public void del(int roleId) {
        roleMapper.del(roleId);
        roleMenuMapper.deleteByRoleId(roleId);
    }
}
