package com.konglingzhan.manager.dao;

import com.konglingzhan.manager.bean.AclModule;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface AclModuleMapper {
    @Insert("insert into sys_acl_module(name,parent_id,level,status,seq) values(#{name},#{parent_id},#{level},#{status},#{seq})")
    int insert(AclModule aclModule);

    @Select("select * from sys_acl_module")
    List<AclModule> selectAll();

    @Select("select * from sys_acl_module where name = #{name}")
    List<AclModule> selectByName(@Param("name") String name);
}
