package com.konglingzhan.manager.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.konglingzhan.manager.dao.RoleMapper;
import com.konglingzhan.manager.entity.Role;
import com.konglingzhan.manager.entity.RoleMenu;
import com.konglingzhan.manager.common.RequestHolder;
import com.konglingzhan.manager.dao.RoleMenuMapper;
import com.konglingzhan.manager.service.RoleMenuService;
import com.konglingzhan.manager.util.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.*;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {
    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    @Transactional
    public void save(List<Integer> menuIds, int roleId) {
        Integer [] roleIds = {roleId};
        List<Integer> originAclIdList = roleMenuMapper.getMenuIdListByRoleIdList(Lists.newArrayList(roleIds));
        if(originAclIdList.size() == menuIds.size()){
            Set<Integer> originAclIdSet = Sets.newHashSet(originAclIdList);
            Set<Integer> aclIdSet = Sets.newHashSet(menuIds);
            originAclIdSet.removeAll(aclIdSet);
            if(CollectionUtils.isEmpty(originAclIdSet)){
                return;
            }
        }
        updateRoleAcls(roleId,menuIds);
    }

//    @Override
//    public void changeRoleAcls(int roleId, List<Integer> aclIdList) {
//        List<Integer> originAclIdList = roleMenuMapper.getAclIdListByRoleIdList(Lists.newArrayList(aclIdList));
//        if(originAclIdList.size() == aclIdList.size()){
//            Set<Integer> originAclIdSet = Sets.newHashSet(originAclIdList);
//            Set<Integer> aclIdSet = Sets.newHashSet(aclIdList);
//            originAclIdSet.removeAll(aclIdSet);
//            if(CollectionUtils.isEmpty(originAclIdSet)){
//                return;
//            }
//        }sys_dept
//        updateRoleAcls(roleId,aclIdList);
//    }

    public void updateRoleAcls(int roleId,List<Integer> menuIds){
        roleMenuMapper.deleteByRoleId(roleId);

        if(CollectionUtils.isEmpty(menuIds)){
            return;
        }

        List<RoleMenu> roleMenuList = Lists.newArrayList();
        for(Integer menuId: menuIds){
            RoleMenu roleMenu = RoleMenu.builder().role_id(roleId).menu_id(menuId).operator(UserUtil.getLoginUser().getUsername()).operate_ip("127.0.0.1").operate_time(new Date()).build();
            roleMenuList.add(roleMenu);
        }
        roleMenuMapper.batchInsert(roleMenuList);
//        throw new RuntimeException("函数执行有异常!");
    }
}
