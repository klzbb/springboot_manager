package com.konglingzhan.manager.bean;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Acl {
    private int id;
    private String code;
    private String name;
    private Integer acl_module_id;
    private String url;
    private Integer type;
    private Integer status;
    private Integer seq;
    private String remark;
    private String operator;
    private Date operate_time;
    private String operate_ip;
}
