package com.qs.bluewhale.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qs.bluewhale.controller.base.BaseController;
import com.qs.bluewhale.entity.TagInfo;
import com.qs.bluewhale.service.TagInfoService;
import com.qs.bluewhale.utils.ExecutionContext;
import com.qs.bluewhale.utils.JsonResult;
import com.qs.bluewhale.utils.JsonStatus;
import com.qs.bluewhale.utils.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
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

    /**
     * 跳转到新增标签页面
     */
    @GetMapping(value = "addTag")
    public String addTag(){
        return "tag/addTag";
    }

    /**
     * 保存标签
     */
    @PostMapping(value = "saveTag")
    @ResponseBody
    public JsonResult saveTag(TagInfo tagInfo){
        JsonResult jsonResult=new JsonResult();
        String tagName=tagInfo.getTagName();
        if(StringUtils.isBlank(tagName)){
            jsonResult.setMsg("参数校验出错！");
            return jsonResult;
        }
        tagInfoService.saveOrUpdate(tagInfo);
        jsonResult.setStatus(JsonStatus.SUCCESS);
        return jsonResult;
    }

    /**
     * 跳转修改页面
     */
    @GetMapping(value = "updateTag")
    public String updateTag(TagInfo tagInfo, Model model){
        String tagId=tagInfo.getTagId();
        if(StringUtils.isBlank(tagId)){
            throw new RuntimeException("404");
        }
        tagInfo=tagInfoService.findTagInfoById(tagId);
        model.addAttribute("tagInfo",tagInfo);
        return "tag/updateTag";
    }

    /**
     * 保存修改后的标签
     */
    @PostMapping(value = "saveUpdatedTag")
    @ResponseBody
    public JsonResult saveUpdatedTag(TagInfo tagInfo){
        JsonResult jsonResult=new JsonResult();
        String tagName=tagInfo.getTagName();
        if(StringUtils.isBlank(tagName)){
            jsonResult.setMsg("参数校验存错！");
            return jsonResult;
        }
        tagInfo=tagInfoService.findTagInfoById(tagInfo.getTagId());
        if (tagInfo == null) {
            jsonResult.setMsg("数据库中该标签不存在！");
            return jsonResult;
        }
        tagInfo.setTagName(tagName);
        tagInfo.setLastModifyBy(ExecutionContext.getUserId());
        tagInfo.setLastModifyTime(new Timestamp(new Date().getTime()));
        tagInfoService.updateById(tagInfo);
        jsonResult.setStatus(JsonStatus.SUCCESS);
        return jsonResult;
    }

    /**
     * 删除标签
     */
    @RequestMapping(value = "deleteTag",method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult deleteTag(HttpServletRequest request){
        JsonResult jsonResult=new JsonResult();
        String[] selectedTagIds=request.getParameterValues("selectedTagIds[]");
        if (selectedTagIds==null||selectedTagIds.length==0){
            jsonResult.setMsg("未选择需要删除的标签！");
            return jsonResult;
        }
        tagInfoService.removeByIds(Arrays.asList(selectedTagIds));
        jsonResult.setStatus(JsonStatus.SUCCESS);
        return jsonResult;
    }
}
