package com.mola.core.response.success;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;

/**
 * Response拦截处理
 *
 * @author hatim
 */
@RestControllerAdvice
public class MolaResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    private static final Class<? extends Annotation> ANNOTATION_TYPE = MolaResponseBody.class;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
//        return AnnotatedElementUtils.hasAnnotation(methodParameter.getContainingClass(), ANNOTATION_TYPE) || methodParameter.hasMethodAnnotation(ANNOTATION_TYPE);
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object object, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        serverHttpResponse.setStatusCode(httpStatus(serverHttpRequest.getMethod()));
        return SuccessVo.builder().data(object).build();
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
