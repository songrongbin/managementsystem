package com.bins.managementsystem.user.constants;

/**
 * Created by songrongbin on 2018/1/29.
 */
public enum UserStatusEnum {

    FORBIDDEN(0, "禁止登录"),
    VALID(1, "有效");

    private Integer key;

    private String desc;

    UserStatusEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public static UserStatusEnum fromValue(Integer key) {
        for (UserStatusEnum item : values()) {
            if (key == item.getKey()) return item;
        }
        return null;
    }

    public Integer getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }
}
