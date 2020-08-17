package com.konglingzhan.manager.bean;

import lombok.*;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {
    private Integer id;
    private String name;
    private Integer type;
    private Integer status;
    private String remark;
    private String operator;
    private Date operate_time;
    private String operate_ip;
}
