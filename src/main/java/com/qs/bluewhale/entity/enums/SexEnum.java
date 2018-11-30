package com.qs.bluewhale.entity.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 用户性别枚举类
 */
public enum SexEnum implements IEnum<Integer> {

    MAN(1, "男"),
    WOMAN(2, "女");

    private Integer value;
    private String desc;

    SexEnum(final Integer value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
