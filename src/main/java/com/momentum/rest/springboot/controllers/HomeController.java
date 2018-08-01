package com.momentum.rest.springboot.controllers;

import com.momentum.rest.entities.Order;
import com.momentum.rest.springboot.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private OrderRepository orRp;

    @RequestMapping("/")
    public String index() {
        return "index.html";
    }

    public void doSomething(){
        List<Order> orders = orRp.findAllByOrderId(1001);
        System.out.println(orders);
    }

    @GetMapping("orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> list = orRp.findAllByOrderId(1001);
        return new ResponseEntity<List<Order>>(list, HttpStatus.OK);
    }
}