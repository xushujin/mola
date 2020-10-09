package com.mola.core.log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mola.core.helper.JsonHelper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
    /**
     * 执行状态
     */
    private String status;
    /**
     * 执行耗时
     */
    private long useTime;
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

    @Override
    public String toString() {
        String info = null;
        try {
            info = JsonHelper.toJson(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return info;
    }
}
