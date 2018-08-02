package com.momentum.rest.service;

import com.momentum.rest.entities.TwoMA;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TwoMAService {

    public List<TwoMA> getTwoMAByStrategyID( int strategyId);
    public List<TwoMA> getTwoMAByLongAvgRange(Integer longAvgRange);
    public List<TwoMA> getwoMAByShortAvgRange( Integer shortAvgRange);
    public List<TwoMA> getTwoMAByPercentToExit(Double percentToExit);

}
