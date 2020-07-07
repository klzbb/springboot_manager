package com.konglingzhan.manager.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AclMapper {
    @Insert("insert into sys_acl(name,parent_id,level,status,seq) values(#{name},#{parent_id},#{level},#{status},#{seq})")
    int insert(AclMapper acl);

    @Select("select * from sys_acl")
    List<AclMapper> selectAll();

    @Select("select * from sys_acl where name = #{name}")
    List<AclMapper> selectByName(@Param("name") String name);
}
