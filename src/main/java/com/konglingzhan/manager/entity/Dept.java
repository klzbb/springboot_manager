package com.konglingzhan.manager.entity;

import lombok.*;

import javax.persistence.*;
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
@Entity
@Table(name = "sys_dept")
public class Dept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
