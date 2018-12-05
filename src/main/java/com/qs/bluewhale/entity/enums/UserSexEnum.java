package com.qs.bluewhale.entity.enums;

/**
 * 用户性别枚举
 */
public enum UserSexEnum {
    MAN("1", "男"),
    WOMAN("2", "女"),
    SECRET("3", "保密");

    private String code;

    private String desc;

    UserSexEnum(String code, String desc) {
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
