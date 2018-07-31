package com.momentum.rest.spring.boot.repos;

import com.momentum.rest.entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    public Iterable<Order> findOrderByStrategy_type(@Param("strategy_type") String strategy_type);

}
