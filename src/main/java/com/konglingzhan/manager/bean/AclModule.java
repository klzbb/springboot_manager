package com.konglingzhan.manager.bean;

import lombok.*;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AclModule {

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
