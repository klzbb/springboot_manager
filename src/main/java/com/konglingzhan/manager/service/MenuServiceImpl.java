package com.konglingzhan.manager.service;

import com.konglingzhan.manager.common.RequestHolder;
import com.konglingzhan.manager.dao.MenuMapper;
import com.konglingzhan.manager.exception.ParamException;
import com.konglingzhan.manager.param.MenuParam;
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
        BeanValidator.check(param);
        if(checkExist(param.getParentId(),param.getName(),param.getId())){
            throw new ParamException("同一层级下存在相同的权限名称");
        }
        com.konglingzhan.manager.bean.Menu menu = com.konglingzhan.manager.bean.Menu.builder().name(param.getName()).parent_id(param.getParentId()).seq(param.getSeq()).status(param.getStatus()).remark(param.getRemark()).build();
        String level = LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId());
        menu.setLevel(level);
        menu.setOperator(RequestHolder.getCurrentUser().getUsername());
        menu.setOperate_ip("127.0.0.1");
        menu.setOperate_time(new Date());
        this.menuMapper.insert(menu);
    }

    @Transactional
    public void updateWithChild(com.konglingzhan.manager.bean.Menu before, com.konglingzhan.manager.bean.Menu after){
        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();
        if(!after.getLevel().equals(before.getLevel())){
            List<com.konglingzhan.manager.bean.Menu> list = menuMapper.getChildDeptListByLevel(before.getLevel());
            if(CollectionUtils.isNotEmpty(list)){
                for (com.konglingzhan.manager.bean.Menu menu : list){
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
        com.konglingzhan.manager.bean.Menu menu = this.menuMapper.selectByPrimaryKey(aclModuleId);
        if(menu == null){
            return null;
        }
        return menu.getLevel();
    }
    @Override
    public List<com.konglingzhan.manager.bean.Menu> selectAll() {
        return menuMapper.selectAll();
    }

    @Override
    public List<com.konglingzhan.manager.bean.Menu> selectByName(String name) {
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
        com.konglingzhan.manager.bean.Menu before = menuMapper.selectByPrimaryKey(param.getId());
        Objects.requireNonNull(before,"待更新权限模块不存在");
        com.konglingzhan.manager.bean.Menu after = com.konglingzhan.manager.bean.Menu.builder().id(param.getId()).name(param.getName()).parent_id(param.getParentId()).seq(param.getSeq()).status(param.getStatus()).remark(param.getRemark()).build();
        String level = LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId());
        after.setLevel(level);
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperate_ip("127.0.0.1");
        after.setOperate_time(new Date());
        updateWithChild(before,after);
    }

    @Override
    public com.konglingzhan.manager.bean.Menu findLevelById(int aclModuleId) {
        return menuMapper.findLevelById(aclModuleId);
    }
}
