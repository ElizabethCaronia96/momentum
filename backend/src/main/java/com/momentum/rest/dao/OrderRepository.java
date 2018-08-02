package com.momentum.rest.dao;

import com.momentum.rest.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findOrdersByStrategyId(@Param("strategy_id") int strategyId);

    List<Order> findOrdersByCrossoverStartType(@Param("crossover_start_type") String crossoverStartType);

    List<Order> findOrdersByCrossoverStartDatetime(@Param("crossover_start_datetime") Timestamp crossoverStartDatetime);

    List<Order> findOrdersByCrossoverStartPrice(@Param("crossover_start_price") double crossoverStartPrice);

    List<Order> findOrdersByCrossoverEndType(@Param("crossover_end_type") String crossoverEndType);

    List<Order> findOrdersByCrossoverEndDatetime(@Param("crossover_end_datetime") Timestamp crossoverEndDatetime);

    List<Order> findOrdersByCrossoverEndPrice(@Param("crossover_end_price") double crossoverEndPrice);

    List<Order> findOrdersByProfitLoss(@Param("profit_loss") double profitLoss);

    Order findOrderByStrategyId(@Param("strategy_id") int strategyId);

    Order findOrderByCrossoverStartType(@Param("crossover_start_type") String crossoverStartType);

    Order findOrderByCrossoverStartDatetime(@Param("crossover_start_datetime") Timestamp crossoverStartDatetime);

    Order findOrderByCrossoverStartPrice(@Param("crossover_start_price") double crossoverStartPrice);

    Order findOrderByCrossoverEndType(@Param("crossover_end_type") String crossoverEndType);

    Order findOrderByCrossoverEndDatetime(@Param("crossover_end_datetime") Timestamp crossoverEndDatetime);

    Order findOrderByCrossoverEndPrice(@Param("crossover_end_price") double crossoverEndPrice);

    Order findOrderByProfitLoss(@Param("profit_loss") double profitLoss);





}