package com.qs.bluewhale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.Page;
import com.qs.bluewhale.entity.Article;

import java.util.List;

public interface ArticleService extends IService<Article> {

    List<Article> findArticlesByUserId(String userId);

    Page<Article> listArticlesPage(Article article, Page<Article> page);
    Article findArticleById(String articleId);
}
