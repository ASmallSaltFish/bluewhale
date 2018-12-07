package com.qs.bluewhale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qs.bluewhale.entity.Role;

import java.util.List;

public interface RoleService extends IService<Role> {

    List<Role> listRoles(List<String> roleIds);
    Role findRoleByName(String roleName);
}
