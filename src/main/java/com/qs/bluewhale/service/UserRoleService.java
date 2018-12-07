package com.qs.bluewhale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qs.bluewhale.entity.Role;
import com.qs.bluewhale.entity.UserRole;

import java.util.Set;

public interface UserRoleService extends IService<UserRole> {

    Set<Role> findRolesByUserId(String userId);

    Set<String> getRoleNames(String username);
    void saveUserRole(UserRole userRole);
}
