package com.momentum.rest;


import com.momentum.algo.AlgoTwoMovingAverages;
import com.momentum.rest.entities.Strategies;

import com.momentum.rest.service.PriceService;
import com.momentum.rest.service.StrategiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.momentum.algo.AlgoBollingerBands;

import javax.annotation.PostConstruct;
import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.Map;


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

        
        Runnable r = new AlgoTwoMovingAverages("Auto", stock, shortSMAPeriod, longSMAPeriod, exitPercent);



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


/*      System.out.println("Algorithm check executed.");
        List prices20 = ps.getLastNPricesOfStock("GOOG", 20);
        List prices5 = ps.getLastNPricesOfStock("GOOG", 5);

        System.out.println(prices5);
        System.out.println(prices20);
*/

        // while loop

        // get all strats fom database
       /*

        strat_threads = {1:1, 2:2, 3:7}
        strat_id = 1 -> thread 1
        id = 2 -> thread 2
*/


    }



        //loop thru strats, if no thread, create thread of strategy



        // function call for each thread

        // example function {
        // initialize ur queues for this straegy
        // inside this thread, u call price service's get last N prices of stock

    
}

