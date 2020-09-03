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

    private List<AclModuleLevelDto> aclModuleList = new ArrayList<>();

    private List<AclDto> aclList = new ArrayList<>();

    // 深拷贝
    public static AclModuleLevelDto adapt(Menu menu){
        AclModuleLevelDto dto = new AclModuleLevelDto();
        BeanUtils.copyProperties(menu,dto);
        return dto;
    }
}
