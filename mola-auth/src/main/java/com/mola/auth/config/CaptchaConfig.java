package com.mola.auth.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * 验证码配置
 *
 * @author hatim
 */
@Configuration
public class CaptchaConfig {
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
