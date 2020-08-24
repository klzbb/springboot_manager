package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.Menu;
import com.konglingzhan.manager.param.AclModuleParam;

import java.util.List;

public interface MenuService {

    void insert(AclModuleParam param);

    List<Menu> selectAll();

    List<Menu> selectByName(String name);

    void delById(int id);

    void update(AclModuleParam param);

    Menu findLevelById(int aclModuleId);
}
