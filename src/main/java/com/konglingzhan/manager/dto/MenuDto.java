package com.konglingzhan.manager.dto;

import com.konglingzhan.manager.entity.Acl;
import com.konglingzhan.manager.entity.Menu;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@ToString
public class MenuDto extends Menu {

    // 是否默认选中
    private boolean checked = false;

    // 是否有权限操作
    private boolean hasAcl = false;

    private List<MenuDto> children = new ArrayList<>();

    // 深拷贝
    public static MenuDto adapt(Menu menu){
        MenuDto dto = new MenuDto();
        BeanUtils.copyProperties(menu,dto);
        return dto;
    }
}
