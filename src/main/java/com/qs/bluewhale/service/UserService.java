package com.qs.bluewhale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qs.bluewhale.entity.User;

public interface UserService extends IService<User> {

    /**
     * 根据用户名获取用户实体信息
     *
     * @param userName 用户名
     * @return 返回用户实体对象
     */
    User findUserByUserName(String userName);

    /**
     * 判断用户名是否存在
     *
     * @param userName 用户名
     * @return 存在返回true，不存在返回false
     */
    boolean existUserName(String userName);

    /**
     * 保存用户信息
     *
     * @param user 用户实体
     */
    void saveUser(User user);
}
