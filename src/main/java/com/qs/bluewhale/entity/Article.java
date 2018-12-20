package com.qs.bluewhale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qs.bluewhale.entity.base.SuperEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@TableName("t_bw_article")
public class Article extends SuperEntity<Article> {

    @TableId(type = IdType.ID_WORKER_STR)
    private String articleId;

    private String title;

    private String author;

    //0-草稿，1-已发布，2-已删除
    private String status;

    //0-公共，1-私有
    private String personalFlag;

    private String imageCover;

    private String categoryId;

    private String content;

    private String previewContent;

    private String description;

    private Date publishDate;

    //预览数
    private int viewCount;

    //评论数
    private int commentCount;

    /*************非持久化字段*******************/
    @TableField(exist = false)
    private String publishStartDate;

    @TableField(exist = false)
    private String publishEndDate;

    @TableField(exist = false)
    private String keyword;

    @TableField(exist = false)
    private String createName;

    @TableField(exist = false)
    private String lasModifyName;

    @TableField(exist = false)
    private List<String> tagIds;

    @TableField(exist = false)
    private String tagNames;

    @TableField(exist = false)
    private List<String> categoryIds;

    @TableField(exist = false)
    private String categoryNames;

    @TableField(exist = false)
    private String orderBy;
}
