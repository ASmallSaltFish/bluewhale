package com.qs.bluewhale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qs.bluewhale.entity.Role;
import com.qs.bluewhale.entity.UserRole;
import com.qs.bluewhale.mapper.UserRoleMapper;
import com.qs.bluewhale.service.RoleService;
import com.qs.bluewhale.service.UserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    private static Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleService roleService;

    @Override
    public Set<Role> findRolesByUserId(String userId) {
        if (StringUtils.isBlank(userId)) {
            throw new RuntimeException("param userId is null or empty");
        }

        List<String> roleIds = userRoleMapper.getRoleIdsByUserId(userId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return new HashSet<>(0);
        }

        return new HashSet<>(roleService.listRoles(roleIds));
    }

    @Override
    public Set<String> getRoleNames(String userName) {
        return userRoleMapper.getRoleNamesByUserName(userName);
    }

    @Override
    public void saveUserRole(UserRole userRole) {
        userRoleMapper.saveUserRole(userRole);
    }
}
