package com.momentum.rest.springboot.repos;

import com.momentum.rest.entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    public Iterable<Order> findOrderByStrategy(@Param("strategy") String strategy);

}