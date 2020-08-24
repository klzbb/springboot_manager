package com.konglingzhan.manager.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.konglingzhan.manager.bean.RoleMenu;
import com.konglingzhan.manager.common.RequestHolder;
import com.konglingzhan.manager.dao.RoleMenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {
    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public void changeRoleAcls(int roleId, List<Integer> aclIdList) {
        List<Integer> originAclIdList = roleMenuMapper.getAclIdListByRoleIdList(Lists.newArrayList(aclIdList));
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
        roleMenuMapper.deleteByRoleId(roleId);
        if(CollectionUtils.isEmpty(aclIdList)){
            return;
        }
        List<RoleMenu> roleMenuList = Lists.newArrayList();
        for(Integer aclId: aclIdList){
            RoleMenu roleMenu = RoleMenu.builder().role_id(roleId).menu_id(aclId).operator(RequestHolder.getCurrentUser().getUsername()).operate_ip("127.0.0.1").operate_time(new Date()).build();
            roleMenuList.add(roleMenu);
        }
        roleMenuMapper.batchInsert(roleMenuList);
    }
}
