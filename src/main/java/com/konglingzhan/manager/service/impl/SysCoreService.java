package com.konglingzhan.manager.service.impl;

import com.konglingzhan.manager.dao.MenuMapper;
import com.konglingzhan.manager.entity.Acl;
import com.konglingzhan.manager.common.RequestHolder;
import com.konglingzhan.manager.dao.AclMapper;
import com.konglingzhan.manager.dao.RoleMenuMapper;
import com.konglingzhan.manager.dao.UserRoleMapper;
import com.konglingzhan.manager.entity.Menu;
import com.konglingzhan.manager.util.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysCoreService {
    @Resource
    private AclMapper aclMapper;

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    /**
     * 当前用户分配的权限点集合
     */
    public List<Menu> getCurrentUserAclList(){
        int userId = UserUtil.getLoginUser().getId();
        return getUserAclList(userId);
    }

    /**
     * 当前角色分配的权限点集合
     */
    public List<Menu> getRoleAclList(int roleId){
        ArrayList<Integer> roleList = new ArrayList<>();
        roleList.add(roleId);
        List<Integer> menuIdList = roleMenuMapper.getMenuIdListByRoleIdList(roleList);
        if(CollectionUtils.isEmpty(menuIdList)){
            return new ArrayList<>();
        }
        return menuMapper.getByIdList(menuIdList);
    }

    public List<Menu> getUserAclList(int userId){

        if(isSuperAdmin()){
            return  menuMapper.selectAll();
        }

        /**
         * 用户角色Ids
         */
        List<Integer> userRoleIdList = userRoleMapper.getRoleIdListByUserId(userId);
        if(CollectionUtils.isEmpty(userRoleIdList)){
            return new ArrayList<>();
        }
        /**
         * 根据用户角色ids获取用户权限点ids
         */
        List<Integer> userMenuIdList = roleMenuMapper.getMenuIdListByRoleIdList(userRoleIdList);
        if(CollectionUtils.isEmpty(userMenuIdList)){
            return new ArrayList<>();
        }

        return menuMapper.getByIdList(userMenuIdList);
    }

    public boolean isSuperAdmin(){
        return true;
    }
}
