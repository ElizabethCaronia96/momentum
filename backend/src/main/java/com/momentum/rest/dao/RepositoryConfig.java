package com.momentum.rest.dao;

import com.momentum.rest.entities.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Order.class);
        config.exposeIdsFor(Strategies.class);
        config.exposeIdsFor(BB.class);
        config.exposeIdsFor(TwoMA.class);
        config.exposeIdsFor(Price.class);
    }
}