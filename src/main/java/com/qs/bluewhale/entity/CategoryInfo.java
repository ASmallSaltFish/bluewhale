package com.qs.bluewhale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qs.bluewhale.entity.base.SuperEntity;
import lombok.Data;

@Data
@TableName("t_bw_article_category")
public class CategoryInfo extends SuperEntity<CategoryInfo> {

    @TableId(type = IdType.ID_WORKER_STR)
    private String categoryId;

    private String categoryName;
    private String parentId;


    /**********非持久化字段*************/
    @TableField(exist = false)
    private String keyword;
}
