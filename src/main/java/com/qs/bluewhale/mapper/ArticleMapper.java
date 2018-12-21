package com.qs.bluewhale.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.qs.bluewhale.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper extends BaseMapper<Article> {

    Page<Article> selectArticlePage(@Param("article") Article article);

    Page<Article> listArticles(@Param("article") Article article);

    void deleteByArticleIds(@Param("articleIds") List<String> articleIds);
}
