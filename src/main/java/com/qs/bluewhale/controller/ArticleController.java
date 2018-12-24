package com.qs.bluewhale.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qs.bluewhale.controller.base.BaseController;
import com.qs.bluewhale.entity.*;
import com.qs.bluewhale.entity.enums.ArticleStatusEnum;
import com.qs.bluewhale.service.*;
import com.qs.bluewhale.utils.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.imageio.stream.FileImageInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping(value = "/article")
public class ArticleController extends BaseController {

    @Resource
    private ArticleService articleService;

    @Resource
    private UserService userService;

    @Resource
    private TagInfoService tagInfoService;

    @Resource
    private CategoryInfoService categoryInfoService;

    @Resource
    private ArticleTagService articleTagService;

    @RequiresRoles("admin")
    @GetMapping(value = "/addArticle")
    public String addArticle(HttpServletRequest request) {
        String articleId = request.getParameter("articleId");
        Article article = articleService.findArticleById(articleId);
        request.setAttribute("article", article);
        return "/articles/addArticle";
    }

    @PostMapping(value = "/saveArticle")
    @ResponseBody
    public JsonResult saveArticle(Article article, HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String title = article.getTitle();
        String description = article.getDescription();
        String author = ExecutionContext.getUserName();
        if (StringUtils.isBlank(title) || StringUtils.isBlank(description)) {
            jsonResult.setMsg("参数校验错误！");
            return jsonResult;
        }

        String tagIdStr = request.getParameter("tagIdStr");
        List<String> tagIds = null;
        if (StringUtils.isNotBlank(tagIdStr)) {
            tagIds = Arrays.asList(tagIdStr.split(","));
        }

        String articleId = IdWorker.getIdStr();
        article.setArticleId(articleId);
        List<ArticleTag> articleTagList = null;
        if (CollectionUtils.isNotEmpty(tagIds)&&tagIds.size()!=0) {
            articleTagList = new ArrayList<>(tagIds.size());
            for (String tagId : tagIds) {
                ArticleTag articleTag = new ArticleTag();
                articleTag.setArticleId(articleId);
                articleTag.setTagId(tagId);
                articleTagList.add(articleTag);
            }
            //保存文章和标签关联信息
            articleTagService.saveBatch(articleTagList);
        }

        article.setAuthor(author);
        //草稿状态
        article.setStatus(ArticleStatusEnum.DRAFTED.getCode());
        //保存文章信息
        articleService.save(article);

        jsonResult.setStatus(JsonStatus.SUCCESS);
        jsonResult.setData(article);
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
                /*if (StringUtils.isNotBlank(a.getCreateBy())) {
                    User user1 = userService.findUserByUserId(a.getCreateBy());
                    a.setCreateName(user1.getUserName());
                }
                if (StringUtils.isNotBlank(a.getLastModifyBy())) {
                    User user2 = userService.findUserByUserId(a.getLastModifyBy());
                    a.setLasModifyName(user2.getUserName());
                }*/
                if (StringUtils.isNotBlank(a.getStatus())) {
                    if (ArticleStatusEnum.PUBLIAHED.getCode().equals(a.getStatus())) {
                        a.setStatus(ArticleStatusEnum.PUBLIAHED.getDesc());
                    } else if (ArticleStatusEnum.DRAFTED.getCode().equals(a.getStatus())) {
                        a.setStatus(ArticleStatusEnum.DRAFTED.getDesc());
                    } else if (ArticleStatusEnum.DELETED.getCode().equals(a.getStatus())) {
                        a.setStatus(ArticleStatusEnum.DELETED.getDesc());
                    }
                }
                if (StringUtils.isNotBlank(a.getCategoryId())) {
                    CategoryInfo categoryInfo = categoryInfoService.findCategoryById(a.getCategoryId());
                    a.setCategoryNames(categoryInfo.getCategoryName());
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
     *
     * @param article
     * @return
     */
    @PostMapping(value = "/updateArticle")
    @ResponseBody
    public JsonResult updateArticle(Article article) {
        JsonResult jsonResult = new JsonResult();
        Article articleFromDB = articleService.findArticleById(article.getArticleId());
        if (articleFromDB == null) {
            jsonResult.setMsg("数据库中该文章不存在！");
            return jsonResult;
        }

        articleFromDB.setTitle(article.getTitle());
        articleFromDB.setContent(article.getContent());
        articleFromDB.setPreviewContent(article.getPreviewContent());
        articleFromDB.setLastModifyBy(ExecutionContext.getUserId());
        articleFromDB.setLastModifyTime(new Timestamp(new Date().getTime()));
        articleService.updateById(articleFromDB);
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

        List<String> asList=Arrays.asList(articleIds); //将数组转换为List集合
        List<Article> articleList= (List<Article>) articleService.listByIds(asList); //批量查找
        for(Article article:articleList){
            //Article article=articleService.findArticleById(articleIds[i]);
            if (ArticleStatusEnum.PUBLIAHED.getCode().equals(article.getStatus())) {
                jsonResult.setMsg("选中的文章包含已发布文章！");
                return jsonResult;
            }
            article.setStatus(ArticleStatusEnum.PUBLIAHED.getCode());
            article.setLastModifyTime(new Timestamp(new Date().getTime()));
        }
        articleService.updateBatchById(articleList);
        jsonResult.setStatus(JsonStatus.SUCCESS);
        return jsonResult;
    }

    /**
     * 删除文章
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteArticle", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult deleteArticle(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String articleIdStr = request.getParameter("articleIds");
        if (StringUtils.isBlank(articleIdStr)) {
            jsonResult.setMsg("未选中任何需要删除的文章！");
            return jsonResult;
        }

        articleService.deleteByArticleIds(Arrays.asList(articleIdStr.split(",")));
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
        //更新预览数
        article.setViewCount(article.getViewCount() + 1);
        articleService.update(article);
        model.addAttribute("article", article);
        return "/articles/displayArticle";
    }

    @GetMapping(value = "/addArticleForm")
    public String addArticleForm(Model model) {
        String userId = ExecutionContext.getUserId();
        //标签列表
        List<TagInfo> tagInfoList = tagInfoService.getTagInfoList(userId);
        model.addAttribute("tagList", tagInfoList);

        //类别列表
        List<CategoryInfo> categoryInfoList = categoryInfoService.getCategoryInfoList(userId);
        model.addAttribute("categoryList", categoryInfoList);
        return "/articles/addArticleForm";
    }

    @PostMapping(value = "/uploadImgFile")
    @ResponseBody
    public JsonResult uploadImgFile(HttpServletRequest request, String articleId) {
        JsonResult jsonResult = new JsonResult();
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile uploadImgFile = multipartHttpServletRequest.getFile("uploadImgFile");
        if (uploadImgFile == null) {
            jsonResult.setMsg("上传文件不存在哟~");
            return jsonResult;
        }

        try {
            String orgFileName = uploadImgFile.getOriginalFilename();
            if (StringUtils.isBlank(orgFileName)) {
                jsonResult.setMsg("上传文件不存在哟~");
                return jsonResult;
            }

            String suffix = orgFileName.substring(orgFileName.lastIndexOf("."));
            InputStream inputStream = uploadImgFile.getInputStream();
            String uploadFilePath = ConfigConstants.getParam("upload.article.imgFilePath") + articleId + suffix;
            File file = new File(uploadFilePath);
            File parentDir = new File(file.getParent());
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            //将获取到的字节数据写入到指定的文件中
            OutputStream outputStream = new FileOutputStream(file);
            byte[] buff = new byte[1024];
            while (-1 != (inputStream.read(buff))) {
                outputStream.write(buff, 0, buff.length);
            }

            //在文件比较小的情况下，可以直接写入获取到的字节
            //outputSream.write(mulFile.getBytes());
            outputStream.flush();
            inputStream.close();
            outputStream.close();

            //更新文章记录
            Article article = new Article();
            article.setArticleId(articleId);
            article.setImageCover(uploadFilePath);
            articleService.update(article);
        } catch (IOException e) {
            logger.error("上传文件出现错误", e);
            jsonResult.setMsg("未知错误！");
        }

        jsonResult.setMsg("上传文件成功!");
        jsonResult.setStatus(JsonStatus.SUCCESS);
        return jsonResult;
    }


    //展示图片
    @PostMapping(value = "/showImageCover")
    @ResponseBody
    public JsonResult showImageCover(String imageFilePath) {
        JsonResult jsonResult = new JsonResult();
        if (StringUtils.isBlank(imageFilePath)) {
            jsonResult.setMsg("图片不存在！");
            return jsonResult;
        }

        File file = new File(imageFilePath);
        FileImageInputStream inputStream = null;
        try {
            inputStream = new FileImageInputStream(file);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while (-1 != (len = inputStream.read(buffer))) {
                outputStream.write(buffer, 0, len);
            }

            //使用字节输出流
            String base64Str = Base64.getEncoder().encodeToString(outputStream.toByteArray());
            jsonResult.setData(base64Str);
            jsonResult.setStatus(JsonStatus.SUCCESS);
            return jsonResult;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
