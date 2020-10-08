package com.mola.core.consts.enu;

import lombok.extern.slf4j.Slf4j;

/**
 * 接口请求状态
 *
 * @author hatim
 */
@Slf4j
public enum RequestStatusEnu {
    SUCCESS("请求成功"),
    EXCEPTION("请求异常"),
    UNKNOWN_EXCEPTION("未知错误");

    private String desc;

    RequestStatusEnu(String desc) {
        this.desc = desc;
    }

    public static RequestStatusEnu name(String name) {
        for (RequestStatusEnu enu : RequestStatusEnu.values()) {
            if (enu.name().equals(name)) {
                return enu;
            }
        }
        throw new RuntimeException("对应枚举不存在[" + Thread.currentThread().getStackTrace()[1].getClassName() + "][" + name + "]");
    }

    public static RequestStatusEnu desc(String desc) {
        for (RequestStatusEnu enu : RequestStatusEnu.values()) {
            if (enu.desc.equals(desc)) {
                return enu;
            }
        }
        throw new RuntimeException("对应枚举不存在[" + Thread.currentThread().getStackTrace()[1].getClassName() + "][" + desc + "]");
    }

    @Override
    public String toString() {
        return desc;
    }
}
