package com.konglingzhan.manager.service;

import com.konglingzhan.manager.entity.Acl;
import com.konglingzhan.manager.dto.PageResult;
import com.konglingzhan.manager.param.AclParam;
import com.konglingzhan.manager.param.PageQuery;

import java.util.List;

public interface AclService {
    void insert(AclParam param);

    List<Acl> queryAll();

    void update(AclParam param);

    PageResult<Acl> pageList(int aclModuleId, PageQuery pageQuery);

    void del(int id);
}
