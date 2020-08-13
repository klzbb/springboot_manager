package com.konglingzhan.manager.dao;

import com.konglingzhan.manager.bean.AclModule;
import com.konglingzhan.manager.bean.Dept;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface AclModuleMapper {
    @Insert("insert into sys_acl_module(name,parent_id,level,status,seq) values(#{name},#{parent_id},#{level},#{status},#{seq})")
    void insert(AclModule aclModule);

    @Select("select * from sys_acl_module")
    List<AclModule> selectAll();

    @Select("select * from sys_acl_module where name = #{name}")
    List<AclModule> selectByName(@Param("name") String name);

    @Select("select * from sys_acl_module where id = #{aclModuleId}")
    AclModule selectByPrimaryKey(Integer aclModuleId);

    @Select({
            "<script>",
            "select count(1)",
            "from sys_acl_module",
            "where parent_id = #{parentId}",
            "and name = #{name}",
            "<if test='id != null'> and id != #{id} </if>",
            "</script>"
    })
    int countByNameAndParentId(int parentId, String name, Integer id);

}
