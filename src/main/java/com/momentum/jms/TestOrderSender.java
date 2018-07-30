package com.momentum.jms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestOrderSender {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");
        OrderSender orderSender = new OrderSender();

        Random r = new Random();

        List<String> stockList = Arrays.asList("AAPL", "GOOG", "BRK-A", "NSC", "MSFT");

        for (int i = 1; i <= 100; i++) {

            System.out.println("ORDER # " + i);
            double rangeMinDouble = 1.00;
            double rangeMaxDouble = 100.00;
            double randPrice = rangeMinDouble + (rangeMaxDouble - rangeMinDouble) * r.nextDouble();

            int rangeMinInt = 1;
            int rangeMaxInt = 1000;
            int randSize = r.nextInt((rangeMaxInt - rangeMinInt) + 1) + rangeMinInt;

            String randStock = stockList.get(r.nextInt(stockList.size()));

            Order newOrder = new Order(true, randPrice, randSize, randStock);
            newOrder.setIdXML(i);
            orderSender.send(newOrder);

            // add delay to sending
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }


        appContext.close();
    }
}
