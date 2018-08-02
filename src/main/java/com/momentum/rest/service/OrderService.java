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

  /*  List<Order> insertStrategyId(Integer id );

    List<Order> insertStartPrice(Double price);

    List<Order> insertStartDatetime(Timestamp date);


    List<Order> insertStartType(String type);

    List<Order> insertEndPrice(Double price);

    List<Order> insertEndDatetime(Timestamp date);

 //   List<Order> insertEndType(String type, Order o);
*/

}
