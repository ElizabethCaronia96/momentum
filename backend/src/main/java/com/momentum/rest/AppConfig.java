package com.momentum.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


@EnableAutoConfiguration
@ComponentScan(basePackages = "com.momentum")
@EntityScan("com.momentum.rest.entities")
//@EnableScheduling
@Configuration
public class AppConfig {

    @Bean(destroyMethod = "shutdown")
    public Executor taskScheduler() {
        return Executors.newScheduledThreadPool(2);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppConfig.class, args);
    }
}