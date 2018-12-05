package com.test.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qs.bluewhale.entity.Role;
import com.qs.bluewhale.entity.User;
import com.qs.bluewhale.entity.UserRole;
import com.qs.bluewhale.mapper.UserRoleMapper;
import com.qs.bluewhale.service.RoleService;
import com.qs.bluewhale.service.UserRoleService;
import com.qs.bluewhale.service.UserService;
import com.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class TestMp extends BaseTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;


    @Autowired
    private UserRoleMapper userRoleMapper;

    @Test
    public void test() {
        System.out.println(userService);
        List<User> userList = userService.list(new QueryWrapper<>());
        System.out.println(userList);
        System.out.println(userList.get(0).getSignature());
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUserName("admin");
        user.setSignature("咸鱼也要有大大的梦想~~");
        user.setPassword("admin");
        user.setSex("1");
        userService.save(user);
    }

    @Test
    public void testSaveRole() {
        Role role = new Role();
        role.setRoleName("管理员");
        role.setRoleDesc("测试");
        roleService.save(role);
    }

    @Test
    public void testSaveUserRole() {
        UserRole userRole = new UserRole();
        userRole.setUserId("1069915463289114625");
        userRole.setRoleId("1069918047802437634");
        userRoleService.save(userRole);
    }

    @Test
    public void testFindUserByUserName() {
        User admin = userService.findUserByUserName("admin");
        System.out.println("---->>>" + admin);
    }

    @Test
    public void testFindRolesByUserId() {
        Set<Role> roleSet = userRoleService.findRolesByUserId("1069915463289114625");
        System.out.println("--->>>roleSet=" + roleSet);
    }


    @Test
    public void testGetRoleNamesByUserName() {
        Set<String> roleNames = userRoleMapper.getRoleNamesByUserName("admin");
        System.out.println("--->>>roleNames=" + roleNames);
    }
}
