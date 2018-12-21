package com.qs.bluewhale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qs.bluewhale.entity.ArticleTag;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ArticleTagService extends IService<ArticleTag> {

    Map<String, Set<String>> getArticleIdAndTagNamesMap(List<String> articleIds);
}
