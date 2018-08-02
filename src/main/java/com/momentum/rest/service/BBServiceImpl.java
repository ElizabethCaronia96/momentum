package com.momentum.rest.service;

import com.momentum.rest.dao.BBRepository;
import com.momentum.rest.entities.BB;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BBServiceImpl {
    @Autowired
    private BBRepository bbRepo;
    public List<BB> getBBbyId(int strategyId)
    {
        return bbRepo.findBBByStrategyId(strategyId);
    }
}
