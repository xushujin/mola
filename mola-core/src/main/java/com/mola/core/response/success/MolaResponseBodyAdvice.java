package com.mola.core.response.success;

import com.mola.core.response.success.pojo.SuccessEntity;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.UiConfiguration;

/**
 * Response拦截处理
 *
 * @author hatim
 */
@Slf4j
@RestControllerAdvice
public class MolaResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object object, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        log.info("uri:{}", serverHttpRequest.getURI());
        if (serverHttpRequest.getURI().toString().contains("swagger-resources") ||
                serverHttpRequest.getURI().toString().contains("api-docs")) {
            return object;
        } else {
            serverHttpResponse.setStatusCode(httpStatus(serverHttpRequest.getMethod()));
            return SuccessEntity.builder().data(object).build();
        }

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
