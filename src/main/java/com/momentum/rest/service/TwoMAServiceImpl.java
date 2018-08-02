package com.momentum.rest.springboot.services;

import com.momentum.rest.entities.TwoMA;
import com.momentum.rest.springboot.repos.TwoMARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwoMAServiceImpl implements TwoMAService {
    @Autowired
    private TwoMARepository twoRepo;
    @Override
    public List<TwoMA> getTwoMAByStrategyID(int strategyId) {
        return twoRepo.findTwoMAByStrategyId(strategyId);
    }

    @Override
    public List<TwoMA> getTwoMAByLongAvgRange(Integer longAvgRange) {
        return twoRepo.findTwoMAByLongAvgRange(longAvgRange);
    }

    @Override
    public List<TwoMA> getwoMAByShortRangeAvg(Integer shortRangeAvg) {
        return twoRepo.findTwoMAByShortRangeAvg(shortRangeAvg);
    }

    @Override
    public List<TwoMA> getTwoMAByPercentToExit(Double percentToExit) {
        return twoRepo.findTwoMAByPercentToExit(percentToExit);
    }
}
