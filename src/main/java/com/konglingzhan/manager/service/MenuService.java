package com.konglingzhan.manager.service;

import com.konglingzhan.manager.entity.Menu;
import com.konglingzhan.manager.param.MenuParam;

import java.util.List;

public interface MenuService {

    void insert(MenuParam param);

    List<Menu> selectAll();

    List<Menu> selectByName(String name);

    void delById(int id);

    void update(MenuParam param);

    Menu findLevelById(int aclModuleId);
}
