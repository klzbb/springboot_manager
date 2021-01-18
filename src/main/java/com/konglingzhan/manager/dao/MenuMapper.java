package com.konglingzhan.manager.dao;

import com.konglingzhan.manager.entity.Acl;
import com.konglingzhan.manager.entity.Menu;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface MenuMapper {
    @Insert("insert into sys_menu(name,parent_id,path,type,level,status,seq,remark,operator,operate_time,operate_ip) values(#{name},#{parentId},#{path},#{type},#{level},#{status},#{seq},#{remark},#{operator},#{operateTime},#{operateIp})")
    void insert(Menu menu);

    @Select("select * from sys_menu")
    @Results({
            @Result(column = "parent_id",property = "parentId")
    })
    List<Menu> selectAll();

    @Select("select * from sys_menu where name = #{name}")
    List<Menu> selectByName(@Param("name") String name);

    @Select("select * from sys_menu where id = #{aclModuleId}")
    Menu selectByPrimaryKey(Integer aclModuleId);

    @Select({
            "<script>",
            "select count(1)",
            "from sys_menu",
            "where parent_id = #{parentId}",
            "and name = #{name}",
            "<if test='id != null'> and id != #{id} </if>",
            "</script>"
    })
    int countByNameAndParentId(int parentId, String name, Integer id);

    @Delete("delete from sys_menu where id = #{id}")
    void delById(int id);

    @Delete({
            "<script>",
                "delete from sys_menu where in",
                "<foreach collection='ids' item='id' open='(' close=')' separator=','>",
                    "#{id}",
                "</foreach>",
            "</script>"
    })
    void delByIds(List<Integer> ids);

    @Update({
            "<script>",
                "<foreach collection='list' item='item' open='' close='' separator=';'>",
                    "update sys_menu set level = #{item.level} where id = #{item.id}",
                "</foreach>",
            "</script>"
    })
    void batchUpdateLevel(@Param("list") List<Menu> list);

    @Select("select * from sys_menu where level like concat('%',#{level},'%')")
    List<Menu> getChildDeptListByLevel(String level);


    @Update({
            "<script>",
                "update sys_menu set",
                    "name = #{name},",
                    "parent_id = #{parentId},",
                    "level = #{level},",
                    "status = #{status},",
                    "seq = #{seq},",
                    "remark = #{remark},",
                    "operator = #{operator},",
                    "operate_time = #{operateTime},",
                    "operate_ip = #{operateIp}",
                "where id = #{id}",
            "</script>"
    })
    void updateByPrimaryKeySelective(Menu menu);

    @Select("select level from sys_menu where id = #{id}")
    Menu findLevelById(int aclModuleId);

    @Select({
            "<script>",
                "select *",
                    "from",
                "sys_menu",
                "where id in ",
                 "<foreach collection='idList' item='id' open='(' close=')' separator=',' > #{id} </foreach>",
            "</script>"
    })
    List<Menu> getByIdList(@Param("idList") List<Integer> idList);
}
