package com.momentum.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableAutoConfiguration
@ComponentScan(basePackages = "com.momentum.rest")
@EntityScan("com.momentum.rest.entities")
@EnableScheduling
public class AppConfig {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppConfig.class, args);

    }
}