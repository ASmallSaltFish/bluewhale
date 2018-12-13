package com.qs.bluewhale.controller;

import com.qs.bluewhale.controller.base.BaseController;
import com.qs.bluewhale.entity.Article;
import com.qs.bluewhale.service.ArticleService;
import com.qs.bluewhale.utils.ExecutionContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class IndexController extends BaseController {

    @Resource
    private ArticleService articleService;

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        String userId = ExecutionContext.getUserId();
        if (StringUtils.isBlank(userId)) {
            throw new RuntimeException("用户未登录！");
        }

        List<Article> articleList = articleService.findArticlesByUserId(userId);
        model.addAttribute("articleList", articleList);
        return "/index/index";
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
    public String admin(){
        return "index";
    }
}
