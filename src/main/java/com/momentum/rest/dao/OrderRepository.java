package com.momentum.rest.dao;

import com.momentum.rest.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Iterable<Order> findOrdersByStrategyId(@Param("strategy_id") int strategyId);

    Iterable<Order> findOrdersByCrossoverStartType(@Param("crossover_start_type") String crossoverStartType);

    Iterable<Order> findOrdersByCrossoverStartDatetime(@Param("crossover_start_datetime") Timestamp crossoverStartDatetime);

    Iterable<Order> findOrdersByCrossoverStartPrice(@Param("crossover_start_price") double crossoverStartPrice);

    Iterable<Order> findOrdersByCrossoverEndType(@Param("crossover_end_type") String crossoverEndType);

    Iterable<Order> findOrdersByCrossoverEndDatetime(@Param("crossover_end_datetime") Timestamp crossoverEndDatetime);

    Iterable<Order> findOrdersByCrossoverEndPrice(@Param("crossover_end_price") double crossoverEndPrice);

    Iterable<Order> findOrdersByProfitLoss(@Param("profit_loss") double profitLoss);

    Iterable<Order> saveOrderByProfitLoss(@Param("profit_loss") double profitLoss);

    Iterable<Order> saveOrdersByStrategyId(@Param("strategy_id") int strategyId);

    Iterable<Order> saveOrdersByCrossoverStartType(@Param("crossover_start_type") String crossoverStartType);

    Iterable<Order> saveOrdersByCrossoverStartDatetime(@Param("crossover_start_datetime") Timestamp crossoverStartDatetime);

    Iterable<Order> saveOrdersByCrossoverStartPrice(@Param("crossover_start_price") double crossoverStartPrice);

    Iterable<Order> saveOrdersByCrossoverEndType(@Param("crossover_end_type") String crossoverEndType);

    Iterable<Order> saveOrdersByCrossoverEndDatetime(@Param("crossover_end_datetime") Timestamp crossoverEndDatetime);

    Iterable<Order> saveOrdersByCrossoverEndPrice(@Param("crossover_end_price") double crossoverEndPrice);




}