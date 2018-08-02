package com.momentum.rest.dao;

import com.momentum.rest.entities.BB;
import com.momentum.rest.entities.Order;
import com.momentum.rest.entities.TwoMA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface BBRepository extends JpaRepository<BB, Integer> {


    public List<BB> findBBByStrategyId(@Param("strategy_id") int strategyId);
    public List<BB> findBBByMovingAvgRange(@Param("moving_avg_range") Integer movingAvgRange);
    public List<BB> findBBByStdDevMultiple(@Param("std_dev_multiple") Double stdDevMultiple);
    public List<BB> findBBByPercentToExit(@Param("percent_to_exit") Double percentToExit);








}