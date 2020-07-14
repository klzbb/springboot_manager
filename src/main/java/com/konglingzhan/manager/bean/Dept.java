package com.konglingzhan.manager.bean;

import lombok.*;

import java.util.Date;

/*
* 部门实体类
*/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Dept {

    private int id;

    private String name;

    private int parent_id;

    private String level;

    private int seq;

    private String remark;

    private String operator;

    private Date operateTime;

    private String operate_ip;
}
