package com.konglingzhan.manager.dto;

import com.konglingzhan.manager.bean.Dept;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class DeptLevelDto extends Dept {
    private List<DeptLevelDto> deptList = new ArrayList<>();

    // 深拷贝
    public static DeptLevelDto adapt(Dept dept){
        DeptLevelDto dto = new DeptLevelDto();
        BeanUtils.copyProperties(dept,dto);
        return dto;
    }
}
