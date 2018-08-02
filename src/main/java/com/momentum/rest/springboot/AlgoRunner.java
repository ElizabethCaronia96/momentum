package com.momentum.rest.springboot;

import com.momentum.algo.AlgoTwoMovingAverages;
import com.momentum.algo.SMA;
import com.momentum.rest.entities.Order;
import com.momentum.rest.springboot.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.momentum.algo.AlgoBollingerBands;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlgoRunner {

    @Autowired
    private OrderService service;
    
    @Scheduled(fixedRate = 25000000) //
    // this is in milliseconds
    public void constantAlgoChecking() {

        System.out.println("this one should run 4 times for every feedcheck print statement");

        AlgoTwoMovingAverages check1 = new AlgoTwoMovingAverages();
        check1.executeStrategy("auto",2,10,0.20);



    }

}
