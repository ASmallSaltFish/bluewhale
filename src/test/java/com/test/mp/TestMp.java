package com.test.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.qs.bluewhale.entity.*;
import com.qs.bluewhale.entity.enums.ArticlePersonalFlagEnum;
import com.qs.bluewhale.entity.enums.ArticleStatusEnum;
import com.qs.bluewhale.mapper.ArticleMapper;
import com.qs.bluewhale.mapper.ArticleTagMapper;
import com.qs.bluewhale.mapper.UserRoleMapper;
import com.qs.bluewhale.service.*;
import com.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestMp extends BaseTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Resource
    private ArticleService articleService;

    @Resource
    private TagInfoService tagInfoService;


    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleTagMapper articleTagMapper;

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
        role.setRoleName("用户");
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
    public void testSaveActicle() {
        for (int i = 0; i < 200; i++) {
            Article article = new Article();
            article.setAuthor("qinyupeng");
            article.setTitle("这是标题" + (i + 1));
            article.setContent("这是markdown正文");
            article.setPreviewContent("这是markdown预览正文");
            article.setDescription("这个是描述");
            article.setPublishDate(new Date());
            article.setPersonalFlag(ArticlePersonalFlagEnum.PUBLIC.getCode());
            article.setStatus(ArticleStatusEnum.PUBLIAHED.getCode());
            articleService.save(article);
        }

    }

    @Test
    public void testSelectArticlePage() {
        PageHelper.startPage(1, 10);
        Article article = new Article();
        article.setKeyword("markdown");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        article.setPublishEndDate(sdf.format(new Date()));
        article.setPersonalFlag(ArticlePersonalFlagEnum.PUBLIC.getCode());
        articleMapper.selectArticlePage(article);
    }


    @Test
    public void saveTags() {
        List<TagInfo> tagInfos = new ArrayList<>();
        TagInfo tagInfo = new TagInfo();
        tagInfo.setTagName("mysql");
        tagInfo.setCreateBy("1069915463289114625");
        tagInfo.setLastModifyBy("1069915463289114625");
        tagInfos.add(tagInfo);

        TagInfo tagInfo2 = new TagInfo();
        tagInfo2.setTagName("db2");
        tagInfo2.setCreateBy("1069915463289114625");
        tagInfo2.setLastModifyBy("1069915463289114625");
        tagInfos.add(tagInfo2);


        TagInfo tagInfo3 = new TagInfo();
        tagInfo3.setTagName("oracle");
        tagInfo3.setCreateBy("1069915463289114625");
        tagInfo3.setLastModifyBy("1069915463289114625");
        tagInfos.add(tagInfo3);


        TagInfo tagInfo4 = new TagInfo();
        tagInfo4.setTagName("spring");
        tagInfo4.setCreateBy("1069915463289114625");
        tagInfo4.setLastModifyBy("1069915463289114625");
        tagInfos.add(tagInfo4);


        TagInfo tagInfo5 = new TagInfo();
        tagInfo5.setTagName("hibernate");
        tagInfo5.setCreateBy("1069915463289114625");
        tagInfo5.setLastModifyBy("1069915463289114625");
        tagInfos.add(tagInfo5);


        TagInfo tagInfo6 = new TagInfo();
        tagInfo6.setTagName("mybatis");
        tagInfo6.setCreateBy("1069915463289114625");
        tagInfo6.setLastModifyBy("1069915463289114625");
        tagInfos.add(tagInfo6);

        tagInfoService.saveBatch(tagInfos);
    }


    @Test
    public void testfindArticleTagListByArticleIds(){
        List<ArticleTag> articleTagList = articleTagMapper.findArticleTagListByArticleIds(Collections.singletonList("1075648162284957697"));
        System.out.println(articleTagList);
    }
}
