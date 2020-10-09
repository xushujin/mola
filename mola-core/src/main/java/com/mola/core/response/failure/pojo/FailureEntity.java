package com.mola.core.response.failure.pojo;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * 失败返回视图
 *
 * @author hatim
 */
@Data
@Builder
@ToString(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class FailureEntity {
    /**
     * 失败提示
     */
    private String msg;
}
