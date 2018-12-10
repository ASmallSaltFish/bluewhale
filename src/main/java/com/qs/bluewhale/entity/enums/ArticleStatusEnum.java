package com.qs.bluewhale.entity.enums;

/**
 * 博客文章状态枚举
 */
public enum ArticleStatusEnum {

    DRAFTED("0", "草稿"),
    PUBLIAHED("1", "已发布"),
    DELETED("2", "已删除");

    private String code;
    private String desc;

    ArticleStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
