package com.konglingzhan.manager.dto;

import com.konglingzhan.manager.bean.AclModule;
import com.konglingzhan.manager.bean.Dept;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class AclModuleLevelDto extends AclModule {

    private List<AclModuleLevelDto> aclModuleList = new ArrayList<>();

    private List<AclDto> aclList = new ArrayList<>();

    // 深拷贝
    public static AclModuleLevelDto adapt(AclModule aclModule){
        AclModuleLevelDto dto = new AclModuleLevelDto();
        BeanUtils.copyProperties(aclModule,dto);
        return dto;
    }
}
