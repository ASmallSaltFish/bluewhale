package com.qs.bluewhale.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qs.bluewhale.controller.base.BaseController;
import com.qs.bluewhale.entity.CategoryInfo;
import com.qs.bluewhale.service.CategoryInfoService;
import com.qs.bluewhale.utils.ExecutionContext;
import com.qs.bluewhale.utils.PageUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/category")
public class CategoryInfoController extends BaseController {

    @Resource
    private CategoryInfoService categoryInfoService;

    @ResponseBody
    @RequestMapping(value = "/listCategoryInfos")
    public List<CategoryInfo> listCategoryInfos() {
        return categoryInfoService.getCategoryInfoList(ExecutionContext.getUserId());
    }


    /**
     * 获取文章列表
     */
    @RequestMapping(value = "/listCategoryPage")
    @ResponseBody
    public Map<String, Object> listtagInfoPage(Page<CategoryInfo> page, CategoryInfo categoryInfo) {
        PageInfo<CategoryInfo> articlePage = categoryInfoService.listCategoryPage(categoryInfo, page);
        return PageUtils.wrapPageDataToMap(articlePage);
    }
}
