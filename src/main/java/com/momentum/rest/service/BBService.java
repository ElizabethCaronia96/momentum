package com.momentum.rest.service;


import com.momentum.rest.entities.BB;
import com.momentum.rest.entities.TwoMA;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BBService {

    public List<BB> getBBByStrategyID(int strategyId);
    public List<BB> getBBByMovingAvgRange(Integer movingAvgRange);
    public List<BB> getwoMAByStdDevMultiple(Double stdDevMultiple);
    public List<BB> getBBByPercentToExit(Double percentToExit);

}
