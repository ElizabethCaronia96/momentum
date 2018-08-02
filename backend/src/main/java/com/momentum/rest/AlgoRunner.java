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

        Map<Strategies, Object> allstrategiesMap = ss.getAllActive();


        Map.Entry<Strategies,Object> tmp_entry = allstrategiesMap.entrySet().iterator().next();
        Strategies key = tmp_entry.getKey();
        Object value = tmp_entry.getValue();
        Map<Strategies, Object> strategiesMap = new HashMap<>();
        strategiesMap.put(key, value);

        while (!exitMomentum) {

            //System.out.println("we are in algo checker while loop" + runningStrategies);
            // strategies in db

           // System.out.println("our map with 1 thing in it hopefully " + strategiesMap);

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

                    System.out.println("this strat not running - make thread");
                    System.out.println(entry.getKey().getClass());
                  //  List<Object> temp = entry.getValue();
                    Object obj = entry.getValue();
                    System.out.println(obj.getClass());
                    System.out.println(obj.toString());
                    System.out.println(entry.getKey());
                    System.out.println(entry.getValue());

                    if (entry.getValue() instanceof TwoMA) {

                        String stock = entry.getKey().getStock();

                        TwoMA strat = ((TwoMA) (entry.getValue()));
                        int shortSMAPeriod = strat.getShortAvgRange();
                        int longSMAPeriod = strat.getLongAvgRange();
                        System.out.println("Running twoMA algo for strat id " + entry.getKey().getStrategyId());
                        double exitPercent = strat.getPercentToExit() / 100.0;


                        Runnable r = new AlgoTwoMovingAverages("Auto", stock, shortSMAPeriod, longSMAPeriod, exitPercent);
                    } else if (entry.getValue() instanceof BB) {

                        String stock = entry.getKey().getStock();

                        BB strat = ((BB) (entry.getValue()));
                        int smaPeriod = strat.getMovingAvgRange();
                        double stdDevMult = strat.getStdDevMultiple();
                        System.out.println("Running BB algo for strat id " + entry.getKey().getStrategyId());

                        double exitPercent = strat.getPercentToExit() / 100.0;

                        Runnable r = new AlgoBollingerBands("Auto", stock, smaPeriod, stdDevMult, exitPercent);
                    }

                    runningStrategies.add(strategyID);
                }
            }
        }
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
