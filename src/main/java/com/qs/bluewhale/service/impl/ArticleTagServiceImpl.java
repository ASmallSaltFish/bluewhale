package com.qs.bluewhale.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qs.bluewhale.entity.ArticleTag;
import com.qs.bluewhale.mapper.ArticleTagMapper;
import com.qs.bluewhale.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("articleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Override
    public Map<String, Set<String>> getArticleIdAndTagNamesMap(List<String> articleIds) {
        List<ArticleTag> articleTagList = articleTagMapper.findArticleTagListByArticleIds(articleIds);
        Map<String, Set<String>> articleIdAndTagNamesMap = new HashMap<>();
        for (ArticleTag articleTag : articleTagList) {
            String articleId = articleTag.getArticleId();
            String tagName = articleTag.getTagName();
            Set<String> tagNameSet = articleIdAndTagNamesMap.computeIfAbsent(articleId, k -> new HashSet<>());
            tagNameSet.add(tagName);
        }

        return articleIdAndTagNamesMap;
    }
}
