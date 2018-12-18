package com.qs.bluewhale.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qs.bluewhale.controller.base.BaseController;
import com.qs.bluewhale.entity.CategoryInfo;
import com.qs.bluewhale.service.CategoryInfoService;
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
import java.util.ArrayList;
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
    public Map<String, Object> listCategoryPage(Page<CategoryInfo> page, CategoryInfo categoryInfo) {
        PageInfo<CategoryInfo> articlePage = categoryInfoService.listCategoryPage(categoryInfo, page);
        return PageUtils.wrapPageDataToMap(articlePage);
    }

    /**
     * 跳转到增加类别页面
     */
    @GetMapping(value = "/addCategory")
    public String addCategory() {
        return "/category/addCategory";
    }

    /**
     * 保存类别
     */
    @PostMapping(value = "saveCategory")
    @ResponseBody
    public JsonResult saveCategory(CategoryInfo categoryInfo){
        JsonResult jsonResult=new JsonResult();
        String categoryName=categoryInfo.getCategoryName();
        if (StringUtils.isBlank(categoryName)){
            jsonResult.setMsg("参数校验错误！");
            return jsonResult;
        }
        categoryInfoService.save(categoryInfo);
        jsonResult.setStatus(JsonStatus.SUCCESS);
        return jsonResult;
    }

    /**
     * 跳转修改页
     */
    @GetMapping(value = "/updateCategory")
    public String updateCategory(CategoryInfo categoryInfo, Model model) {
        if (StringUtils.isBlank(categoryInfo.getCategoryId())) {
            throw new RuntimeException("404");
        }

        categoryInfo = categoryInfoService.findCategoryById(categoryInfo.getCategoryId());
        //model.addAttribute("refer", request.getParameter("refer"));
        model.addAttribute("categoryInfo", categoryInfo);
        return "/category/updateCategory";
    }

    /**
     * 删除类别
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteCategory", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult deleteCategory(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String[] categoryIds=request.getParameterValues("selectedCategoryIds[]");
        if (categoryIds==null||categoryIds.length==0) {
            jsonResult.setMsg("参数校验错误！");
            return jsonResult;
        }
        List<String> categoryInfos=new ArrayList<>();
        for(int i=0;i<categoryIds.length;i++){
            String categoryId=categoryInfoService.findCategoryById(categoryIds[i]).getCategoryId();
            /*categoryInfo.setStatus(ArticleStatusEnum.DELETED.getCode());
            categoryInfo.setLastModifyTime(new Timestamp(new Date().getTime()));*/
            categoryInfos.add(categoryId);
        }
        categoryInfoService.removeByIds(categoryInfos);
        jsonResult.setStatus(JsonStatus.SUCCESS);
        return jsonResult;
    }

}
