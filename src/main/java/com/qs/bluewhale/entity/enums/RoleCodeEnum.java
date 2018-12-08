package com.qs.bluewhale.entity.enums;

public enum RoleCodeEnum {
    USER("user", "用户"),
    ADMIN("admin", "管理员");

    private String code;
    private String name;

    RoleCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
