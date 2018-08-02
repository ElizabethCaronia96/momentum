package com.momentum.rest.service;

import com.google.common.collect.Lists;
import com.momentum.rest.entities.Order;
import com.momentum.rest.dao.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.List;

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
        System.out.println("worked potentially, list strategy: " + list.get(0).getCrossoverStartType());
    }

    @Override
    public List<Order> getAllOrders() {
        return orRp.findAll();
    }

  /*  @Override
    public List<Order> insertStrategyId(Integer id) {
        return orRp.findOrdersByStrategyId(id);
    }

    @Override
    public List<Order> insertStartPrice(Double price){
        return orRp.saveOrdersByCrossoverStartPrice(price);
    }

    @Override
    public List<Order> insertStartDatetime(Timestamp date){
        return orRp.saveOrdersByCrossoverStartDatetime(date);
    }

    @Override
    public List<Order> insertStartType(String type){
        return orRp.saveOrdersByCrossoverStartType(type);
    }

    @Override
    public List<Order> insertEndPrice(Double price) {
        return orRp.saveOrdersByCrossoverEndPrice(price);
    }

    @Override
    public List<Order> insertEndDatetime(Timestamp date) {
        return orRp.saveOrdersByCrossoverEndDatetime(date);
    }

/*    @Override
    public List<Order> insertEndType(String type, Order o) {
        return orRp.saveOrdersByCrossoverEndType(type, o.getStrategyId());
    }
*/




}
