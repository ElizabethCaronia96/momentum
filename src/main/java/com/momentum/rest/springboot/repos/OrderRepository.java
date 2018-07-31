package com.momentum.rest.springboot.repos;

import com.momentum.rest.entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    public Iterable<Order> findOrderByStock(@Param("stock") String stock);

    public Iterable<Order> findOrderByNumstocks(@Param("num_stocks") Integer numstocks);

    public Iterable<Order> findOrderByStrategyId(@Param("strategy_id") Integer strategyId);

    public Iterable<Order> findOrderByStrategyType(@Param("strateg_type") String strategyType);

    public Iterable<Order> findOrderByDatetimeAdded(@Param("datetime_added")Timestamp datetimeAdded);

    public Iterable<Order> findOrderByStatus(@Param("status") String status);

    public Iterable<Order> findOrderByEntryDatetime(@Param("entry_datetime") Timestamp entryDatetime);

    public Iterable<Order> findOrderByEntryPrice(@Param("entry_price")Double entryPrice);

    public Iterable<Order> findOrderByEntryType(@Param("entry_type") String entryType);

    public Iterable<Order> findOrderByExitType(@Param("exit_type") String exitType);

    public Iterable<Order> findOrderByExitDatetime(@Param("exit_datetime") Timestamp exitDatetime);

    public Iterable<Order> findOrderByExitPrice(@Param("exit_price")Double exitPrice);





}