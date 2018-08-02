package com.momentum.rest.springboot.services;

import com.momentum.rest.entities.Order;
import com.momentum.rest.springboot.repos.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orRp;

    @Override
    public void doSomething() {
        System.out.println("didn't fail yet");
      /*  Order o = new Order();
        o.setStrategy_id(2);


        o.setCrossStartDate(Timestamp.valueOf("2018-08-01 09:35:00.0"));
        o.setCrossStartPrice(99.99);
        o.setCrossStartType("buy"); */

        List<Order> list = orRp.findAll();
        System.out.println("worked potentially, list strategy: " + list.get(0).getCrossStartType());
    }

    @Override
    public List<Order> getAllOrders() {
        return orRp.findAll();
    }

    @Override
    public List<Order> insertStrategyId(Integer id) {
        return orRp.findAllByStrategyId(id);
    }

    @Override
    public List<Order> insertStartPrice(Double price){
        return orRp.saveCrossStartPrice(price);
    }

    @Override
    public List<Order> insertStartDatetime(Timestamp date){
        return orRp.saveCrossStartDatetime(date);
    }

    @Override
    public List<Order> insertStartType(String type){
        return orRp.saveCrossStartType(type);
    }

    @Override
    public List<Order> insertEndPrice(Double price) {
        return orRp.saveCrossEndPrice(price);
    }

    @Override
    public List<Order> insertEndDatetime(Timestamp date) {
        return orRp.saveCrossEndDatetime(date);
    }

    @Override
    public List<Order> insertEndType(String type) {
        return orRp.saveCrossEndType(type);
    }


}
