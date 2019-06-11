package com.hobin.crm.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import static springfox.documentation.builders.PathSelectors.regex;


/**
 * Configuration for the swagger to generate API documents.
 * @author xuxx
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .directModelSubstitute(Timestamp.class, Integer.class)
                .consumes(supportMediaTypes())
                .produces(supportMediaTypes())
                .forCodeGeneration(true)
                .select()
                .paths(apiPath())
                .build();
    }

    private Set<String> supportMediaTypes() {
        Set<String> mediaTypes = new HashSet<>();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8_VALUE);
        mediaTypes.add(MediaType.APPLICATION_JSON_VALUE);
        return mediaTypes;
    }

    private Predicate<String> apiPath() {
        return regex("/api/.*");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("HOBIN CRM API")
                .description("The Hobin CRM RESTful API service provides services for apps.")
                .version("1.0.0.SNAPSHOT")
                .build();
    }
}
