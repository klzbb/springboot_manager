package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.AclModule;
import com.konglingzhan.manager.bean.Dept;
import com.konglingzhan.manager.common.RequestHolder;
import com.konglingzhan.manager.dao.AclModuleMapper;
import com.konglingzhan.manager.exception.ParamException;
import com.konglingzhan.manager.param.AclModuleParam;
import com.konglingzhan.manager.util.BeanValidator;
import com.konglingzhan.manager.util.LevelUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public void updateWithChild(){

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

}
