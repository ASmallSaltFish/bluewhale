package com.qs.bluewhale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qs.bluewhale.entity.TagInfo;

import java.util.List;

public interface TagInfoService extends IService<TagInfo> {

    List<TagInfo> getTagInfoList(String userId);
}
