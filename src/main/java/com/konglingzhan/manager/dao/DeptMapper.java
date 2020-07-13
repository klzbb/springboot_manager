package com.konglingzhan.manager.dao;

import com.konglingzhan.manager.bean.Dept;
import com.konglingzhan.manager.bean.Role;
import com.konglingzhan.manager.param.DeptVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
    List<Dept> search(DeptVo vo);

    @Select("select * from sys_dept where name = #{name}")
    List<Dept> selectByName(String name);
}
