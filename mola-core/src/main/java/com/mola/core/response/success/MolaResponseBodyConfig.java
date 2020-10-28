package com.mola.core.response.success;

import com.mola.core.response.success.pojo.SuccessEntity;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Optional;

/**
 * Response拦截处理
 *
 * @author hatim
 */
@Slf4j
@RestControllerAdvice
public class MolaResponseBodyConfig implements ResponseBodyAdvice<Object> {
    private static final String PACKAGE_NAME = "com.mola";
    private static final String SWAGGER_BASE_PACKAGE = "swagger.base-package";

    private final Environment environment;

    public MolaResponseBodyConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        if (methodParameter.getMethod().toString().contains(
                Optional.ofNullable(environment.getProperty(SWAGGER_BASE_PACKAGE)).orElse(PACKAGE_NAME))) {
            return true;
        } else {
            return false;
        }
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object object, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        serverHttpResponse.setStatusCode(httpStatus(serverHttpRequest.getMethod()));
        return SuccessEntity.builder().data(object).build();
    }

    /**
     * 根据请求方法获取返回的HttpStatus
     *
     * @param method
     * @return
     */
    public HttpStatus httpStatus(HttpMethod method) {
        HttpStatus httpStatus = HttpStatus.OK;
        switch (method) {
            case GET:
            case PUT:
            case PATCH:
            case DELETE:
                httpStatus = HttpStatus.OK;
                break;
            case POST:
                httpStatus = HttpStatus.CREATED;
                break;
            default:
                break;
        }
        return httpStatus;
    }

}
