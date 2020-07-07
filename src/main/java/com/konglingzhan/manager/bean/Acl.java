package com.konglingzhan.manager.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Acl {
    private String code;
    private String name;
    private Integer acl_module_id;
    private String url;
    private Integer type;
    private Integer status;
    private Integer seq;
    private String remark;
}
