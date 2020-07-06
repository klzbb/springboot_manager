package com.konglingzhan.manager.mapper;

import com.konglingzhan.manager.bean.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {
    @Insert("insert into sys_role(name,type) values(#{name},#{type})")
    int insert(Role role);

    @Select("select * from sys_role")
    List<Role> selectAllRole();

    @Select("select * from sys_role where name = #{name}")
    List<Role> selectRoleByname(@Param("name") String name);
}
