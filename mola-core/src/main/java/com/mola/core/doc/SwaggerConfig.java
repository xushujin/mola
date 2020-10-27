package com.mola.core.doc;

import com.mola.core.helper.StringHelper;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${swagger.base-package}")
    private String swagger_base_package;
    @Value("${swagger.title}")
    private String swagger_title;
    @Value("${swagger.description}")
    private String swagger_description;
    @Value("${swagger.terms-of-service-url}")
    private String swagger_terms_of_service_url;
    @Value("${swagger.version}")
    private String swagger_version;

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
                .apis(RequestHandlerSelectors.basePackage(swagger_base_package))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swagger_title)
                .description(swagger_description)
                .termsOfServiceUrl(swagger_terms_of_service_url)
                .version(swagger_version)
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
