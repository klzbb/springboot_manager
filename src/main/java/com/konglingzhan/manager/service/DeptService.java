package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.Dept;
import com.konglingzhan.manager.param.DeptVo;

import java.util.List;

public interface DeptService {
    int insert (Dept dept);

    List<Dept> queryAll();

    List<Dept> search(DeptVo vo);

    List<Dept> selectByName(String name);
}
