package com.nhnacademy.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountServerProperties {
    @Value("${account.server.url}")
    private String url;

    @Bean
    public String accountServerProvider() {
        return url;
    }
}
