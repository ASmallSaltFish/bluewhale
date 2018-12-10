package com.qs.bluewhale.entity.enums;

public enum ArticlePersonalFlagEnum {
    PUBLIC("0", "公开"),
    PRIVATE("1", "私有");

    private String code;
    private String desc;

    ArticlePersonalFlagEnum(String code, String desc) {
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
