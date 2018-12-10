package com.qs.bluewhale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qs.bluewhale.entity.base.SuperEntity;
import lombok.Data;

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

    private String content;

    private String previewContent;
}
