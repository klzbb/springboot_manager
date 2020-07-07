package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.AclModule;
import com.konglingzhan.manager.dao.AclModuleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AclModuleServiceImpl implements AclModuleService{
    @Resource
    private AclModuleMapper aclModuleMapper;

    @Override
    public int insert(AclModule aclModule) {
        return aclModuleMapper.insert(aclModule);
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
