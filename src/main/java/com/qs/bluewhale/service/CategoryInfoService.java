package com.qs.bluewhale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qs.bluewhale.entity.CategoryInfo;

import java.util.List;

public interface CategoryInfoService extends IService<CategoryInfo> {

    List<CategoryInfo> getCategoryInfoList(String userId);

    PageInfo<CategoryInfo> listCategoryPage(CategoryInfo categoryInfo, Page<CategoryInfo> page);
}
