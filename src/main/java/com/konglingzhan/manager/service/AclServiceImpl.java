package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.Acl;
import com.konglingzhan.manager.common.RequestHolder;
import com.konglingzhan.manager.dao.AclMapper;
import com.konglingzhan.manager.exception.ParamException;
import com.konglingzhan.manager.param.AclParam;
import com.konglingzhan.manager.util.BeanValidator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AclServiceImpl implements AclService{
    @Resource
    private AclMapper aclMapper;/

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
    public boolean checkExist(int aclModuleId,String name,Integer id){
        return false;
    }

    public String genarateCode(){
        return "00";
    }

    @Override
    public List<Acl> queryAll() {
        return aclMapper.selectAll();
    }
}
