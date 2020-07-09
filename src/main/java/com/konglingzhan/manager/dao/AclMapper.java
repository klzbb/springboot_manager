package com.konglingzhan.manager.dao;

import com.konglingzhan.manager.bean.Acl;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AclMapper {

    @Insert("insert into sys_acl(code,name,acl_module_id,url,type,status,seq,remark) values(#{code},#{name},#{acl_module_id},#{url},#{type},#{status},#{seq},#{remark})")
    int insert(Acl acl);

    @Select("select * from sys_acl")
    List<Acl> selectAll();

}
