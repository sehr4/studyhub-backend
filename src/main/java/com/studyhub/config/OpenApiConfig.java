package com.studyhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI studyhubOpenAPI() {
        return new OpenAPI().info(new Info().title("StudyHub RESTful API").description("StudyHub service and documentation").version("Version 1.1"));
    }
}
