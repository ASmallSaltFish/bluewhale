package com.qs.bluewhale.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qs.bluewhale.entity.ArticleTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    List<ArticleTag> findArticleTagListByArticleIds(@Param("articleIds") List<String> articleIds);
}
