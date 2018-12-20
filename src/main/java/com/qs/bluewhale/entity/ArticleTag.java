package com.qs.bluewhale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qs.bluewhale.entity.base.SuperEntity;
import lombok.Data;

@Data
@TableName("t_bw_article_tag")
public class ArticleTag extends SuperEntity<ArticleTag> {

    @TableId(type = IdType.ID_WORKER_STR)
    private String articleTagId;

    private String tagId;

    private String articleId;


    /*********非持久化字段************/
    @TableField(exist = false)
    private Article article;

    @TableField(exist = false)
    private TagInfo tagInfo;

    @TableField(exist = false)
    private String tagName;
}
