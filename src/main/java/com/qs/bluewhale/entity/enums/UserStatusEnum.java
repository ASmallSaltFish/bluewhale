package com.qs.bluewhale.entity.enums;

/**
 * 用户状态枚举
 *
 * @author qinyupeng
 * @since 2018-12-05 18:15:42
 */
public enum UserStatusEnum {
    ACTIVE("1", "正常"),
    LOCKED("2", "锁定"),
    DISABLED("3", "禁用");

    private String code;

    private String desc;

    UserStatusEnum(String code, String desc) {
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
