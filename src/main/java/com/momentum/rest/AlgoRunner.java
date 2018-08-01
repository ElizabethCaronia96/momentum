package com.momentum.rest;

import com.momentum.rest.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.momentum.algo.AlgoBollingerBands;

import java.util.List;

@Service
public class AlgoRunner {

    @Autowired
    private PriceService ps;

    @Scheduled(fixedRate = 250) // this is in milliseconds
    public void algorithmChecker() {

        System.out.println("Algorithm check executed.");
        List prices = ps.getLastNPricesOfStock("GOOG", 20);
    }


}
