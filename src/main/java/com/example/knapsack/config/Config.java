package com.example.knapsack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Properties;

@Configuration
public class Config {
    @Bean
    public Properties properties() {
        return new Properties();
    }

}
