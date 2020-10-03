package com.mola.common.consts.enu;


import lombok.extern.slf4j.Slf4j;

/**
 * 性别
 *
 * @author hatim
 */
@Slf4j
public enum SexEnum {
    male("男性"),
    female("女性"),
    neutral("中性");

    /**
     * 描述
     */
    private String desc;

    SexEnum(String desc) {
        this.desc = desc;
    }

    /**
     * 获取枚举
     *
     * @param name
     * @return
     */
    public static SexEnum ofName(String name) {
        for (SexEnum enu : values()) {
            if (enu.name().equals(name)) {
                return enu;
            }
        }
        throw new RuntimeException("对应枚举不存在[" + Thread.currentThread().getStackTrace()[1].getClassName() + "][" + name + "]");
    }

    public String getDesc() {
        return desc;
    }
}
