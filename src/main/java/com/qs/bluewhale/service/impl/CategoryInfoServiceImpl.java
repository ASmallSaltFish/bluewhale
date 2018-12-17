package com.qs.bluewhale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qs.bluewhale.entity.CategoryInfo;
import com.qs.bluewhale.mapper.CategoryInfoMapper;
import com.qs.bluewhale.service.CategoryInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryInfoService")
public class CategoryInfoServiceImpl extends ServiceImpl<CategoryInfoMapper, CategoryInfo> implements CategoryInfoService {

    @Override
    public List<CategoryInfo> getCategoryInfoList(String userId) {
        if (StringUtils.isBlank(userId)) {
            throw new RuntimeException("param userId is null or empty");
        }

        QueryWrapper<CategoryInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("create_by", userId);
        return list(queryWrapper);
    }

    @Override
    public PageInfo<CategoryInfo> listCategoryPage(CategoryInfo categoryInfo, Page<CategoryInfo> page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        QueryWrapper<CategoryInfo> queryWrapper = new QueryWrapper<>();
        String keyword = categoryInfo.getKeyword();
        if (StringUtils.isNotBlank(keyword)) {
            queryWrapper.like("category_name", keyword);
        }

        //queryWrapper.eq("create_by", ExecutionContext.getUserId());
        return new PageInfo<>(list(queryWrapper));
    }
}
