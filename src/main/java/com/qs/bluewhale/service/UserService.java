package com.qs.bluewhale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qs.bluewhale.entity.User;

public interface UserService extends IService<User> {

    User findUserByUserName(String userName);
    void saveUser(User user);

}
