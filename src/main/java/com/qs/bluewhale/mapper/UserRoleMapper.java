package com.qs.bluewhale.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qs.bluewhale.entity.UserRole;

import java.util.List;
import java.util.Set;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<String> getRoleIdsByUserId(String userId);

    Set<String> getRoleNamesByUserName(String userName);
}
