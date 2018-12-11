package com.qs.bluewhale.controller;

import com.qs.bluewhale.controller.base.BaseController;
import com.qs.bluewhale.entity.Article;
import com.qs.bluewhale.entity.enums.ArticlePersonalFlagEnum;
import com.qs.bluewhale.entity.enums.ArticleStatusEnum;
import com.qs.bluewhale.service.ArticleService;
import com.qs.bluewhale.utils.ExecutionContext;
import com.qs.bluewhale.utils.JsonResult;
import com.qs.bluewhale.utils.JsonStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/article")
public class ArticleController extends BaseController {

    @Resource
    private ArticleService articleService;

    @GetMapping(value = "/example")
    public String example() {
        return "articles/example";
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
