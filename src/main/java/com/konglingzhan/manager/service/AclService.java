package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.Acl;
import com.konglingzhan.manager.param.AclParam;

import java.util.List;

public interface AclService {
    void insert(AclParam param);

    List<Acl> queryAll();
}
