package com.qs.bluewhale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qs.bluewhale.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService extends IService<Role> {

    /**
     * 获取角色集合信息
     *
     * @param roleIds 角色id集合
     * @return 获取角色集合信息
     */
    List<Role> listRoles(List<String> roleIds);

    /**
     * 根据角色编号，获取角色信息
     *
     * @param roleCode 角色编号
     * @return 返回角色角色实体
     */
    Role findByRoleCode(String roleCode);


    Set<String> findRoleCodesByUserName(String userName);
}
