package com.qs.bluewhale.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qs.bluewhale.controller.base.BaseController;
import com.qs.bluewhale.entity.Article;
import com.qs.bluewhale.entity.User;
import com.qs.bluewhale.entity.enums.ArticlePersonalFlagEnum;
import com.qs.bluewhale.entity.enums.ArticleStatusEnum;
import com.qs.bluewhale.service.ArticleService;
import com.qs.bluewhale.service.UserService;
import com.qs.bluewhale.utils.ExecutionContext;
import com.qs.bluewhale.utils.JsonResult;
import com.qs.bluewhale.utils.JsonStatus;
import com.qs.bluewhale.utils.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping(value = "/article")
public class ArticleController extends BaseController {

    @Resource
    private ArticleService articleService;
    @Autowired
    private UserService userService;

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
        if (articlePage.getResult() != null && articlePage.getResult().size() != 0) {
            for (Article a : articlePage.getResult()) {
                if (StringUtils.isNotBlank(a.getCreateBy())) {
                    User user1 = userService.findUserByUserId(a.getCreateBy());
                    a.setCreateName(user1.getUserName());
                }
                if (StringUtils.isNotBlank(a.getLastModifyBy())) {
                    User user2 = userService.findUserByUserId(a.getLastModifyBy());
                    a.setLasModifyName(user2.getUserName());
                }
                if (StringUtils.isNotBlank(a.getStatus())){
                    if (ArticleStatusEnum.PUBLIAHED.getCode().equals(a.getStatus())){
                        a.setStatus(ArticleStatusEnum.PUBLIAHED.getDesc());
                    }else if (ArticleStatusEnum.DRAFTED.getCode().equals(a.getStatus())){
                        a.setStatus(ArticleStatusEnum.DRAFTED.getDesc());
                    }else if (ArticleStatusEnum.DELETED.getCode().equals(a.getStatus())){
                        a.setStatus(ArticleStatusEnum.DELETED.getDesc());
                    }
                }
            }
        }

        PageInfo<Article> pageInfo = new PageInfo<>(articlePage);
        return PageUtils.wrapPageDataToMap(pageInfo);
    }

    /**
     * 预览文章
     */
    @GetMapping(value = "/previewArticle")
    public String previewArticle(Article article, Model model, HttpServletRequest request) {
        if (StringUtils.isBlank(article.getArticleId())) {
            throw new RuntimeException("404");
        }

        article = articleService.findArticleById(article.getArticleId());
        model.addAttribute("refer", request.getParameter("refer"));
        model.addAttribute("article", article);
        return "/articles/previewArticle";
    }

    /**
     * 修改文章
     * @param article
     * @return
     */
    @PostMapping(value = "/updateArticle")
    @ResponseBody
    public JsonResult updateArticle(Article article) {
        JsonResult jsonResult = new JsonResult();
        Article article1 = articleService.findArticleById(article.getArticleId());
        if (article1 == null) {
            jsonResult.setMsg("该原文章不存在！");
            return jsonResult;
        }
        String lasModifyId = ExecutionContext.getUserId();
        if (StringUtils.isBlank(article.getTitle()) || StringUtils.isBlank(article.getContent())
                || StringUtils.isBlank(article.getPreviewContent()) || StringUtils.isBlank(lasModifyId)) {
            jsonResult.setMsg("参数校验错误！");
            return jsonResult;
        }

        article1.setTitle(article.getTitle());
        article1.setContent(article.getContent());
        article1.setPreviewContent(article.getPreviewContent());
        article1.setLastModifyBy(lasModifyId);
        article1.setLastModifyTime(new Timestamp(new Date().getTime()));
        articleService.updateById(article1);
        jsonResult.setStatus(JsonStatus.SUCCESS);
        return jsonResult;
    }

    /**
     * 发布文章
     */
    @RequestMapping(value = "/publishArticle", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult publishArticle(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String[] articleIds=request.getParameterValues("selectedArticleIds[]");
        if (articleIds==null||articleIds.length==0) {
            jsonResult.setMsg("参数校验错误！");
            return jsonResult;
        }
        List<Article> articles=new ArrayList<>();
        for(int i=0;i<articleIds.length;i++){
            Article article=articleService.findArticleById(articleIds[i]);
            if (ArticleStatusEnum.PUBLIAHED.getCode().equals(article.getStatus())){
                jsonResult.setMsg("选中的文章包含已发布文章！");
                return jsonResult;
            }
            article.setStatus(ArticleStatusEnum.PUBLIAHED.getCode());
            article.setLastModifyTime(new Timestamp(new Date().getTime()));
            articles.add(article);
        }
        articleService.updateBatchById(articles);
        jsonResult.setStatus(JsonStatus.SUCCESS);
        return jsonResult;
    }

    /**
     * 删除文章
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteArticle", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult deleteArticle(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String[] articleIds=request.getParameterValues("selectedArticleIds[]");
        if (articleIds==null||articleIds.length==0) {
            jsonResult.setMsg("参数校验错误！");
            return jsonResult;
        }
        List<Article> articles=new ArrayList<>();
        for(int i=0;i<articleIds.length;i++){
            Article article=articleService.findArticleById(articleIds[i]);
            article.setStatus(ArticleStatusEnum.DELETED.getCode());
            article.setLastModifyTime(new Timestamp(new Date().getTime()));
            articles.add(article);
        }
        articleService.updateBatchById(articles);
        jsonResult.setStatus(JsonStatus.SUCCESS);
        return jsonResult;
    }

    /**
     * 首页查看文章
     */
    @GetMapping(value = "/displayArticle")
    public String displayArticle(Article article, Model model) {
        if (StringUtils.isBlank(article.getArticleId())) {
            throw new RuntimeException("404");
        }

        article = articleService.findArticleById(article.getArticleId());
        model.addAttribute("article", article);
        return "/articles/displayArticle";
    }

}
