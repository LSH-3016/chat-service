package com.example.rapa.chatT.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**").allowedOriginPatterns(new String[]{"*"}).allowedMethods(new String[]{"*"}).allowedHeaders(new String[]{"*"}).allowCredentials(true);
    }
}
