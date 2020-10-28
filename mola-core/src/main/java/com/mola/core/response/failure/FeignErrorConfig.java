package com.mola.core.response.failure;

import com.mola.core.helper.JsonHelper;
import com.mola.core.response.failure.pojo.FailureEntity;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * feign异常拦截
 *
 * @author hatim
 */
@Slf4j
@Configuration
public class FeignErrorConfig implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        String body = String.valueOf(response.body());
        FailureEntity failureEntity = null;
        try {
            failureEntity = JsonHelper.fromJson(body, FailureEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw MolaException.builder()
                .code(String.valueOf(response.status()))
                .msg(failureEntity != null ? failureEntity.getMsg() : body)
                .build();
    }
}
