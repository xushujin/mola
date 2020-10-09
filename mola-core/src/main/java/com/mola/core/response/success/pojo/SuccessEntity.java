package com.mola.core.response.success.pojo;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

/**
 * 成功返回视图
 *
 * @author hatim
 */
@Data
@Builder
@ToString(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SuccessEntity<T> {
    /**
     * 返回数据
     */
    private T data;

    @Builder.Default
    private String code = HttpStatus.OK.name();

    @Builder.Default
    private String msg = HttpStatus.OK.getReasonPhrase();
}
