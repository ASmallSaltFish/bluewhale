package com.qs.bluewhale.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qs.bluewhale.controller.base.BaseController;
import com.qs.bluewhale.entity.Article;
import com.qs.bluewhale.entity.TagInfo;
import com.qs.bluewhale.service.ArticleService;
import com.qs.bluewhale.service.TagInfoService;
import com.qs.bluewhale.utils.ExecutionContext;
import com.qs.bluewhale.utils.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class IndexController extends BaseController {

    @Resource
    private ArticleService articleService;

    @Resource
    private TagInfoService tagInfoService;

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        String userId = ExecutionContext.getUserId();
        if (StringUtils.isBlank(userId)) {
            throw new RuntimeException("用户未登录！");
        }

        List<TagInfo> tagInfoList = tagInfoService.getTagInfoList(userId);
        int count = articleService.countByUserId(userId);
        model.addAttribute("tagInfoList", tagInfoList);
        model.addAttribute("count", count);
        return "/index/index";
    }


    /**
     * 获取文章列表
     */
    @RequestMapping(value = "/listArticles")
    @ResponseBody
    public Map<String, Object> listArticles(HttpServletRequest request, Article article) {
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        String tagIdStr = request.getParameter("tagIdStr");
        if (StringUtils.isNotBlank(tagIdStr)) {
            List<String> tagIds = Arrays.asList(tagIdStr.split(","));
            article.setTagIds(tagIds);
        }

        String categoryIdStr = request.getParameter("categoryIds");
        if (StringUtils.isNotBlank(categoryIdStr)) {
            List<String> categoryIds = Arrays.asList(categoryIdStr.split(","));
            article.setCategoryIds(categoryIds);
        }

        Page<Article> articlePage = articleService.listArticlesPage(pageNum, pageSize, article);
        return PageUtils.wrapPageDataToMap(new PageInfo<>(articlePage));
    }

    @GetMapping(value = "/about")
    public String about() {
        return "/index/about";
    }

    @GetMapping(value = "/album")
    public String album() {
        return "/index/album";
    }

    @GetMapping(value = "/details")
    public String details() {
        return "/index/details";
    }

    @GetMapping(value = "/leacots")
    public String leacots() {
        return "/index/leacots";
    }

    @GetMapping(value = "/whisper")
    public String whisper() {
        return "/index/whisper";
    }

    @GetMapping(value = "/test")
    public String test() {
        return "/test";
    }

    //用户信息
    @GetMapping(value = "/userInfo")
    public String userInfo() {
        return "/userInfo";
    }

    @GetMapping(value = "/admin")
    public String admin() {
        return "index";
    }
}
