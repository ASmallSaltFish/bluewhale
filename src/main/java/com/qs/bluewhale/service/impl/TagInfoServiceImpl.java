package com.qs.bluewhale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qs.bluewhale.entity.Article;
import com.qs.bluewhale.entity.TagInfo;
import com.qs.bluewhale.mapper.TagInfoMapper;
import com.qs.bluewhale.service.TagInfoService;
import com.qs.bluewhale.utils.ExecutionContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tagInfoService")
public class TagInfoServiceImpl extends ServiceImpl<TagInfoMapper, TagInfo> implements TagInfoService {

    @Override
    public List<TagInfo> getTagInfoList(String userId) {
        if (StringUtils.isBlank(userId)) {
            throw new RuntimeException("param userId is null or empty");
        }

        QueryWrapper<TagInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("create_by", userId);
        return list(queryWrapper);
    }

    @Override
    public PageInfo<TagInfo> listArticlesPage(TagInfo tagInfo, Page<TagInfo> page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        QueryWrapper<TagInfo> queryWrapper = new QueryWrapper<>();
        String keyword = tagInfo.getKeyword();
        if (StringUtils.isNotBlank(keyword)) {
            queryWrapper.like("tag_name", keyword);
        }

        queryWrapper.eq("create_by", ExecutionContext.getUserId());
        return new PageInfo<>(list(queryWrapper));
    }
}
