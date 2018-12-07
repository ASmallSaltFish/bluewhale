package com.qs.bluewhale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qs.bluewhale.entity.Role;
import com.qs.bluewhale.mapper.RoleMapper;
import com.qs.bluewhale.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<Role> listRoles(List<String> roleIds) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", roleIds);
        return list(queryWrapper);
    }

    @Override
    public Role findRoleByName(String roleName) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name", roleName);
        return getOne(queryWrapper);
    }
}
