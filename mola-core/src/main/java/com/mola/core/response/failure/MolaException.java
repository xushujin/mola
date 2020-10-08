package com.mola.core.response.failure;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 自定义异常
 * 需要返回两种返回形式，所以需要一个标识符区分：
 * 1. 返回错误视图（httpStatus != 200）
 * 2. 返回业务异常包装体(httpStatus = 200)
 *
 * @author hatim
 */
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MolaException extends RuntimeException {
    private static final long serialVersionUID = 5561493488854219533L;

    /**
     * 是否使得http status = 200，默认false
     */
    @Builder.Default
    private boolean isOk = false;

    /**
     * 异常码
     */
    private String code;

    /**
     * 异常描述
     */
    private String msg;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public MolaException(final boolean isOk, final String code, final String msg) {
        super("{isOk:" + isOk + ",code:" + code + ",msg:" + msg + "}");
        this.isOk = isOk;
        this.code = code;
        this.msg = msg;
    }
}
