package com.momentum.rest.dao;


import com.momentum.rest.entities.Order;
import com.momentum.rest.entities.TwoMA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TwoMARepository extends JpaRepository<TwoMA, Integer> {


    public List<TwoMA> findTwoMAByStrategyId(@Param("strategy_id") int strategyId);
    public List<TwoMA> findTwoMAByLongAvgRange(@Param("long_avg_range") Integer longAvgRange);
    public List<TwoMA> findTwoMAByShortRangeAvg(@Param("short_avg_range") Integer shortRangeAvg);
    public List<TwoMA> findTwoMAByPercentToExit(@Param("percent_to_exit") Double percentToExit);

}