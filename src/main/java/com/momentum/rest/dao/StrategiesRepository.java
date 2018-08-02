package com.momentum.rest.dao;

import com.momentum.rest.entities.Strategies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface StrategiesRepository extends JpaRepository<Strategies, Integer> {


    public List<Strategies> findStrategiesByStrategyId(@Param("strategy_id") int strategyId);
    public List<Strategies> findStrategiesByType(@Param("type") String type);
    public List<Strategies> findStrategiesByTypeId(@Param("type_id") Integer typeId);
    public List<Strategies> findStrategiesByStock(@Param("stock") String stock);
    public List<Strategies> findStrategiesBySize(@Param("size") Integer size);
    public List<Strategies> findStrategiesByStatus(@Param("status")String status);
    public List<Strategies> findStrategiesByStatusOrderByAddedTime(@Param("status")String status);
    public List<Strategies> findStrategiesByAddedTime(@Param("added_time") Timestamp addedTime);
    public List<Strategies> findStrategiesByEntryTime(@Param("entry_Time") Timestamp entryTime);
    public List<Strategies> findStrategiesByExitTime(@Param("exit_Time") Timestamp exitTime);
    public List<Strategies> findStrategiesByProfitLoss(@Param("profit_loss") Double profitLoss);



}
