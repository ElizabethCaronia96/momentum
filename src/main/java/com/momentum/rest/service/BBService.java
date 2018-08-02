package com.momentum.rest.service;


import com.momentum.rest.dao.BBRepository;
import com.momentum.rest.entities.BB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BBService {

    public List<BB> getBBByStrategyId(int strategyId);
}
