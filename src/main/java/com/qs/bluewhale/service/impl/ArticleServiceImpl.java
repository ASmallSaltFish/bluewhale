package com.qs.bluewhale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.bluewhale.entity.Article;
import com.qs.bluewhale.mapper.ArticleMapper;
import com.qs.bluewhale.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Article> findArticlesByUserId(String userId) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("create_by",userId);
        return list(queryWrapper);
    }

    @Override
    public Page<Article> listArticlesPage(Article article, Page<Article> page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return articleMapper.selectArticlePage(article);
    }

    @Override
    public Article findArticleById(String articleId) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id",articleId);
        return getOne(queryWrapper);
    }


}
