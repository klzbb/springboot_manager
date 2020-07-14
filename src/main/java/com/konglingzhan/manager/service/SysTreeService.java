package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.Dept;
import com.konglingzhan.manager.dao.DeptMapper;
import com.konglingzhan.manager.dto.DeptLevelDto;

import org.apache.commons.collections.MultiMap;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysTreeService {
    @Resource
    private DeptMapper deptMapper;

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
        } else {
            MultiMap levelDeptMap = ArrayListMultimap.create();
        }
    }
}
