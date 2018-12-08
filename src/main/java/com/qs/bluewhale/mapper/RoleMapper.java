package com.qs.bluewhale.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qs.bluewhale.entity.Role;

import java.util.Set;

public interface RoleMapper extends BaseMapper<Role> {

    Set<String> findRoleCodesByUserName(String userName);

}
