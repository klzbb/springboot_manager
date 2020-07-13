package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.Dept;
import com.konglingzhan.manager.dao.DeptMapper;
import com.konglingzhan.manager.param.DeptVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class DeptServiceImpl implements DeptService{
    @Resource
    private DeptMapper deptMapper;

    @Override
    public int insert(Dept dept) {
        return deptMapper.insert(dept);
    }

    @Override
    public List<Dept> queryAll() {
        return deptMapper.selectAll();
    }

    @Override
    public List<Dept> search(DeptVo vo) {
        return deptMapper.search(vo);
    }

    @Override
    public List<Dept> selectByName(String name) {
        return deptMapper.selectByName(name);
    }
}
