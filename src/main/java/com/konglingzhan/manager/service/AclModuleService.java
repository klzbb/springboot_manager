package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.AclModule;
import com.konglingzhan.manager.param.AclModuleParam;

import java.util.List;

public interface AclModuleService {

    void insert(AclModuleParam param);

    List<AclModule> selectAll();

    List<AclModule> selectByName(String name);

    void delById(int id);

    void update(AclModuleParam param);
}
