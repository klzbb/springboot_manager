package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.Acl;
import com.konglingzhan.manager.bean.AclModule;
import com.konglingzhan.manager.bean.Dept;
import com.konglingzhan.manager.common.RequestHolder;
import com.konglingzhan.manager.dao.AclModuleMapper;
import com.konglingzhan.manager.exception.ParamException;
import com.konglingzhan.manager.param.AclModuleParam;
import com.konglingzhan.manager.util.BeanValidator;
import com.konglingzhan.manager.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Preconditions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class AclModuleServiceImpl implements AclModuleService{
    @Resource
    private AclModuleMapper aclModuleMapper;

    @Override
    public void insert(AclModuleParam param) {
        BeanValidator.check(param);
        if(checkExist(param.getParentId(),param.getName(),param.getId())){
            throw new ParamException("同一层级下存在相同的权限名称");
        }
        AclModule aclModule = AclModule.builder().name(param.getName()).parent_id(param.getParentId()).seq(param.getSeq()).status(param.getStatus()).remark(param.getRemark()).build();
        String level = LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId());
        aclModule.setLevel(level);
        aclModule.setOperator(RequestHolder.getCurrentUser().getUsername());
        aclModule.setOperate_ip("127.0.0.1");
        aclModule.setOperate_time(new Date());
        aclModuleMapper.insert(aclModule);
    }

    @Transactional
    public void updateWithChild(AclModule before,AclModule after){
        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();
        if(!after.getLevel().equals(before.getLevel())){
            List<AclModule> list = aclModuleMapper.getChildDeptListByLevel(before.getLevel());
            if(CollectionUtils.isNotEmpty(list)){
                for (AclModule aclModule: list){
                    String level = aclModule.getLevel();
                    if(level.indexOf(oldLevelPrefix) == 0){
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        aclModule.setLevel(level);
                    }
                }
                aclModuleMapper.batchUpdateLevel(list);
            }
        }
        aclModuleMapper.updateByPrimaryKeySelective(after);
    }

    private boolean checkExist(Integer parentId,String aclModuleName,Integer id){
        return aclModuleMapper.countByNameAndParentId(parentId,aclModuleName,id) > 0;
    }

    private String getLevel(Integer aclModuleId) {
        AclModule aclModule = aclModuleMapper.selectByPrimaryKey(aclModuleId);
        if(aclModule == null){
            return null;
        }
        return aclModule.getLevel();
    }
    @Override
    public List<AclModule> selectAll() {
        return aclModuleMapper.selectAll();
    }

    @Override
    public List<AclModule> selectByName(String name) {
        return aclModuleMapper.selectByName(name);
    }

    @Override
    public void delById(int id) {
        aclModuleMapper.delById(id);
    }


    @Override
    public void update(AclModuleParam param) {
        BeanValidator.check(param);
        if(checkExist(param.getParentId(),param.getName(),param.getId())){
            throw new ParamException("同一层级下存在相同名称的权限模块");
        }
        AclModule before = aclModuleMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before,"待更新权限模块不存在");
        AclModule after = AclModule.builder().id(param.getId()).name(param.getName()).parent_id(param.getParentId()).seq(param.getSeq()).status(param.getStatus()).remark(param.getRemark()).build();
        String level = LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId());
        after.setLevel(level);
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperate_ip("127.0.0.1");
        after.setOperate_time(new Date());
        updateWithChild(before,after);
    }

    @Override
    public AclModule findLevelById(int aclModuleId) {
        return aclModuleMapper.findLevelById(aclModuleId);
    }
}
