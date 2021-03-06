package com.konglingzhan.manager.service.impl;

import com.konglingzhan.manager.common.exception.CommonException;
import com.konglingzhan.manager.dao.RoleMenuMapper;
import com.konglingzhan.manager.entity.Menu;
import com.konglingzhan.manager.common.RequestHolder;
import com.konglingzhan.manager.dao.MenuMapper;
import com.konglingzhan.manager.common.exception.ParamException;
import com.konglingzhan.manager.param.MenuParam;
import com.konglingzhan.manager.service.MenuService;
import com.konglingzhan.manager.util.BeanValidator;
import com.konglingzhan.manager.util.LevelUtil;
import com.konglingzhan.manager.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuMapper menuMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public void insert(MenuParam param) {
        if(checkExist(param.getParentId(),param.getName(),param.getId())){
            throw new ParamException("同一层级下存在相同的权限名称");
        }
        Menu menu = Menu.builder()
                        .path(param.getPath())
                        .type(param.getType())
                        .name(param.getName())
                        .parentId(param.getParentId())
                        .seq(param.getSeq())
                        .status(param.getStatus())
                        .remark(param.getRemark())
                        .componentName(param.getComponentName())
                        .icon(param.getIcon())
                        .component(param.getComponent())
                        .build();
        String level = LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId());
        menu.setLevel(level);
        menu.setOperator("admin");
        menu.setOperateIp("127.0.0.1");
        menu.setOperateTime(new Date());
        this.menuMapper.insert(menu);
    }

    @Transactional
    public void updateWithChild(Menu before, Menu after){
        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();
        if(!after.getLevel().equals(before.getLevel())){
            // 更新关联menu的level值
            String paramLevel = LevelUtil.calculateLevel(before.getLevel(), before.getId());
            List<Menu> list = menuMapper.getChildDeptListByLevel(paramLevel);
            if(CollectionUtils.isNotEmpty(list)){
                for (Menu menu : list){
                    String level = menu.getLevel();
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        menu.setLevel(level);
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
    @Transactional
    public void delById(int menuId) {
        if(roleMenuMapper.countByMenuId(menuId) > 0){
           throw new CommonException(-1,"请先解绑该菜单在角色中的关联");
        }
        menuMapper.delById(menuId);
    }

    @Override
    public void delByIds(List<Integer> ids){
        menuMapper.delByIds(ids);
    }

    @Override
    public List<Menu> getMenuListByMenuIds(List<Integer> menuIds) {
        return menuMapper.getByIdList(menuIds);
    }

    @Override
    public void update(MenuParam param) {
        BeanValidator.check(param);
        if(checkExist(param.getParentId(),param.getName(),param.getId())){
            throw new ParamException("同一层级下存在相同名称的菜单");
        }
        Menu before = menuMapper.selectByPrimaryKey(param.getId());
        Objects.requireNonNull(before,"待更新菜单不存在");

        Menu after = Menu.builder()
                         .id(param.getId())
                         .path(param.getPath())
                         .type(param.getType())
                         .name(param.getName())
                         .parentId(param.getParentId())
                         .seq(param.getSeq())
                         .status(param.getStatus())
                         .remark(param.getRemark())
                         .componentName(param.getComponentName())
                         .icon(param.getIcon())
                         .component(param.getComponent())
                         .build();
        String level = LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId());
        after.setLevel(level);
        after.setOperator(UserUtil.getLoginUser().getUsername());
        after.setOperateIp("127.0.0.1");
        after.setOperateTime(new Date());
        updateWithChild(before,after);
    }

    @Override
    public Menu findLevelById(int aclModuleId) {
        return menuMapper.findLevelById(aclModuleId);
    }

    @Override
    public List<Menu> getMenuListByLevel(String level) {
        return menuMapper.getChildDeptListByLevel(level);
    }



}
