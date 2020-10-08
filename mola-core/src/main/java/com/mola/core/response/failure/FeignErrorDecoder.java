package com.mola.core.response.failure;

import com.mola.core.helper.JsonHelper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * feign异常拦截
 *
 * @author hatim
 */
@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        String body = String.valueOf(response.body());
        FailureVo failureVo = null;
        try {
            failureVo = JsonHelper.fromJson(body, FailureVo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw MolaException.builder()
                .code(String.valueOf(response.status()))
                .msg(failureVo != null ? failureVo.getMsg() : body)
                .build();
    }
}
