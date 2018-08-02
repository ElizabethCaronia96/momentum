package com.momentum.rest;


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
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class AlgoRunner {

    @Autowired
    private PriceService ps;

    @Autowired
    private StrategiesService ss;

    @Autowired
    private OrderService os;

    /*  @Scheduled(fixedRate = 250) // this is in milliseconds
      public void algorithmChecker() {

          System.out.println("Algorithm check executed.");
         // List prices = ps.getLastNPricesOfStock("GOOG", 20);
      }
  */
    @PostConstruct
    public void runOnce() {
        Map<Strategies, Object> relist = ss.getAllActive();
        for (Map.Entry<Strategies, Object> entry : relist.entrySet()) {
            // System.out.println("should have everything");
            System.out.println(entry.getKey() + " / " + entry.getValue());
        }

        Order trial2 = new Order(2222,"sell",  Timestamp.valueOf("2018-08-02 12:36:00.0"), 101.99 );
       os.addOrder(trial2);
        Order trail1 = os.getAllOpenPositions().get(0);
        System.out.println("strategy_id: "+ trail1.getStrategyId()+" cross end date "+ trail1.getCrossoverEndDatetime());
        trail1.setCrossoverEndDatetime(Timestamp.valueOf("2018-08-02 12:36:00.0"));
        os.updateOrder(trail1);
        System.out.println("strategy_id: "+ trail1.getStrategyId()+" cross end date "+ trail1.getCrossoverEndDatetime());


    }


}
