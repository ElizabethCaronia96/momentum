package com.momentum.rest.springboot;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.momentum.algo.AlgoBollingerBands;

@Service
public class AlgoRunner {

    @Scheduled(fixedRate = 250) // this is in milliseconds
    public void constantAlgoChecking() {

        System.out.println("this one should run 4 times for every feedcheck print statement");
    }
}
