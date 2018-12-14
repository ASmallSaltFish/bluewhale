package com.qs.bluewhale.controller;

import com.qs.bluewhale.controller.base.BaseController;
import com.qs.bluewhale.entity.TagInfo;
import com.qs.bluewhale.service.TagInfoService;
import com.qs.bluewhale.utils.ExecutionContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/tag")
public class TagInfoController extends BaseController {

    @Resource
    private TagInfoService tagInfoService;

    @ResponseBody
    @RequestMapping(value = "/listTagInfos")
    public List<TagInfo> listTagInfos(){
        return tagInfoService.getTagInfoList(ExecutionContext.getUserId());
    }
}
