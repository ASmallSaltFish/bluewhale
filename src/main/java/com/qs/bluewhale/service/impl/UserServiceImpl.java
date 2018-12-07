package com.qs.bluewhale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qs.bluewhale.entity.User;
import com.qs.bluewhale.mapper.UserMapper;
import com.qs.bluewhale.service.UserService;
import com.qs.bluewhale.utils.PwdEncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUserByUserName(String userName) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        return getOne(queryWrapper);
    }

    @Override
    public void saveUser(User user) {
        String salt = user.getUserId() + user.getUserName();
        user.setPassword(PwdEncryptUtil.getPassword(user.getPassword(),salt));
        userMapper.saveUser(user);
    }
}
