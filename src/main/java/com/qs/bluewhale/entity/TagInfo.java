package com.qs.bluewhale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qs.bluewhale.entity.base.SuperEntity;
import lombok.Data;

@Data
@TableName("t_bw_tag")
public class TagInfo extends SuperEntity<TagInfo> {

    @TableId(type = IdType.ID_WORKER_STR)
    private String tagId;

    private String tagName;
}
