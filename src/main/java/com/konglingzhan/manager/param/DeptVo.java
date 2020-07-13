package com.konglingzhan.manager.param;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class DeptVo {

    private Integer id;

    private String name;

    private Integer parent_id;

    private String level;

    private Integer seq;

    private String remark;

    private String operator;

    private Date operateTime;

    private String operateIp;
}
