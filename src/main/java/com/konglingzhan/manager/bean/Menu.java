package com.konglingzhan.manager.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)	//注解控制null不序列化
public class Menu {

    private Integer id;

    private String name;

    private Integer parent_id;

    private String level;

    private Integer status;

    private Integer seq;

    private String remark;

    private String operator;

    private Date operate_time;

    private String operate_ip;
}
