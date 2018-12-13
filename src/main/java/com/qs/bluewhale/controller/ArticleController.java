package com.qs.bluewhale.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qs.bluewhale.controller.base.BaseController;
import com.qs.bluewhale.entity.Article;
import com.qs.bluewhale.entity.enums.ArticlePersonalFlagEnum;
import com.qs.bluewhale.entity.enums.ArticleStatusEnum;
import com.qs.bluewhale.service.ArticleService;
import com.qs.bluewhale.utils.ExecutionContext;
import com.qs.bluewhale.utils.JsonResult;
import com.qs.bluewhale.utils.JsonStatus;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/article")
public class ArticleController extends BaseController {

    @Resource
    private ArticleService articleService;

    @RequiresRoles("admin")
    @GetMapping(value = "/addArticle")
    public String addArticle() {
        return "/articles/addArticle";
    }

    @PostMapping(value = "/saveArticle")
    @ResponseBody
    public JsonResult saveArticle(Article article) {
        JsonResult jsonResult = new JsonResult();
        String title = article.getTitle();
        String content = article.getContent();
        String previewContent = article.getPreviewContent();
        String author = ExecutionContext.getUserName();
        if (StringUtils.isBlank(title) || StringUtils.isBlank(content)
                || StringUtils.isBlank(previewContent) || StringUtils.isBlank(author)) {
            jsonResult.setMsg("参数校验错误！");
            return jsonResult;
        }

        article.setAuthor(author);
        //草稿状态
        article.setStatus(ArticleStatusEnum.DRAFTED.getCode());
        //先默认为公开
        article.setPersonalFlag(ArticlePersonalFlagEnum.PUBLIC.getCode());
        articleService.save(article);
        jsonResult.setStatus(JsonStatus.SUCCESS);
        return jsonResult;
    }

    /**
     * 获取文章列表
     */
    @RequestMapping(value = "/listArticles")
    @ResponseBody
    public Map<String, Object> listArticles(Page<Article> page, Article article) {
        Page<Article> articlePage = articleService.listArticlesPage(article, page);
        PageInfo<Article> pageInfo = new PageInfo<>(articlePage);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("count", pageInfo.getTotal());
        dataMap.put("data", pageInfo.getList());
        dataMap.put("code", 0);
        return dataMap;
    }

    @GetMapping(value = "/previewArticle")
    public String previewArticle(String articleId, Model model) {
        if (StringUtils.isBlank(articleId)) {
            throw new RuntimeException("404");
        }

        Article article = articleService.getById(articleId);
        model.addAttribute("article", article);
        return "/articles/previewArticle";
    }

}
