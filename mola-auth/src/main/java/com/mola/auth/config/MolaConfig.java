package com.mola.auth.config;

import com.mola.core.cache.RedisConfig;
import com.mola.core.doc.SwaggerConfig;
import com.mola.core.ds.DruidDsAutoConfig;
import com.mola.core.ds.DsProviderConfig;
import com.mola.core.log.ApiLogConfig;
import com.mola.core.response.failure.FeignErrorConfig;
import com.mola.core.response.failure.MolaErrorConfig;
import com.mola.core.response.success.MolaResponseBodyConfig;
import com.mola.core.response.success.MolaWebMvcConfig;
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
        DruidDsAutoConfig.class,
        DsProviderConfig.class,
        RedisConfig.class,
        MolaWebMvcConfig.class,
        SwaggerConfig.class,
        MolaResponseBodyConfig.class,
        FeignErrorConfig.class,
        MolaErrorConfig.class,
        ApiLogConfig.class,
})
public class MolaConfig {
}
