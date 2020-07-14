package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.Dept;
import com.konglingzhan.manager.dao.DeptMapper;
import com.konglingzhan.manager.exception.ParamException;
import com.konglingzhan.manager.param.DeptParam;
import com.konglingzhan.manager.util.BeanValidator;
import com.konglingzhan.manager.util.LevelUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@Service
public class DeptServiceImpl implements DeptService{
    @Resource
    private DeptMapper deptMapper;

    @Override
    public boolean checkExist(Integer parentId, String deptName, Integer deptId) {
        List<Dept> list = deptMapper.selectByName(deptName);
        if(list.size() == 0){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<Dept> selectByPrimaryKey(Integer deptId) {
        return null;
    }

    private String getLevel(Integer deptId) {
        List<Dept> list = deptMapper.selectByPrimaryKey(deptId);
        if(list.size() == 0){
            return null;
        }
        return list.get(0).getLevel();
    }

    @Override
    public int insert(DeptParam param) {
        BeanValidator.check(param);
        if(checkExist(param.getParentId(),param.getName(),param.getId())){
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        Dept dept = Dept.builder().name(param.getName()).parent_id(param.getParentId()).seq(param.getSeq()).remark(param.getRemark()).build();
        String level = LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId());
        dept.setLevel(level);
        dept.setOperator("system"); // todo
        dept.setOperate_ip("127.0.0.1"); // todo
        dept.setOperateTime(new Date());
        return deptMapper.insert(dept);
    }

    @Override
    public List<Dept> queryAll() {
        return deptMapper.selectAll();
    }

    @Override
    public List<Dept> search(DeptParam vo) {
        return deptMapper.search(vo);
    }

    @Override
    public List<Dept> selectByName(String name) {
        return deptMapper.selectByName(name);
    }


}
