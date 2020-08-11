package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.RoleUser;
import com.konglingzhan.manager.dao.RoleUserMapper;
import com.konglingzhan.manager.param.RoleUserParam;
import com.konglingzhan.manager.util.BeanValidator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class RoleUserServiceImpl implements RoleUserService {
    @Resource
    private RoleUserMapper roleUserMapper;

    @Override
    public void insert(RoleUserParam param) {
        BeanValidator.check(param);
        RoleUser roleUser = RoleUser.builder().roleId(param.getRoleId()).userId(param.getUserId()).build();
        roleUser.setOperator("system");
        roleUser.setOperateIp("127.0.0.1");
        roleUser.setOperateTime(new Date());
        roleUserMapper.insert(roleUser);
    }
}
