package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.Acl;
import com.konglingzhan.manager.common.RequestHolder;
import com.konglingzhan.manager.dao.AclMapper;
import com.konglingzhan.manager.dao.RoleAclMapper;
import com.konglingzhan.manager.dao.RoleUserMapper;
import org.assertj.core.internal.Lists;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SysCoreService {
    @Resource
    private AclMapper aclMapper;

    @Resource
    private RoleUserMapper roleUserMapper;

    @Resource
    private RoleAclMapper roleAclMapper;


    public List<Acl> getCurrentUserAclList(){
        int userId = RequestHolder.getCurrentUser().getId();
        return getUserAclList(userId);
    }

    public List<Acl> getRoleAclList(int roleId){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(roleId);
        List<Integer> aclIdList = roleAclMapper.getAclIdListByRoleIdList(list);
        if(CollectionUtils.isEmpty(aclIdList)){
            return new ArrayList<>();
        }
        return aclMapper.getByIdList(aclIdList);
    }

    public List<Acl> getUserAclList(int userId){

        if(isSuperAdmin()){
            return  aclMapper.selectAll();
        }

        List<Integer> userRoleIdList = roleUserMapper.getRoleIdListByUserId(userId);
        if(CollectionUtils.isEmpty(userRoleIdList)){
            return new ArrayList<>();
        }

        List<Integer> userAclIdList = roleAclMapper.getAclIdListByRoleIdList(userRoleIdList);
        if(CollectionUtils.isEmpty(userAclIdList)){
            return new ArrayList<>();
        }

        return aclMapper.getByIdList(userAclIdList);
    }

    public boolean isSuperAdmin(){
        return true;
    }
}
