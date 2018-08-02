package com.momentum.rest.service;

import com.momentum.rest.dao.BBRepository;
import com.momentum.rest.entities.BB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BBServiceImpl implements BBService {

    @Autowired
    private BBRepository bbRepo;

    @Override
    public List<BB> getBBByStrategyID(int strategyId) {

        return bbRepo.findBBByStrategyId(strategyId);
    }

    @Override
    public List<BB> getBBByMovingAvgRange(Integer movingAvgRange) {
        return bbRepo.findBBByMovingAvgRange(movingAvgRange);
    }

    @Override
    public List<BB> getwoMAByStdDevMultiple(Double stdDevMultiple) {
        return bbRepo.findBBByStdDevMultiple(stdDevMultiple);
    }

    @Override
    public List<BB> getBBByPercentToExit(Double percentToExit) {
        return bbRepo.findBBByPercentToExit(percentToExit);

    }
}