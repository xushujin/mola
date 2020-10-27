package com.mola.core.doc;

import com.mola.core.helper.StringHelper;
import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Swagger配置
 *
 * @author hatim
 */
@EnableOpenApi
public class SwaggerConfig {

    private static final String SWAGGER_BASE_PACKAGE = "swagger.base-package";
    private static final String SWAGGER_TITLE = "swagger.title";
    private static final String SWAGGER_DESCRIPTION = "swagger.description";
    private static final String SWAGGER_TERMS_OF_SERVICE_URL = "swagger.terms-of-service-url";
    private static final String SWAGGER_VERSION = "swagger.version";

    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private static final String MOLA_SESSION_ID = "MOLA_SESSION_ID";
    private static final String GLOBAL = "global";

    private static final String PROD = "prod";
    private static final String SPRING_PROFILES_ACTIVE = "spring.profiles.active";

    private final Environment environment;

    public SwaggerConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .enable(StringHelper.equals(environment.getProperty(SPRING_PROFILES_ACTIVE), PROD) ? false : true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(environment.getProperty(SWAGGER_BASE_PACKAGE)))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(environment.getProperty(SWAGGER_TITLE))
                .description(environment.getProperty(SWAGGER_DESCRIPTION))
                .termsOfServiceUrl(environment.getProperty(SWAGGER_TERMS_OF_SERVICE_URL))
                .version(environment.getProperty(SWAGGER_VERSION))
                .build();
    }

    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> securitySchemes = new ArrayList<>();
        securitySchemes.add(new ApiKey(ACCESS_TOKEN, null, In.HEADER.toValue()));
        securitySchemes.add(new ApiKey(MOLA_SESSION_ID, null, In.HEADER.toValue()));
        return securitySchemes;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(SecurityReference.builder().reference(ACCESS_TOKEN).scopes(new AuthorizationScope[]{new AuthorizationScope(GLOBAL, null)}).build());
        securityReferences.add(SecurityReference.builder().reference(MOLA_SESSION_ID).scopes(new AuthorizationScope[]{new AuthorizationScope(GLOBAL, null)}).build());
        return Collections.singletonList(SecurityContext.builder().securityReferences(securityReferences).build());
    }
}
