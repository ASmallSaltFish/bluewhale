package com.qs.bluewhale.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.qs.bluewhale.entity.Article;
import org.apache.ibatis.annotations.Param;

public interface ArticleMapper extends BaseMapper<Article> {

    Page<Article> selectArticlePage(@Param("article") Article article);
}
