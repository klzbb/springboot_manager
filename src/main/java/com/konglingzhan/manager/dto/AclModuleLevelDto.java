package com.konglingzhan.manager.dto;

import com.konglingzhan.manager.entity.Menu;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class AclModuleLevelDto extends Menu {

//    // 是否默认选中
//    private boolean checked = false;
//
//    // 是否有权限操作
//    private boolean hasAcl = false;

    private List<AclModuleLevelDto> children = new ArrayList<>();

    // 深拷贝
    public static AclModuleLevelDto adapt(Menu menu){
        AclModuleLevelDto dto = new AclModuleLevelDto();
        BeanUtils.copyProperties(menu,dto);
        return dto;
    }
}
