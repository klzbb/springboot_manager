package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.Acl;
import com.konglingzhan.manager.dao.AclMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AclServiceImpl implements AclService{
    @Resource
    private AclMapper aclMapper;

    @Override
    public int insert(Acl acl) {
        return aclMapper.insert(acl);
    }

    @Override
    public List<Acl> queryAll() {
        return aclMapper.selectAll();
    }
}
