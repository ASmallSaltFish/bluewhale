package com.test.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qs.bluewhale.entity.User;
import com.qs.bluewhale.entity.enums.SexEnum;
import com.qs.bluewhale.service.UserService;
import com.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestMp extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        System.out.println(userService);
        List<User> userList = userService.list(new QueryWrapper<>());
        System.out.println(userList);
        System.out.println(userList.get(0).getSignature());
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setUserName("admin");
        user.setSignature("咸鱼也要有大大的梦想~~");
        user.setPassword("admin");
        user.setSex(SexEnum.MAN);
        userService.save(user);
    }
}
