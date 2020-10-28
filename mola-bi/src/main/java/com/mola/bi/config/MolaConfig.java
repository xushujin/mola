package com.mola.bi.config;

import com.mola.core.doc.SwaggerConfig;
import com.mola.core.log.ApiLogConfig;
import com.mola.core.response.failure.FeignErrorConfig;
import com.mola.core.response.failure.MolaErrorConfig;
import com.mola.core.response.success.MolaResponseBodyConfig;
import com.mola.core.response.success.MolaWebMvcConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * bean 装载器
 *
 * @author hatim
 */
@Configuration
@Import({
        MolaResponseBodyConfig.class,
        FeignErrorConfig.class,
        MolaErrorConfig.class,
        ApiLogConfig.class,
        MolaWebMvcConfig.class,
        SwaggerConfig.class
})
public class MolaConfig {
}
