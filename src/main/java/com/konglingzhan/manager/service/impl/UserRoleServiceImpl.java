package com.konglingzhan.manager.service.impl;

import com.konglingzhan.manager.bean.UserRole;
import com.konglingzhan.manager.common.RequestHolder;
import com.konglingzhan.manager.dao.UserRoleMapper;
import com.konglingzhan.manager.param.RoleUserParam;
import com.konglingzhan.manager.service.UserRoleService;
import com.konglingzhan.manager.util.BeanValidator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public void insert(RoleUserParam param) {
        BeanValidator.check(param);
        UserRole userRole = UserRole.builder().roleId(param.getRoleId()).userId(param.getUserId()).build();
        userRole.setOperator(RequestHolder.getCurrentUser().getUsername());
        userRole.setOperateIp("127.0.0.1");
        userRole.setOperateTime(new Date());
        userRoleMapper.insert(userRole);
    }
}
