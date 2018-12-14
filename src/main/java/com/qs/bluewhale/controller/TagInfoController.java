package com.qs.bluewhale.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qs.bluewhale.controller.base.BaseController;
import com.qs.bluewhale.entity.TagInfo;
import com.qs.bluewhale.service.TagInfoService;
import com.qs.bluewhale.utils.ExecutionContext;
import com.qs.bluewhale.utils.PageUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tag")
public class TagInfoController extends BaseController {

    @Resource
    private TagInfoService tagInfoService;

    @ResponseBody
    @RequestMapping(value = "/listTagInfos")
    public List<TagInfo> listTagInfos() {
        return tagInfoService.getTagInfoList(ExecutionContext.getUserId());
    }


    /**
     * 获取文章列表
     */
    @RequestMapping(value = "/listTagPage")
    @ResponseBody
    public Map<String, Object> listtagInfoPage(Page<TagInfo> page, TagInfo tagInfo) {
        PageInfo<TagInfo> articlePage = tagInfoService.listArticlesPage(tagInfo, page);
        return PageUtils.wrapPageDataToMap(articlePage);
    }
}
