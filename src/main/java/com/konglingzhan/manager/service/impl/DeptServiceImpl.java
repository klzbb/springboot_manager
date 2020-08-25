package com.konglingzhan.manager.service.impl;

import com.konglingzhan.manager.bean.Dept;
import com.konglingzhan.manager.common.RequestHolder;
import com.konglingzhan.manager.dao.DeptMapper;
import com.konglingzhan.manager.common.exception.ParamException;
import com.konglingzhan.manager.param.DeptParam;
import com.konglingzhan.manager.service.DeptService;
import com.konglingzhan.manager.util.BeanValidator;
import com.konglingzhan.manager.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptMapper deptMapper;

    @Override
    public boolean checkExist(Integer parentId, String deptName, Integer deptId) {
       return deptMapper.countByNameAndParentId(parentId,deptName,deptId) > 0;
    }

    @Override
    public Dept selectByPrimaryKey(Integer deptId) {
        return null;
    }

    private String getLevel(Integer deptId) {
        Dept dept = deptMapper.selectByPrimaryKey(deptId);
        if(dept == null){
            return null;
        }
        return dept.getLevel();
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
        dept.setOperator(RequestHolder.getCurrentUser().getUsername()); // todo
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

    @Override
    public void updateDept(DeptParam param) {
        BeanValidator.check(param);
        Dept before = deptMapper.selectByPrimaryKey(param.getId());
        Objects.requireNonNull(before,"待更新的部门不存在");
        if(checkExist(param.getParentId(),param.getName(),param.getId())){
            throw new ParamException("同一层级下存在相同名称的部门");
        }

        Dept after = Dept.builder().id(param.getId()).name(param.getName()).parent_id(param.getParentId()).seq(param.getSeq()).remark(param.getRemark()).build();
        after.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()),param.getParentId()));
        after.setOperator(RequestHolder.getCurrentUser().getUsername()); // todo
        after.setOperate_ip("127.0.0.1"); // todo
        after.setOperateTime(new Date());
        updateWithChild(before,after);
    }

    @Transactional
    public void updateWithChild(Dept before, Dept after){
        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();
        if(!after.getLevel().equals(before.getLevel())){
            List<Dept> deptList = deptMapper.getChildDeptListByLevel(before.getLevel());
            if(CollectionUtils.isNotEmpty(deptList)){
                for (Dept dept: deptList){
                    String level = dept.getLevel();
                    if(level.indexOf(oldLevelPrefix) == 0){
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        dept.setLevel(level);
                    }
                }
                deptMapper.batchUpdateLevel(deptList);
            }
        }
        deptMapper.updateDeptById(after);
    }

    @Override
    public void delById(int id) {
        deptMapper.delByid(id);
    }
}
