package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.Acl;
import com.konglingzhan.manager.bean.PageResult;
import com.konglingzhan.manager.bean.User;
import com.konglingzhan.manager.common.RequestHolder;
import com.konglingzhan.manager.dao.AclMapper;
import com.konglingzhan.manager.exception.ParamException;
import com.konglingzhan.manager.param.AclParam;
import com.konglingzhan.manager.param.PageQuery;
import com.konglingzhan.manager.util.BeanValidator;
import org.assertj.core.util.Preconditions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

@Service
public class AclServiceImpl implements AclService{
    @Resource
    private AclMapper aclMapper;

    @Override
    public void insert(AclParam param) {
        BeanValidator.check(param);
        if(checkExist(param.getAclModuleId(),param.getName(),param.getId())){
            throw new ParamException("当前权限模块下存在相同名称的权限点");
        }
        Acl acl = Acl.builder().name(param.getName()).acl_module_id(param.getAclModuleId()).url(param.getUrl()).type(param.getType()).status(param.getStatus()).seq(param.getSeq()).remark(param.getRemark()).build();
        acl.setCode(genarateCode());
        acl.setOperator(RequestHolder.getCurrentUser().getUsername());
        acl.setOperate_time(new Date());
        acl.setOperate_ip("127.0.0.1");
        aclMapper.insert(acl);
    }

    @Override
    public void update(AclParam param) {
        BeanValidator.check(param);
        if(checkExist(param.getAclModuleId(),param.getName(),param.getId())){
            throw new ParamException("当前权限模块下存在相同名称的权限点");
        }
        Acl before = aclMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before,"待更新权限点不存在");
        Acl after = Acl.builder().id(param.getId()).name(param.getName()).acl_module_id(param.getAclModuleId()).url(param.getUrl()).type(param.getType()).status(param.getStatus()).seq(param.getSeq()).remark(param.getRemark()).build();
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperate_time(new Date());
        after.setOperate_ip("127.0.0.1");
        aclMapper.updateByPrimaryKeySelective(after);
    }

    public boolean checkExist(int aclModuleId, String name, Integer id){
        return aclMapper.countByNameAndParentId(aclModuleId,name,id) > 0;
    }

    public String genarateCode(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmSS");
        return dateFormat.format(new Date()) + "_" + (int) (Math.random() * 100);
    }

    @Override
    public List<Acl> queryAll() {
        return aclMapper.selectAll();
    }

    @Override
    public PageResult<Acl> pageList(int aclModuleId, PageQuery pageQuery) {
        BeanValidator.check(pageQuery);
        int num = aclMapper.countByAclModuleId(aclModuleId);

        if(num > 0){
            List list = aclMapper.pageList(aclModuleId,pageQuery);
            return PageResult.<Acl>builder().total(num).data(list).build();
        }

        return PageResult.<Acl>builder().build();
    }

    @Override
    public void del(int id) {
        aclMapper.del(id);
    }
}
