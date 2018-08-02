package com.momentum.rest.service;

import com.momentum.rest.entities.Strategies;

import java.sql.Timestamp;
import java.util.List;

public interface StrategiesService {
    public List<Strategies> getStratByStrategyId(int strategyId);
    public List<Strategies> getStratsByType( String type);
    public List<Strategies> getStratByTypeId(Integer typeId);
    public List<Strategies> getStratByStock( String stock);
    public List<Strategies> getStratBySize( Integer size);
    public List<Strategies> getStratByStatus(String  status);
    public List<Strategies> getStratByAddedTime( Timestamp addedTime);
    public List<Strategies> getStratByEntryTime(Timestamp entryTime);
    public List<Strategies> getStratByExitTime(Timestamp exitTime);
    public List<Strategies> getStratByProfitLoss( Double profitLoss);

    public List<Strategies> getAllActive();

    public List<Strategies> getAllStrats();

}
