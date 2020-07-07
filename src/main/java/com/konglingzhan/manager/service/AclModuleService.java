package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.AclModule;

import java.util.List;

public interface AclModuleService {

    int insert(AclModule aclModule);

    List<AclModule> selectAll();

    List<AclModule> selectByName(String name);

}
