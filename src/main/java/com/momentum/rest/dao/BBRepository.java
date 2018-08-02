package com.momentum.rest.dao;

import com.momentum.rest.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface BBRepository extends JpaRepository<Order, Integer> {


    public List<Order> findAllByOrderId(@Param("order_id") Integer orderId);
    public List<Order> findAllByStrategyId(@Param("strategy_id") Integer strategyId);

 /*   public List<Order> saveEntryPrice(@Param("entry_price") Double entryPrice);
    public List<Order> saveEntryDatetime(@Param("entry_datetime") Timestamp entryDatetime);

    public List<Order> saveStrategyId(@Param("strategy_id") Integer strategyId);
    public List<Order> saveCrossStartType(@Param("crossover_start_type") String crossStartType);

    public List<Order> saveCrossStartDatetime(@Param("crossover_start_datetime") Timestamp crossStartDate);
    public List<Order> saveCrossStartPrice(@Param("crossover_start_price") Double crossStartPrice);
    public List<Order> saveCrossEndDatetime(@Param("crossover_end_datetime") Timestamp crossEndDate);
    public List<Order> saveCrossEndPrice(@Param("crossover_end_price") Double crossEndPrice);
    public List<Order> saveCrossEndType(@Param("crossover_end_type") String type);
*/








}