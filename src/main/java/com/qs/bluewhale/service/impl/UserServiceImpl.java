package com.qs.bluewhale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qs.bluewhale.entity.Role;
import com.qs.bluewhale.entity.User;
import com.qs.bluewhale.entity.UserRole;
import com.qs.bluewhale.entity.enums.RoleCodeEnum;
import com.qs.bluewhale.mapper.UserMapper;
import com.qs.bluewhale.service.RoleService;
import com.qs.bluewhale.service.UserRoleService;
import com.qs.bluewhale.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public User findUserByUserName(String userName) {
        if (StringUtils.isBlank(userName)) {
            throw new RuntimeException("param userName is null or empty");
        }

        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        User loginUser = getOne(queryWrapper);
        if (loginUser == null) {
            return loginUser;
        }

        Set<String> roleCodes = roleService.findRoleCodesByUserName(userName);
        loginUser.setRoleCodes(roleCodes);
        return loginUser;
    }

    @Override
    public boolean existUserName(String userName) {
        if (StringUtils.isBlank(userName)) {
            throw new RuntimeException("param userName is null or empty");
        }

        return findUserByUserName(userName) != null;
    }

    @Override
    public void saveUser(User user) {
        Role role = roleService.findByRoleCode(RoleCodeEnum.USER.getCode());

        UserRole userRole = new UserRole();
        userRole.setUserRoleId(IdWorker.getIdStr());
        userRole.setUserId(user.getUserId());
        userRole.setRoleId(role.getRoleId());
        userRole.setCreateBy(user.getUserId());
        userRole.setLastModifyBy(user.getUserId());
        userRoleService.save(userRole);
        save(user);
    }

    @Override
    public User findUserByUserId(String userId) {
        if (StringUtils.isBlank(userId)) {
            throw new RuntimeException("param userName is null or empty");
        }

        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        User user = getOne(queryWrapper);
        if (user == null) {
            return user;
        }

        return user;
    }
}
