package com.mola.bi.config;

import com.mola.core.log.ApiLog;
import com.mola.core.response.failure.MolaErrorController;
import com.mola.core.response.failure.FeignErrorDecoder;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * bean 装载器
 *
 * @author hatim
 */
@Configuration
public class BeanConfig {

    /**
     * 日志拦截器装载
     *
     * @return
     */
    @Bean
    public ApiLog apiLogAop() {
        return new ApiLog();
    }

    /**
     * 异常处理器装载
     *
     * @return
     */
    @Bean
    public MolaErrorController ccErrorController() {
        return new MolaErrorController(errorAttributes());
    }

    /**
     * ErrorAttributes
     *
     * @return
     */
    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes();
    }

    /**
     * feign异常处理
     *
     * @return
     */
    @Bean
    public FeignErrorDecoder feignErrorDecoder() {
        return new FeignErrorDecoder();
    }
}
