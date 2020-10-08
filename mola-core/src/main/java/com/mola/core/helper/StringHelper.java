package com.mola.core.helper;


import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

/**
 * 字符串处理工具
 *
 * @author hatim
 */
public final class StringHelper {
    /**
     * 全角空格
     */
    private static final char EM_SPACE = ' ';
    private static final char EN_SPACE = ' ';

    /**
     * 加强版（处理全角空格）
     *
     * @param str
     * @return
     */
    public static String trimPlus(String str) {
        char[] value = str.toCharArray();
        int len = value.length;
        int st = 0;
        char[] val = value;
        while ((st < len) && (val[st] <= EN_SPACE || val[st] == EM_SPACE)) {
            st++;
        }
        while ((st < len) && (val[len - 1] <= EN_SPACE || val[len - 1] == EM_SPACE)) {
            len--;
        }
        return ((st > 0) || (len < value.length)) ? str.substring(st, len) : str;
    }

    public static boolean equals(final CharSequence cs1, final CharSequence cs2) {
        return StringUtils.equals(cs1, cs2);
    }

    public static boolean notEquals(final CharSequence cs1, final CharSequence cs2) {
        return !StringUtils.equals(cs1, cs2);
    }

    public static boolean equalsIgnoreCase(final CharSequence cs1, final CharSequence cs2) {
        return StringUtils.equalsIgnoreCase(cs1, cs2);
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !StringUtils.isEmpty(cs);
    }

    public static boolean isNotBlank(CharSequence cs) {
        return StringUtils.isNotBlank(cs);
    }

    public static boolean isBlank(CharSequence cs) {
        return StringUtils.isBlank(cs);
    }

    public static String join(Collection collection, String separator) {
        return StringUtils.join(collection, separator);
    }

    public static String[] split(String str, String separatorChars) {
        return StringUtils.split(str, separatorChars);
    }
}
