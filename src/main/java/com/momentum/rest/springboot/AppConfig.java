package com.momentum.rest.springboot;


import com.momentum.rest.springboot.controllers.HomeController;
import com.momentum.rest.springboot.services.OrderServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableAutoConfiguration()
@EnableJpaRepositories
@ComponentScan
@EntityScan("com.momentum.rest.entities")
public class AppConfig {
    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
       // OrderClient oc = new OrderClient();
       // oc.getOrders();

    //    HomeController hc = new HomeController();
     //   hc.getAllOrders();



    }

}