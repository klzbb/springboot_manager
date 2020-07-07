package com.konglingzhan.manager.bean;

import lombok.Data;

@Data
public class AclModule {
    private String name;
    private Integer parent_id;
    private String level;
    private Integer status;
    private Integer seq;
    private String operator;
}
