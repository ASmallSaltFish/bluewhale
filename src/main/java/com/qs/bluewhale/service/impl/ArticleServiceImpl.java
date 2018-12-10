package com.qs.bluewhale.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qs.bluewhale.entity.Article;
import com.qs.bluewhale.mapper.ArticleMapper;
import com.qs.bluewhale.service.ArticleService;
import org.springframework.stereotype.Service;

@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
