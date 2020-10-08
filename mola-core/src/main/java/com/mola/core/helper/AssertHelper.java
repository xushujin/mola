package com.mola.core.helper;

import com.mola.core.response.failure.MolaException;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * 断言工具类
 *
 * @author hatim
 */
public final class AssertHelper {

    /**
     * 直接抛异常
     *
     * @param httpStatus
     * @param msg
     */
    public static void cast(HttpStatus httpStatus, String msg) {
        fail(httpStatus.value(), msg);
    }
    // ===================（以上为业务事件断言）华丽的分割线（以下为异常断言）=================== //

    /**
     * 业务事件code
     *
     * @param condition
     * @param enu
     */
    public static void event(boolean condition, Enum enu) {
        if (!condition) {
            fail(true, enu.name(), enu.toString());
        }
    }

    /**
     * 业务事件code
     *
     * @param condition
     * @param code
     * @param msg
     */
    public static void event(boolean condition, String code, String msg) {
        if (!condition) {
            fail(true, code, msg);
        }
    }

    // ===================（以上为业务事件断言）华丽的分割线（以下为异常断言）=================== //

    public static void check(boolean condition, HttpStatus httpStatus) {
        if (!condition) {
            fail(httpStatus.value(), httpStatus.getReasonPhrase());
        }
    }

    public static void check(boolean condition, HttpStatus httpStatus, String msg) {
        if (!condition) {
            fail(httpStatus.value(), msg);
        }
    }

    public static void check(boolean condition, int code, String msg) {
        if (!condition) {
            fail(code, msg);
        }
    }

    public static void notEmpty(String str, HttpStatus httpStatus) {
        if (str == null || str.isEmpty()) {
            fail(httpStatus.value(), httpStatus.getReasonPhrase());
        }
    }

    public static void notEmpty(String str, HttpStatus httpStatus, String msg) {
        if (str == null || str.isEmpty()) {
            fail(httpStatus.value(), msg);
        }
    }

    public static void notEmpty(String str, int code, String msg) {
        if (str == null || str.isEmpty()) {
            fail(code, msg);
        }
    }

    public static void notBlank(String str, HttpStatus httpStatus) {
        if (str == null || StringHelper.trimPlus(str).length() == 0) {
            fail(httpStatus.value(), httpStatus.getReasonPhrase());
        }
    }

    public static void notBlank(String str, HttpStatus httpStatus, String msg) {
        if (str == null || StringHelper.trimPlus(str).length() == 0) {
            fail(httpStatus.value(), msg);
        }
    }

    public static void notBlank(String str, int code, String msg) {
        if (str == null || StringHelper.trimPlus(str).length() == 0) {
            fail(code, msg);
        }
    }

    public static void notNull(Object obj, HttpStatus httpStatus) {
        if (obj == null) {
            fail(httpStatus.value(), httpStatus.getReasonPhrase());
        }
    }

    public static void notNull(Object obj, HttpStatus httpStatus, String msg) {
        if (obj == null) {
            fail(httpStatus.value(), msg);
        }
    }

    public static void notNull(Object obj, int code, String msg) {
        if (obj == null) {
            fail(code, msg);
        }
    }

    public static void listNotEmpty(List list, HttpStatus httpStatus) {
        if (list == null || list.isEmpty()) {
            fail(httpStatus.value(), httpStatus.getReasonPhrase());
        }
    }

    public static void listNotEmpty(List list, HttpStatus httpStatus, String msg) {
        if (list == null || list.isEmpty()) {
            fail(httpStatus.value(), msg);
        }
    }

    public static void listNotEmpty(List list, int code, String msg) {
        if (list == null || list.isEmpty()) {
            fail(code, msg);
        }
    }

    public static void isInstanceOf(Class<?> type, Object obj, HttpStatus httpStatus) {
        if (!type.isInstance(obj)) {
            fail(httpStatus.value(), httpStatus.getReasonPhrase());
        }
    }

    public static void isInstanceOf(Class<?> type, Object obj, HttpStatus httpStatus, String msg) {
        if (!type.isInstance(obj)) {
            fail(httpStatus.value(), msg);
        }
    }

    public static void isInstanceOf(Class<?> type, Object obj, int code, String msg) {
        if (!type.isInstance(obj)) {
            fail(code, msg);
        }
    }

    public static void isContains(String textToSearch, String substring, HttpStatus httpStatus) {
        if (!textToSearch.contains(substring)) {
            fail(httpStatus.value(), httpStatus.getReasonPhrase());
        }
    }

    public static void isContains(String textToSearch, String substring, HttpStatus httpStatus, String msg) {
        if (!textToSearch.contains(substring)) {
            fail(httpStatus.value(), msg);
        }
    }

    public static void isContains(String textToSearch, String substring, int code, String msg) {
        if (!textToSearch.contains(substring)) {
            fail(code, msg);
        }
    }

    private static void fail(int code, String msg) {
        throw MolaException.builder().code(String.valueOf(code)).msg(msg).build();
    }

    private static void fail(boolean isOk, String code, String msg) {
        throw MolaException.builder().isOk(isOk).code(code).msg(msg).build();
    }
}
