package com.konglingzhan.manager.service.impl;

import com.konglingzhan.manager.bean.Menu;
import com.konglingzhan.manager.common.RequestHolder;
import com.konglingzhan.manager.dao.MenuMapper;
import com.konglingzhan.manager.common.exception.ParamException;
import com.konglingzhan.manager.param.MenuParam;
import com.konglingzhan.manager.service.MenuService;
import com.konglingzhan.manager.util.BeanValidator;
import com.konglingzhan.manager.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuMapper menuMapper;

    @Override
    public void insert(MenuParam param) {
        if(checkExist(param.getParentId(),param.getName(),param.getId())){
            throw new ParamException("同一层级下存在相同的权限名称");
        }
        Menu menu = Menu.builder().url(param.getUrl()).type(param.getType()).name(param.getName()).parentId(param.getParentId()).seq(param.getSeq()).status(param.getStatus()).remark(param.getRemark()).build();
        String level = LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId());
        menu.setLevel(level);
        menu.setOperator(RequestHolder.getCurrentUser().getUsername());
        menu.setOperateIp("127.0.0.1");
        menu.setOperateTime(new Date());
        this.menuMapper.insert(menu);
    }

    @Transactional
    public void updateWithChild(Menu before, Menu after){
        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();
        if(!after.getLevel().equals(before.getLevel())){
            List<Menu> list = menuMapper.getChildDeptListByLevel(before.getLevel());
            if(CollectionUtils.isNotEmpty(list)){
                for (Menu menu : list){
                    String level = menu.getLevel();
                    if(level.indexOf(oldLevelPrefix) == 0){
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        menu.setLevel(level);
                    }
                }
                menuMapper.batchUpdateLevel(list);
            }
        }
        menuMapper.updateByPrimaryKeySelective(after);
    }

    private boolean checkExist(Integer parentId,String aclModuleName,Integer id){
        return menuMapper.countByNameAndParentId(parentId,aclModuleName,id) > 0;
    }

    private String getLevel(Integer aclModuleId) {
        Menu menu = this.menuMapper.selectByPrimaryKey(aclModuleId);
        if(menu == null){
            return null;
        }
        return menu.getLevel();
    }
    @Override
    public List<Menu> selectAll() {
        return menuMapper.selectAll();
    }

    @Override
    public List<Menu> selectByName(String name) {
        return menuMapper.selectByName(name);
    }

    @Override
    public void delById(int id) {
        menuMapper.delById(id);
    }


    @Override
    public void update(MenuParam param) {
        BeanValidator.check(param);
        if(checkExist(param.getParentId(),param.getName(),param.getId())){
            throw new ParamException("同一层级下存在相同名称的权限模块");
        }
        Menu before = menuMapper.selectByPrimaryKey(param.getId());
        Objects.requireNonNull(before,"待更新权限模块不存在");
        Menu after = Menu.builder().id(param.getId()).name(param.getName()).parentId(param.getParentId()).seq(param.getSeq()).status(param.getStatus()).remark(param.getRemark()).build();
        String level = LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId());
        after.setLevel(level);
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperateIp("127.0.0.1");
        after.setOperateTime(new Date());
        updateWithChild(before,after);
    }

    @Override
    public Menu findLevelById(int aclModuleId) {
        return menuMapper.findLevelById(aclModuleId);
    }
}
