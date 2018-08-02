package com.momentum.rest.springboot.repos;

import com.momentum.rest.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    Iterable<Order> findOrderByStrategyId(@Param("strategy_id") int strategyId);
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
//TODO probably should be deleted now that its jpa
 /*   public Iterable<Order> findOrderByStock(@Param("stock") String stock);

    public Iterable<Order> findOrderByNumstocks(@Param("num_stocks") Integer numstocks);

    public Iterable<Order> findOrderByStrategyId(@Param("strategy_id") Integer strategyId);

    public Iterable<Order> findOrderByStrategyType(@Param("strateg_type") String strategyType);

    public Iterable<Order> findOrderByDatetimeAdded(@Param("datetime_added")Timestamp datetimeAdded);

    public Iterable<Order> findOrderByStatus(@Param("status") String status);

    public Iterable<Order> findOrderByEntryDatetime(@Param("entry_datetime") Timestamp entryDatetime);

    public Iterable<Order> findOrderByEntryPrice(@Param("entry_price")Double entryPrice);

    Iterable<Order> findOrderByCrossoverStartType(@Param("crossover_start_type") String crossoverStartType);

    Iterable<Order> findOrderByCrossoverStartDatetime(@Param("crossover_start_datetime") Timestamp crossoverStartDatetime);

    Iterable<Order> findOrderByCrossoverStartPrice(@Param("crossover_start_price") double crossoverStartPrice);

    Iterable<Order> findOrderByCrossoverEndType(@Param("crossover_end_type") String crossoverEndType);

    Iterable<Order> findOrderByCrossoverEndDatetime(@Param("crossover_end_datetime") Timestamp crossoverEndDatetime);

    Iterable<Order> findOrderByCrossoverEndPrice(@Param("crossover_end_price") double crossoverEndPrice);

    Iterable<Order> findOrderByProfitLoss(@Param("profit_loss") double profitLoss);
*/

    public List<Order> findAllByOrderId(@Param("order_id") Integer orderId);
    public List<Order> findAllByStrategyId(@Param("strategy_id") Integer strategyId);

}