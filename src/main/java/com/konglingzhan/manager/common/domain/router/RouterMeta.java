package com.konglingzhan.manager.common.domain.router;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouterMeta {

    private String title;

    private String icon;

    private Boolean noCache = true;

    public RouterMeta(String title,String icon,Boolean noCache){
        this.title = title;
        this.icon = icon;
        this.noCache = noCache;
    }
}
