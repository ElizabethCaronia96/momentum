package com.momentum.rest.springboot.repos;

import com.momentum.rest.entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    Iterable<Order> findOrderByStrategyId(@Param("strategy_id") int strategyId);

    Iterable<Order> findOrderByCrossoverStartType(@Param("crossover_start_type") String crossoverStartType);

    Iterable<Order> findOrderByCrossoverStartDatetime(@Param("crossover_start_datetime") Timestamp crossoverStartDatetime);

    Iterable<Order> findOrderByCrossoverStartPrice(@Param("crossover_start_price") double crossoverStartPrice);

    Iterable<Order> findOrderByCrossoverEndType(@Param("crossover_end_type") String crossoverEndType);

    Iterable<Order> findOrderByCrossoverEndDatetime(@Param("crossover_end_datetime") Timestamp crossoverEndDatetime);

    Iterable<Order> findOrderByCrossoverEndPrice(@Param("crossover_end_price") double crossoverEndPrice);

    Iterable<Order> findOrderByProfitLoss(@Param("profit_loss") double profitLoss);

}