package com.konglingzhan.manager.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
public class DeptParam {

    private Integer id;

    @NotBlank(message = "部门名称不能为空")
    @Length(max=15,min=2,message = "部门名称长度为2-15个字段")
    private String name;

    private Integer parentId;

    private String level;

    @NotNull(message = "展示顺序不能为空")
    private Integer seq;

    @Length(max=150,message = "备注的长度最大不能超过150个字段")
    private String remark;

}
