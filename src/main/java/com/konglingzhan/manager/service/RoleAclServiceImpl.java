package com.konglingzhan.manager.service;

import com.google.common.collect.Lists;
import com.konglingzhan.manager.dao.RoleAclMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleAclServiceImpl implements RoleAclService{
    @Resource
    private RoleAclMapper roleAclMapper;

    @Override
    public void changeRoleAcls(int roleId, List<Integer> aclIdList) {
        List<Integer> originAclIdList = roleAclMapper.getAclIdListByRoleIdList(aclIdList);
//        List<Integer> originAclIdList = roleAclMapper.getAclIdListByRoleIdList(Lists.newArrayList(aclIdList));
    }
}
