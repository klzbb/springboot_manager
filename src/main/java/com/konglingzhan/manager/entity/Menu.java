package com.konglingzhan.manager.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="sys_menu")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)    //注解控制null不序列化
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String url;

    private String code;

    private Integer type;

    @Column(name = "parent_id")
    private Integer parentId;

    private String level;

    private Integer status;

    private Integer seq;

    private String remark;

    private String operator;

    @Column(name = "operate_time")
    private Date operateTime;

    @Column(name = "operate_ip")
    private String operateIp;
}
