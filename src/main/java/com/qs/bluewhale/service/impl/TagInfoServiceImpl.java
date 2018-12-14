package com.qs.bluewhale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qs.bluewhale.entity.TagInfo;
import com.qs.bluewhale.mapper.TagInfoMapper;
import com.qs.bluewhale.service.TagInfoService;
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
        queryWrapper.eq("create_by",userId);
        return list(queryWrapper);
    }
}
