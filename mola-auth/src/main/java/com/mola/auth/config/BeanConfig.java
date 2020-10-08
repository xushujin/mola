package com.mola.auth.config;

import com.mola.core.cache.RedisConfig;
import com.mola.core.doc.SwaggerConfig;
import com.mola.core.ds.DruidDsAutoConfig;
import com.mola.core.ds.DsProviderConfig;
import com.mola.core.log.ApiLog;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.mola.core.response.failure.MolaErrorController;
import com.mola.core.response.success.MolaResponseBodyAdvice;
import com.mola.core.response.success.MolaWebMvcConfigurer;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spring.web.plugins.Docket;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * bean 装载器
 *
 * @author hatim
 */
@Configuration
@EnableOpenApi
@Import({DruidDsAutoConfig.class,
        DsProviderConfig.class,
        RedisConfig.class,
        MolaWebMvcConfigurer.class
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
     * swagger文档配置装载
     *
     * @return
     */
    @Bean
    public Docket SwaggerConfig() {
        return new SwaggerConfig(environment).docket();
    }

    /**
     * DefaultKaptcha 装载
     *
     * @return
     */
    @Bean
    public DefaultKaptcha defaultKaptcha() throws IOException {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(defaultKaptchaProperties());
        return defaultKaptcha;
    }

    /**
     * 接口返回值包装
     *
     * @return
     */
    @Bean
    public MolaResponseBodyAdvice molaResponseBodyAdvice() {
        return new MolaResponseBodyAdvice();
    }

    /**
     * 验证码配置文件
     *
     * @return
     */
    private static Config defaultKaptchaProperties() throws IOException {
        Properties properties = PropertiesLoaderUtils.loadProperties(
                new EncodedResource(new ClassPathResource("kaptcha.properties"), Charset.defaultCharset()));
        return new Config(properties);
    }

}
