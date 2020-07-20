package com.konglingzhan.manager.dao;

import com.konglingzhan.manager.bean.Dept;
import com.konglingzhan.manager.param.DeptParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Insert("insert into sys_dept(name,parent_id,level,seq,remark) values( #{name},#{parent_id},#{level},#{seq},#{remark})")
    int insert(Dept dept);

    @Select("select * from sys_dept")
    List<Dept> selectAll();

    @Select({
            "<script>",
                "select * from sys_dept",
                   "where 1=1",
                    "<if test='name != null'> and name=#{name} </if>",
                    "<if test='parent_id != null'> and parent_id=#{parent_id} </if>",
                    "<if test='level != null'> and level=#{level} </if>",
                    "<if test='seq != null'> and seq=#{seq} </if>",
            "</script>"
    })
    List<Dept> search(DeptParam vo);

    @Select("select * from sys_dept where name = #{name}")
    List<Dept> selectByName(String name);

    @Select("select * from sys_dept where id = #{deptId}")
    Dept selectByPrimaryKey(Integer deptId);

    @Update({
            "<script>",
                "update sys_dept set",
                    "name = #{name}, ",
                    "parent_id = #{parent_id}, ",
                    "level = #{level}, ",
                    "seq = #{seq}, ",
                    "remark = #{remark}, ",
                    "operator = #{operator}, ",
                    "operate_time = #{operateTime}, ",
                    "operate_ip = #{operate_ip} ",
                "where id = #{id}",
            "</script>"
    })
    void updateDeptById(Dept dept);

    @Select("select * from sys_dept where level like #{level} || '0.%' ")
    List<Dept> getChildDeptListByLevel(String level);

    @Update({
            "<script>",
                "<foreach collection='deptList' item='item' open='' close='' separator=';'>",
                    "update sys_dept set level = #{item.level} where id = #{item.id}",
                "</foreach>",
            "</script>"
    })
    void batchUpdateLevel(@Param("deptList") List<Dept> deptList);


    @Select({
            "<script>",
                "select count(1)",
                    "from sys_dept",
                "where parent_id = #{parentId}",
                "and name = #{name}",
                "<if test='id != null'> and id != #{id} </if>",
            "</script>"
    })
    int countByNameAndParentId(int parentId, String name, Integer id);

    @Select("delete from sys_dept where id = #{id}")
    void delByid(int id);


}
