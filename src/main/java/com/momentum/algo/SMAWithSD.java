package com.momentum.algo;

import java.util.ArrayList;
import java.util.LinkedList;

public class SMAWithSD {

    LinkedList<Double> queue;
    int range;
    double average;
    double stdDev;

    /**
     * Constructor initializing the queue of stock prices and the SMA range.
     * @param range the SMA range.
     */
    public SMAWithSD(int range) {
        queue = new LinkedList<Double>();
        this.range = range;
    }

    /**
     * Initializes the SMA with the past stock prices and calculates the average and standard deviation.
     * @param pastPrices the past stock prices.
     */
    public void initialize(ArrayList<Double> pastPrices) {

        if(range != pastPrices.size()) {
            System.out.println("ERROR: Size of list containing past stock prices does not match SMA range.");
            return;
        }

        while(queue.size() < range) {

            for(Double price : pastPrices) {

                queue.add(price);
            }
        }

        double sum = 0.0;
        for(Double price : queue) {
            sum += price;
        }

        average = sum / queue.size();

        stdDev = 0.0;

        for(Double price: queue) {
            stdDev += ((price - average) * (price - average));
        }

        stdDev = stdDev / queue.size();

        stdDev = Math.sqrt(stdDev);
    }

    /**
     * Updates the SMA with the new stock price and calculates the average and standard deviation.
     * @param newPrice the new stock price.
     */
    public void update(double newPrice) {

        if(queue.size() < range) {

            queue.add(newPrice);

            double sum = 0.0;
            for(Double price : queue) {
                sum += price;
            }

            average = sum / queue.size();

            stdDev = 0.0;

            for(Double price: queue) {
                stdDev += ((price - average) * (price - average));
            }

            stdDev = Math.sqrt(stdDev / queue.size());
        }
        else {

            double oldPrice = queue.remove();

            queue.add(newPrice);

            double oldAverage = average;
            average = average + ((newPrice - oldPrice) / queue.size());

            stdDev = Math.sqrt((stdDev * stdDev) + ((newPrice - oldPrice) * (newPrice - average + oldPrice - oldAverage) / queue.size()));
        }
    }
}
