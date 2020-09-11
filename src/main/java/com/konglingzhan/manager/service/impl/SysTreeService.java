package com.konglingzhan.manager.service.impl;

import com.konglingzhan.manager.dto.MenuDto;
import com.konglingzhan.manager.entity.Acl;
import com.konglingzhan.manager.entity.Dept;
import com.konglingzhan.manager.dao.AclMapper;
import com.konglingzhan.manager.dao.MenuMapper;
import com.konglingzhan.manager.dao.DeptMapper;
import com.konglingzhan.manager.dto.AclDto;
import com.konglingzhan.manager.dto.AclModuleLevelDto;
import com.konglingzhan.manager.dto.DeptLevelDto;

import com.konglingzhan.manager.entity.Menu;
import com.konglingzhan.manager.util.LevelUtil;


import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SysTreeService {
    @Resource
    private DeptMapper deptMapper;

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private SysCoreService sysCoreService;

    @Resource
    private AclMapper aclMapper;

    /**
     * 查询当前角色权限树
     */
    public List<MenuDto> roleTree(int roleId){
        // 当前用户分配的权限点
        List<Menu> userAclList = sysCoreService.getCurrentUserAclList();
        // 当前角色分配的权限点
        List<Menu> roleAclList = sysCoreService.getRoleAclList(roleId);
        // 当前系统所有权限点
        List<MenuDto> menuDtoList = new ArrayList<>();
        Set<Integer> userAclIdSet = userAclList.stream().map(acl -> acl.getId()).collect(Collectors.toSet());
        Set<Integer> roleAclIdSet = roleAclList.stream().map(acl -> acl.getId()).collect(Collectors.toSet());

        List<Menu> allMenuList = menuMapper.selectAll();
        allMenuList.stream().forEach(menu -> {
            MenuDto dto = MenuDto.adapt(menu);
            if(userAclIdSet.contains(menu.getId())){
                dto.setHasAcl(true);
            }
            if(roleAclIdSet.contains(menu.getId())){
                dto.setChecked(true);
            }
            menuDtoList.add(dto);
        });
        return aclListToTreeList(menuDtoList);
    }
    public List<MenuDto> aclListToTreeList(List<MenuDto> menuDtoList){

        if(CollectionUtils.isEmpty(menuDtoList)){
            return new ArrayList<>();
        }

        List<MenuDto> rootList = new ArrayList<>();
        List<AclModuleLevelDto> aclModuleLevelList = aclModuleTree();
        MultiMap moduleIdAclMap = new MultiValueMap();
        for(MenuDto dto : menuDtoList){
            if(dto.getStatus() == 1){
                moduleIdAclMap.put(dto.getLevel(),dto);
            }
            if(LevelUtil.ROOT.equals(dto.getLevel())){
                rootList.add(dto);
            }
        }

        // 按照seq从小到大排序
        Collections.sort(rootList, aclSeqComparator);

        // 递归生成树
        bindAclsWithOrder(rootList,LevelUtil.ROOT,moduleIdAclMap);
        return rootList;
    }
    public void bindAclsWithOrder( List<MenuDto> aclModuleLevelList,String level,MultiMap moduleIdAclMap){
        if(CollectionUtils.isEmpty(aclModuleLevelList)){
            return;
        }
        for(MenuDto dto: aclModuleLevelList){
            // 处理当前层级的数据（level算法）
            String nextLevel = LevelUtil.calculateLevel(level,dto.getId());
            System.out.println("nextLevel=" + nextLevel);
            // 处理下一层
            List<MenuDto> tempList = (List<MenuDto>) moduleIdAclMap.get(nextLevel);
            if(!CollectionUtils.isEmpty(tempList)){
                // 排序
                Collections.sort(tempList,aclSeqComparator);
                // 设置下一层部门
                dto.setChildren(tempList);
                // 进入到下一层处理
                bindAclsWithOrder(tempList,nextLevel,moduleIdAclMap);
            }
        }
    }

    /**
     * 菜单树状结构
     **/
    public List<AclModuleLevelDto> aclModuleTree(){
        List<Menu> list = menuMapper.selectAll();
        List<AclModuleLevelDto> dtoList = new ArrayList<>();
        for(Menu menu : list){
            AclModuleLevelDto dto = AclModuleLevelDto.adapt(menu);
            dtoList.add(dto);
        }
        return aclModuleListToTree(dtoList);
    }

    public List<AclModuleLevelDto> aclModuleListToTree(List<AclModuleLevelDto> dtoList){
        if(CollectionUtils.isEmpty(dtoList)){
            return new ArrayList<>();
        }
        MultiMap levelAclModuleMap = new MultiValueMap();
        List<AclModuleLevelDto> rootList = new ArrayList<>();
        for (AclModuleLevelDto dto : dtoList){
            levelAclModuleMap.put(dto.getLevel(),dto);
            if(LevelUtil.ROOT.equals(dto.getLevel())){
                rootList.add(dto);
            }
        }

        // 按照seq从小到大排序
        Collections.sort(rootList, aclModuleSeqComparator);

        // 递归生成树
        transformAclModuleTree(rootList,LevelUtil.ROOT, levelAclModuleMap);
        System.out.println("levelDeptMap=" + levelAclModuleMap);
        return rootList;
    }

    public void transformAclModuleTree(List<AclModuleLevelDto> dtoList,String level, MultiMap levelAclModuleMap){
        for(int i = 0;i<dtoList.size(); i++){
            // 遍历该层的每个元素
            AclModuleLevelDto dto = dtoList.get(i);
            // 处理当前层级的数据（level算法）
            String nextLevel = LevelUtil.calculateLevel(level,dto.getId());
            System.out.println("nextLevel=" + nextLevel);
            // 处理下一层
            List<AclModuleLevelDto> tempList = (List<AclModuleLevelDto>) levelAclModuleMap.get(nextLevel);
            if(!CollectionUtils.isEmpty(tempList)){
                // 排序
                Collections.sort(tempList,aclModuleSeqComparator);
                // 设置下一层部门
                dto.setChildren(tempList);
                // 进入到下一层处理
                transformAclModuleTree(tempList,nextLevel,levelAclModuleMap);
            }

        }
    }
    public List<DeptLevelDto> deptTree(){
        List<Dept> deptList = deptMapper.selectAll();
        List<DeptLevelDto> dtoList = new ArrayList<>();
        for(Dept dept: deptList){
            DeptLevelDto dto = DeptLevelDto.adapt(dept);
            dtoList.add(dto);
        }
        return deptListToTree(dtoList);
    }

    public List<DeptLevelDto> deptListToTree(List<DeptLevelDto> deptLevelList){
        if(CollectionUtils.isEmpty(deptLevelList)){
            return new ArrayList<>();
        }
        // level => {dept1,dept2,...}
        MultiMap levelDeptMap = new MultiValueMap();
        List<DeptLevelDto> rootList = new ArrayList<>();
        for (DeptLevelDto dto : deptLevelList){
            levelDeptMap.put(dto.getLevel(),dto);
            if(LevelUtil.ROOT.equals(dto.getLevel())){
                rootList.add(dto);
            }
            System.out.println(rootList);
        }

        // 按照seq从小到大排序
        Collections.sort(rootList, new Comparator<DeptLevelDto>() {
            @Override
            public int compare(DeptLevelDto o1, DeptLevelDto o2) {
                return o1.getSeq() - o2.getSeq();
            }
        });

        // 递归生成树
        transformDeptTree(deptLevelList,LevelUtil.ROOT, levelDeptMap);
        System.out.println("levelDeptMap=" + levelDeptMap);
        return rootList;
    }

    public void transformDeptTree(List<DeptLevelDto> deptLevelList, String level, MultiMap levelDeptMap){
        for(int i = 0;i<deptLevelList.size(); i++){
            // 遍历该层的每个元素
            DeptLevelDto deptLevelDto = deptLevelList.get(i);
            // 处理当前层级的数据
            String nextLevel = LevelUtil.calculateLevel(level,deptLevelDto.getId());
            System.out.println("nextLevel=" + nextLevel);
            // 处理下一层
            List<DeptLevelDto> tempDeptList = (List<DeptLevelDto>) levelDeptMap.get(nextLevel);
            if(!CollectionUtils.isEmpty(tempDeptList)){
                // 排序
                Collections.sort(tempDeptList,deptSeqComparator);
                // 设置下一层部门
                deptLevelDto.setDeptList(tempDeptList);
                // 进入到下一层处理
                transformDeptTree(tempDeptList,nextLevel,levelDeptMap);
            }

        }
    }

    public Comparator<DeptLevelDto> deptSeqComparator = new Comparator<DeptLevelDto>() {
        @Override
        public int compare(DeptLevelDto o1, DeptLevelDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

    public Comparator<AclModuleLevelDto> aclModuleSeqComparator = new Comparator<AclModuleLevelDto>() {
        @Override
        public int compare(AclModuleLevelDto o1, AclModuleLevelDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

    public Comparator<MenuDto> aclSeqComparator = new Comparator<MenuDto>() {
        @Override
        public int compare(MenuDto o1, MenuDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };
}
