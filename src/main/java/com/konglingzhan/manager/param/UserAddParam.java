package com.konglingzhan.manager.param;

import com.konglingzhan.manager.param.common.UserParam;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class UserAddParam extends UserParam {
    @NotBlank(message = "(密码不能为空)")
    private String password;
}
