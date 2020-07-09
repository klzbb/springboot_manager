package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.Acl;

import java.util.List;

public interface AclService {
    int insert(Acl acl);

    List<Acl> queryAll();
}
