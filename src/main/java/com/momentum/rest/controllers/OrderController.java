package com.momentum.rest.controllers;

import com.momentum.rest.dao.OrderRepository;
import com.momentum.rest.entities.Order;
import com.momentum.rest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path="order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping(path="/open-pos")
    @ResponseBody
    public List<Order> getOpenPositions() {
        return orderService.getAllOpenPositions();
    }
}
