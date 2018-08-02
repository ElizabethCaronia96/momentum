package com.momentum.rest;


import com.momentum.algo.AlgoBollingerBands;
import com.momentum.algo.AlgoTwoMovingAverages;
import com.momentum.rest.entities.BB;
import com.momentum.rest.entities.Strategies;
import com.momentum.rest.entities.TwoMA;
import com.momentum.rest.service.PriceService;
import com.momentum.rest.service.StrategiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class AlgoRunner {

    @Autowired
    private PriceService ps;

    @Autowired
    private StrategiesService ss;


    static final int MAX_THREADS = 10;

    @Scheduled(fixedRate = 1000000) // this is in milliseconds
    public void algorithmChecker() {

        ExecutorService pool = Executors.newFixedThreadPool(MAX_THREADS);

        boolean exitMomentum = false;

        while (!exitMomentum) {

            // strategies in db
            Map<Strategies, Object> strategiesMap = ss.getAllActive();

            // id's of running strategies
            List<Integer> runningStrategies = new ArrayList<Integer>();

            // iterate through strategies in db
            for (Map.Entry<Strategies, Object> entry : strategiesMap.entrySet()) {

                boolean alreadyRunning = false;

                int strategyID = entry.getKey().getStrategyId();

                // iterate through running strategies
                for (Integer i : runningStrategies) {

                    // if strategy is already running
                    if (strategyID == i) {

                        alreadyRunning = true;
                    }
                }

                // if strategy is not already running, send it to thread pool
                if (!alreadyRunning) {

                    if (entry.getValue().getClass() == TwoMA.class) {

                        String stock = entry.getKey().getStock();

                        TwoMA strat = ((TwoMA) (entry.getValue()));
                        int shortSMAPeriod = strat.getShortAvgRange();
                        int longSMAPeriod = strat.getLongAvgRange();
                        double exitPercent = strat.getPercentToExit();

                        Runnable r = new AlgoTwoMovingAverages("Auto", stock, shortSMAPeriod, longSMAPeriod, exitPercent);
                    } else if (entry.getValue().getClass() == BB.class) {

                        String stock = entry.getKey().getStock();

                        BB strat = ((BB) (entry.getValue()));
                        int smaPeriod = strat.getMovingAvgRange();
                        double stdDevMult = strat.getStdDevMultiple();
                        double exitPercent = strat.getPercentToExit();

                        Runnable r = new AlgoBollingerBands("Auto", stock, smaPeriod, stdDevMult, exitPercent);
                    }

                    runningStrategies.add(strategyID);
                }
            }
        }
    }
}


//    @PostConstruct
//    public void runOnce() {
//        Map<Strategies, Object> relist = ss.getAllActive();
//        for (Map.Entry<Strategies, Object> entry : relist.entrySet()) {
//            // System.out.println("should have everything");
//            System.out.println(entry.getKey() + " / " + entry.getValue());
//        }
//
//    }


// function call for each thread

// example function {
// initialize ur queues for this straegy
// inside this thread, u call price service's get last N prices of stock
