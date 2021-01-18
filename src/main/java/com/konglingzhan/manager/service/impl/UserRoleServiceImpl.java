package com.konglingzhan.manager.service.impl;

import com.konglingzhan.manager.dto.UserRoleDto;
import com.konglingzhan.manager.entity.UserRole;
import com.konglingzhan.manager.common.RequestHolder;
import com.konglingzhan.manager.dao.UserRoleMapper;
import com.konglingzhan.manager.param.RoleUserParam;
import com.konglingzhan.manager.service.UserRoleService;
import com.konglingzhan.manager.util.BeanValidator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public void insert(RoleUserParam param) {
        BeanValidator.check(param);
        UserRole userRole = UserRole.builder().roleId(param.getRoleId()).userId(param.getUserId()).build();
        userRole.setOperator("admin");
        userRole.setOperateIp("127.0.0.1");
        userRole.setOperateTime(new Date());
        userRoleMapper.insert(userRole);
    }

    @Override
    public List<UserRoleDto> all() {
        return userRoleMapper.all();
    }

    @Override
    public List<Integer> getRoleIdListByUserId(int uid) {
        return userRoleMapper.getRoleIdListByUserId(uid);
    }

    @Override
    public void del(int id) {
        userRoleMapper.del(id);
    }

}
