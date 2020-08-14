package com.konglingzhan.manager.dao;

import com.konglingzhan.manager.bean.AclModule;
import com.konglingzhan.manager.bean.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface AclModuleMapper {
    @Insert("insert into sys_acl_module(name,parent_id,level,status,seq,remark,operator,operate_time,operate_ip) values(#{name},#{parent_id},#{level},#{status},#{seq},#{remark},#{operator},#{operate_time},#{operate_ip})")
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

    @Delete("delete from sys_acl_module where id = #{id}")
    void delById(int id);

    @Update({
            "<script>",
                "<foreach collection='list' item='item' open='' close='' separator=';'>",
                    "update sys_acl_module set level = #{item.level} where id = #{item.id}",
                "</foreach>",
            "</script>"
    })
    void batchUpdateLevel(@Param("list") List<AclModule> list);

    @Select("select * from sys_acl_module where level like #{level} || '0.%' ")
    List<AclModule> getChildDeptListByLevel(String level);

    @Update({
            "<script>",
                "update sys_acl_module set",
                    "name = #{name},",
                    "parent_id = #{parent_id},",
                    "level = #{level},",
                    "status = #{status},",
                    "seq = #{seq},",
                    "remark = #{remark},",
                    "operator = #{operator},",
                    "operate_time = #{operate_time},",
                    "operate_ip = #{operate_ip}",
                "where id = #{id}",
            "</script>"
    })
    void updateByPrimaryKeySelective(AclModule aclModule);
}
