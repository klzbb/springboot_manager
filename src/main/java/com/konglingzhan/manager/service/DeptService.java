package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.Dept;
import com.konglingzhan.manager.param.DeptParam;

import java.util.List;

public interface DeptService {
    int insert (DeptParam param);

    List<Dept> queryAll();

    List<Dept> search(DeptParam vo);

    List<Dept> selectByName(String name);

    boolean checkExist(Integer parentId,String deptName,Integer deptId);

    List<Dept> selectByPrimaryKey(Integer deptId);
}
