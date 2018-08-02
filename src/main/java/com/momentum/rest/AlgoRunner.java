package com.momentum.rest;


import com.momentum.rest.entities.Strategies;
import com.momentum.rest.service.PriceService;
import com.momentum.rest.service.StrategiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.momentum.algo.AlgoBollingerBands;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Service
public class AlgoRunner {

    @Autowired
    private PriceService ps;

    @Autowired
    private StrategiesService ss;

  /*  @Scheduled(fixedRate = 250) // this is in milliseconds
    public void algorithmChecker() {

        System.out.println("Algorithm check executed.");
       // List prices = ps.getLastNPricesOfStock("GOOG", 20);
    }
*/
    @PostConstruct
    public void runOnce(){
         //   ss.getAllActive();
           Map<Strategies, Object> relist =  ss.getAllActive();
           for(Map.Entry<Strategies, Object> entry: relist.entrySet()){
              // System.out.println("should have everything");
               System.out.println(entry.getKey()+" / "+ entry.getValue());
           }


    }


}
