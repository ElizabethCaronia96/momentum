package com.momentum.rest.service;

import com.momentum.rest.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;


public interface OrderService {

    void doSomething();


    List<Order> getAllOrders();

    List<Order> getAllOrdersByStratID(int i);

    List<Order> getAllOpenPositions();

    void updateOrder(Order orderr);

    void addOrder(Order order);

    //   void insertEndType(String type, Order o);
    Order createOrderFromCross1(int strategy_id, String crossType1, Timestamp crossTime1, Double crossPrice1);

    void updateOrderFromCross2(Order o, String crossType2, Timestamp crossTime2, Double crossPrice2, Double profit);


}