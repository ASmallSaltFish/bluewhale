package com.qs.bluewhale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qs.bluewhale.entity.TagInfo;

import java.util.List;

public interface TagInfoService extends IService<TagInfo> {

    List<TagInfo> getTagInfoList(String userId);

    PageInfo<TagInfo> listArticlesPage(TagInfo tagInfo, Page<TagInfo> page);

    TagInfo findTagInfoById(String tagId);
}
