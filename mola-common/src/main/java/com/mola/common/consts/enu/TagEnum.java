package com.mola.common.consts.enu;


import lombok.extern.slf4j.Slf4j;

/**
 * 数据标签
 *
 * @author hatim
 */
@Slf4j
public enum TagEnum {
    normal("正常数据", 0),
    del("删除数据", 1),
    abnormal("异常数据", 9);

    /**
     * 描述
     */
    private String desc;
    /**
     * code
     */
    private Integer code;

    TagEnum(String desc, Integer code) {
        this.desc = desc;
        this.code = code;
    }

    /**
     * 获取枚举
     *
     * @param code
     * @return
     */
    public static TagEnum ofCode(String code) {
        for (TagEnum enu : values()) {
            if (enu.getCode().equals(code)) {
                return enu;
            }
        }
        throw new RuntimeException("对应枚举不存在[" + Thread.currentThread().getStackTrace()[1].getClassName() + "][" + code + "]");
    }

    public String getDesc() {
        return desc;
    }

    public Integer getCode() {
        return code;
    }
}
