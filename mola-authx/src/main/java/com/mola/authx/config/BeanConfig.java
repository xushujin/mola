package com.mola.authx.config;

import com.mola.core.doc.SwaggerConfig;
import com.mola.core.log.ApiLogConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * bean 装载器
 *
 * @author hatim
 */
@Configuration
@EnableOpenApi
@Import({
        SwaggerConfig.class,
        ApiLogConfig.class
})
public class BeanConfig {
}
