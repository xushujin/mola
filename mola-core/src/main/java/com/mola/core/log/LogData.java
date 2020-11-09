package com.mola.core.log;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mola.core.helper.JsonHelper;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Optional;

/**
 * 日志数据
 *
 * @author hatim
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogData {
    public static final String SEPARATOR = " ::: ";
    public static final String LOG_IN_HEAD = "[入口日志]";
    public static final String LOG_OUT_HEAD = "[出口日志]";

    /**
     * 日志头
     */
    private String head;
    /**
     * 执行状态
     */
    private String status;
    /**
     * 执行耗时
     */
    private Long useTime;
    /**
     * 入口方法名称
     */
    private String methodName;
    /**
     * 方法描述
     */
    private String methodDesc;
    /**
     * 方法参数
     */
    private Object methodArgs;
    /**
     * 操作人
     */
    private String operator;
    /**
     * 执行结果
     */
    private Object result;
    /**
     * 是否打印执行结果
     */
    @JsonIgnore
    private Boolean resultPrint;

    @SneakyThrows
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(head).append(" ");
        // 方法相关
        sb.append(methodDesc).append("::")
                .append(methodName).append("::")
                .append(JsonHelper.toJson(methodArgs))
                .append(SEPARATOR).append(Optional.ofNullable(operator).orElse(""));
        if (LOG_OUT_HEAD.equals(head)) {
            // 接口处理状态
            sb.append(SEPARATOR).append(Optional.ofNullable(status).orElse(""))
                    // 接口请求耗时
                    .append(SEPARATOR).append(useTime == null ? "" : useTime + "ms")
                    // 接口返回结果
                    .append(SEPARATOR).append(resultPrint ? (result == null ? "" : JsonHelper.toJson(result)) : "");
        }
        return sb.toString();
    }
}
