package com.momentum.rest.springboot.repos;

import com.momentum.rest.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

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

    public Iterable<Order> findOrderByEntryType(@Param("entry_type") String entryType);

    public Iterable<Order> findOrderByExitType(@Param("exit_type") String exitType);

    public Iterable<Order> findOrderByExitDatetime(@Param("exit_datetime") Timestamp exitDatetime);

    public Iterable<Order> findOrderByExitPrice(@Param("exit_price")Double exitPrice);

    public Iterable<Integer> findEntryPriceByOrderId(@Param("order_id") Integer orderId);


*/

    public List<Order> findAllByOrderId(@Param("order_id") Integer orderId);
    public List<Order> findAllByStrategyId(@Param("strategy_id") Integer strategyId);

}