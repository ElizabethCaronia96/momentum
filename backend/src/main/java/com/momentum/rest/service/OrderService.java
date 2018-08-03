package com.momentum.rest.service;

import com.momentum.rest.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public interface OrderService {

    void doSomething();


    List<Order> getAllOrders();
    List<Order> getAllOpenPositions();

   void updateOrder(Order orderr);

   void addOrder(Order order);

 //   void insertEndType(String type, Order o);
  //  Order createOrderFromCross1(String strategy_id, )
}
