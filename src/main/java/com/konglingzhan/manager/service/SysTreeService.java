package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.Acl;
import com.konglingzhan.manager.bean.AclModule;
import com.konglingzhan.manager.bean.Dept;
import com.konglingzhan.manager.dao.AclMapper;
import com.konglingzhan.manager.dao.AclModuleMapper;
import com.konglingzhan.manager.dao.DeptMapper;
import com.konglingzhan.manager.dto.AclDto;
import com.konglingzhan.manager.dto.AclModuleLevelDto;
import com.konglingzhan.manager.dto.DeptLevelDto;

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
    private AclModuleMapper aclModuleMapper;

    @Resource
    private SysCoreService sysCoreService;

    @Resource
    private AclMapper aclMapper;

    /**
     * 角色权限树
     */
    public List<AclModuleLevelDto> roleTree(int roleId){
        // 当前用户分配的权限点
        List<Acl> userAclList = sysCoreService.getCurrentUserAclList();
        // 当前角色分配的权限点
        List<Acl> roleAclList = sysCoreService.getRoleAclList(roleId);
        // 当前系统所有权限点
        List<AclDto> aclDtoList = new ArrayList<>();

        Set<Integer> userAclIdSet = userAclList.stream().map(acl -> acl.getId()).collect(Collectors.toSet());
        Set<Integer> roleAclIdSet = roleAclList.stream().map(acl -> acl.getId()).collect(Collectors.toSet());

        List<Acl> allAclList = aclMapper.selectAll();
        for(Acl acl : allAclList){
            AclDto dto = AclDto.adapt(acl);
            if(userAclIdSet.contains(acl.getId())){
                dto.setHasAcl(true);
            }
            if(roleAclIdSet.contains(acl.getId())){
                dto.setChecked(true);
            }
            aclDtoList.add(dto);
        }
        return aclListToTreeList(aclDtoList);
    }

    public List<AclModuleLevelDto> aclListToTreeList(List<AclDto> aclDtoList){
        if(CollectionUtils.isEmpty(aclDtoList)){
            return new ArrayList<>();
        }

        List<AclModuleLevelDto> aclModuleLevelList = aclModuleTree();
        MultiMap moduleIdAclMap = new MultiValueMap();
        for(Acl acl : aclDtoList){
            if(acl.getStatus() == 1){
                moduleIdAclMap.put(acl.getAcl_module_id(),acl);
            }
        }
        bindAclsWithOrder(aclModuleLevelList,moduleIdAclMap);
        return aclModuleLevelList;
    }

    public void bindAclsWithOrder( List<AclModuleLevelDto> aclModuleLevelList,MultiMap moduleIdAclMap){
        if(CollectionUtils.isEmpty(aclModuleLevelList)){
            return;
        }
        for(AclModuleLevelDto dto: aclModuleLevelList){
            List<AclDto> aclDtoList = (List<AclDto>) moduleIdAclMap.get(dto.getId());
            if(!CollectionUtils.isEmpty(aclDtoList)){
                Collections.sort(aclDtoList,aclSeqComparator);
                dto.setAclList(aclDtoList);
            }
            bindAclsWithOrder(dto.getAclModuleList(),moduleIdAclMap);
        }
    }

    public List<AclModuleLevelDto> aclModuleTree(){
        List<AclModule> list = aclModuleMapper.selectAll();
        List<AclModuleLevelDto> dtoList = new ArrayList<>();
        for(AclModule aclModule: list){
            AclModuleLevelDto dto = AclModuleLevelDto.adapt(aclModule);
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
            // 处理当前层级的数据
            String nextLevel = LevelUtil.calculateLevel(level,dto.getId());
            System.out.println("nextLevel=" + nextLevel);
            // 处理下一层
            List<AclModuleLevelDto> tempList = (List<AclModuleLevelDto>) levelAclModuleMap.get(nextLevel);
            if(!CollectionUtils.isEmpty(tempList)){
                // 排序
                Collections.sort(tempList,aclModuleSeqComparator);
                // 设置下一层部门
                dto.setAclModuleList(tempList);
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

    public Comparator<AclDto> aclSeqComparator = new Comparator<AclDto>() {
        @Override
        public int compare(AclDto o1, AclDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };
}
