package com.mola.bi.config;

import com.mola.core.doc.SwaggerConfig;
import com.mola.core.log.ApiLog;
import com.mola.core.response.failure.FeignErrorDecoder;
import com.mola.core.response.failure.MolaErrorController;
import com.mola.core.response.success.MolaResponseBodyAdvice;
import com.mola.core.response.success.MolaWebMvcConfigurer;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

/**
 * bean 装载器
 *
 * @author hatim
 */
@Configuration
@Import({
        MolaWebMvcConfigurer.class,
        SwaggerConfig.class
})
public class BeanConfig {

    private final Environment environment;

    public BeanConfig(Environment environment) {
        this.environment = environment;
    }

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

    /**
     * 接口返回值包装
     *
     * @return
     */
    @Bean
    public MolaResponseBodyAdvice molaResponseBodyAdvice() {
        return new MolaResponseBodyAdvice(environment);
    }

}
