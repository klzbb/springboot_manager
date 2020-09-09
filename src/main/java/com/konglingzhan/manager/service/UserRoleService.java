package com.konglingzhan.manager.service;

import com.konglingzhan.manager.dto.UserRoleDto;
import com.konglingzhan.manager.param.RoleUserParam;

import java.util.List;

public interface UserRoleService {
    public void insert(RoleUserParam param);

    public List<UserRoleDto> all();

    public void del(int id);
}
