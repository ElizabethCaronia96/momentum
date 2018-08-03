package com.momentum.rest;


import com.momentum.algo.AlgoTwoMovingAverages;
import com.momentum.rest.entities.TwoMA;
import com.momentum.rest.entities.BB;

import com.momentum.rest.entities.Order;

import com.momentum.rest.entities.Strategies;
import com.momentum.rest.service.OrderService;
import com.momentum.rest.service.PriceService;
import com.momentum.rest.service.StrategiesService;
import org.apache.activemq.transport.tcp.TimeStampStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.momentum.algo.AlgoBollingerBands;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.HashMap;
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

        // id's of running strategies
        List<Integer> runningStrategies = new ArrayList<Integer>();

        boolean exitMomentum = false;

        // get all strategies from database
        Map<Strategies, Object> allStrategiesMap = ss.getAllActive();

        Map.Entry<Strategies,Object> tempEntry = allStrategiesMap.entrySet().iterator().next();
        Strategies key = tempEntry.getKey();
        Object value = tempEntry.getValue();

        Map<Strategies, Object> strategiesMap = new HashMap<Strategies, Object>();
        strategiesMap.put(key, value);

        // Momentum trading platform keeps running
        while (!exitMomentum) {

            // iterate through strategies in database
            for (Map.Entry<Strategies, Object> entry : strategiesMap.entrySet()) {

                boolean alreadyRunning = false;

                int strategyID = entry.getKey().getStrategyId();

                // test if strategy in database is already running
                for (Integer id : runningStrategies) {

                    // if strategy is already running
                    if (strategyID == id) {

                        alreadyRunning = true;
                    }
                }

                // if strategy is not already running, send it to thread pool
                if (!alreadyRunning) {

                    System.out.println("Strategy sent to thread pool: " + entry.getKey() + " / " + entry.getValue());

                    if (entry.getValue() instanceof TwoMA) {

                        String stock = entry.getKey().getStock();

                        TwoMA strategy = ( (TwoMA) (entry.getValue()) );
                        int shortSMAPeriod = strategy.getShortAvgRange();
                        int longSMAPeriod = strategy.getLongAvgRange();
                        double exitPercent = strategy.getPercentToExit() / 100.0;

                        System.out.println(stock + " / " + shortSMAPeriod + " / " + longSMAPeriod + " / " + exitPercent);

                        Runnable r = new AlgoTwoMovingAverages("Auto", stock, shortSMAPeriod, longSMAPeriod, exitPercent, ps);
                        pool.execute(r);
                    }
                    else if (entry.getValue() instanceof BB) {

                        String stock = entry.getKey().getStock();

                        BB strategy = ( (BB) (entry.getValue()) );
                        int smaPeriod = strategy.getMovingAvgRange();
                        double stdDevMult = strategy.getStdDevMultiple();
                        double exitPercent = strategy.getPercentToExit() / 100.0;

                        System.out.println(stock + " / " + smaPeriod + " / " + stdDevMult + " / " + exitPercent);

                        Runnable r = new AlgoBollingerBands("Auto", stock, smaPeriod, stdDevMult, exitPercent, ps);
                        pool.execute(r);
                    }

                    runningStrategies.add(strategyID);
                }
            }
        }

        pool.shutdown();
    }
}







        /*
        int threadCount = 0;

        boolean exitTradePlatform = false;

        while(!exitTradePlatform) {

            ss.getAllStrats();

            AlgoTwoMovingAverages thread1 = new AlgoTwoMovingAverages(orderType, stock, shortSMAPeriod, longSMAPeriod, exitPercent);
            thread1.start();




    /*  @Scheduled(fixedRate = 250) // this is in milliseconds
      public void algorithmChecker() {

          System.out.println("Algorithm check executed.");
         // List prices = ps.getLastNPricesOfStock("GOOG", 20);
      }
  */


        //loop thru strats, if no thread, create thread of strategy

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
