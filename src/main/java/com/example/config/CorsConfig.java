package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    private static final String ALL_ORIGINS = "*";
    private static final String ALL_HEADERS = "*";
    private static final String ALL_MAPPING = "/**";
    private static final String[] ALL_METHODS = {"GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"};

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(ALL_MAPPING)
                .allowedMethods(ALL_METHODS)
                .allowedOrigins(ALL_ORIGINS)
                .allowedHeaders(ALL_HEADERS);
    }
}
