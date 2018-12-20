package com.qs.bluewhale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.bluewhale.entity.Article;
import com.qs.bluewhale.mapper.ArticleMapper;
import com.qs.bluewhale.service.ArticleService;
import com.qs.bluewhale.service.ArticleTagService;
import com.qs.bluewhale.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Resource
    private ArticleTagService articleTagService;

    @Override
    public List<Article> findArticlesByUserId(String userId) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("create_by", userId);
        return list(queryWrapper);
    }

    @Override
    public Page<Article> listArticlesPage(Article article, Page<Article> page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return articleMapper.selectArticlePage(article);
    }

    @Override
    public Page<Article> listArticlesPage(int pageNum, int pageSize, Article article) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Article> articlePage = articleMapper.listArticles(article);
        List<Article> articleList = articlePage.getResult();
        if (CollectionUtils.isEmpty(articleList)) {
            return articlePage;
        }

        List<String> articleIds = new ArrayList<>(articleList.size());
        for (Article art : articleList) {
            articleIds.add(art.getArticleId());
        }

        //获取文章的标签
        Map<String, Set<String>> articleIdAndTagNamesMap = articleTagService.getArticleIdAndTagNamesMap(articleIds);
        for (Article art : articleList) {
            Set<String> tagNameSet = articleIdAndTagNamesMap.get(art.getArticleId());
            art.setTagNames(CollectionUtils.isEmpty(tagNameSet) ? "暂无标签~" : CommonUtil.collectionToString(tagNameSet, ","));

            //获取文章封面
            String imageCover = art.getImageCover();
            if (StringUtils.isBlank(imageCover)) {
                continue;
            }

            art.setBase64ImageCover(CommonUtil.imageToBase64String(imageCover));
        }

        return articlePage;
    }

    @Override
    public Article findArticleById(String articleId) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", articleId);
        return getOne(queryWrapper);
    }

    @Override
    public int countByUserId(String userId) {
        if (StringUtils.isBlank(userId)) {
            throw new RuntimeException("param userId is null or empty");
        }

        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("create_by", userId);
        return count(queryWrapper);
    }

    @Override
    public void update(Article article) {
        String articleId = article.getArticleId();
        if (StringUtils.isBlank(articleId)) {
            throw new RuntimeException("param articleId is null or empty");
        }

        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", articleId);
        update(article, queryWrapper);
    }

    @Override
    public void deleteByArticleIds(List<String> articleId) {
        articleMapper.deleteByArticleIds(articleId);
    }


}
