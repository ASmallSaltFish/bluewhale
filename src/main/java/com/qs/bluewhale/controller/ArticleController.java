package com.qs.bluewhale.controller;

import com.qs.bluewhale.controller.base.BaseController;
import com.qs.bluewhale.entity.Article;
import com.qs.bluewhale.service.ArticleService;
import com.qs.bluewhale.utils.ExecutionContext;
import com.qs.bluewhale.utils.JsonResult;
import com.qs.bluewhale.utils.JsonStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/article")
public class ArticleController extends BaseController {

    @Resource
    private ArticleService articleService;

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
        articleService.save(article);
        jsonResult.setStatus(JsonStatus.SUCCESS);
        return jsonResult;
    }
}
