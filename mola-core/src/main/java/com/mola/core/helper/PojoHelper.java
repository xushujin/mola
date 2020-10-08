package com.mola.core.helper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

/**
 * POJO处理工具
 *
 * @author hatim
 */
public class PojoHelper {
    /**
     * 对象拷贝
     *
     * @param source 源对象
     * @param target 目标对象
     * @throws BeansException
     */
    public static void copyProperties(Object source, Object target) throws BeansException {
        BeanUtils.copyProperties(source, target);
    }
}
