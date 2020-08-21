package com.konglingzhan.manager.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.konglingzhan.manager.bean.RoleAcl;
import com.konglingzhan.manager.common.RequestHolder;
import com.konglingzhan.manager.dao.RoleAclMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class RoleAclServiceImpl implements RoleAclService{
    @Resource
    private RoleAclMapper roleAclMapper;

    @Override
    public void changeRoleAcls(int roleId, List<Integer> aclIdList) {
        List<Integer> originAclIdList = roleAclMapper.getAclIdListByRoleIdList(Lists.newArrayList(aclIdList));
        if(originAclIdList.size() == aclIdList.size()){
            Set<Integer> originAclIdSet = Sets.newHashSet(originAclIdList);
            Set<Integer> aclIdSet = Sets.newHashSet(aclIdList);
            originAclIdSet.removeAll(aclIdSet);
            if(CollectionUtils.isEmpty(originAclIdSet)){
                return;
            }
        }
        updateRoleAcls(roleId,aclIdList);
    }

    @Transactional
    public void updateRoleAcls(int roleId,List<Integer> aclIdList){
        roleAclMapper.deleteByRoleId(roleId);
        if(CollectionUtils.isEmpty(aclIdList)){
            return;
        }
        List<RoleAcl> roleAclList = Lists.newArrayList();
        for(Integer aclId: aclIdList){
            RoleAcl roleAcl = RoleAcl.builder().role_id(roleId).acl_id(aclId).operator(RequestHolder.getCurrentUser().getUsername()).operate_ip("127.0.0.1").operate_time(new Date()).build();
            roleAclList.add(roleAcl);
        }
        roleAclMapper.batchInsert(roleAclList);
    }
}
