package com.nhnacademy.gateway.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskServerProperties {
    @Value("${task.server.url}")
    private String url;

    @Bean
    public String taskServerUrlProvider() {
        return url;
    }
}
