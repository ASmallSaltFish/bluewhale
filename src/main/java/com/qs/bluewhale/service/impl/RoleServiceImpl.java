package com.qs.bluewhale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qs.bluewhale.entity.Role;
import com.qs.bluewhale.mapper.RoleMapper;
import com.qs.bluewhale.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> listRoles(List<String> roleIds) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", roleIds);
        return list(queryWrapper);
    }

    @Override
    public Role findByRoleCode(String roleCode) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_code", roleCode);
        return getOne(queryWrapper);
    }

    @Override
    public Set<String> findRoleCodesByUserName(String userName) {
        return roleMapper.findRoleCodesByUserName(userName);
    }
}
