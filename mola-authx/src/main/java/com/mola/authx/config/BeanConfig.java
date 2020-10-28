package com.mola.authx.config;

import com.mola.core.doc.SwaggerConfig;
import com.mola.core.log.ApiLog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * bean 装载器
 *
 * @author hatim
 */
@Configuration
@EnableOpenApi
@Import({
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
}
