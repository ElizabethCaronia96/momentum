package com.momentum.algo;

import java.util.ArrayList;
import java.util.LinkedList;

public class SMAWithSD {

    /**
     * Holds all stock prices over the time period of the moving average.
     */
    LinkedList<Double> queue;
    /**
     * The time period of the moving average.
     */
    int period;
    /**
     * The value of the moving average.
     */
    double average;
    /**
     * The standard deviation of the stock prices in the time period of the moving average.
     */
    double stdDev;

    /**
     * Constructor initializing the queue of stock prices and the SMA time period.
     * @param period the SMA time period.
     */
    public SMAWithSD(int period) {
        queue = new LinkedList<Double>();
        this.period = period;
    }

    /**
     * Initializes the SMA with the past stock prices and calculates the average and standard deviation.
     * @param pastPrices the past stock prices.
     */
    public void initialize(ArrayList<Double> pastPrices) {

        if(period != pastPrices.size()) {
            System.out.println("ERROR: Size of list containing past stock prices does not match SMA range.");
            return;
        }

        while(queue.size() < period) {

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
     * Updates the SMA with the new stock price and calculates the average and standard deviation efficiently using the old and new prices.
     * @param newPrice the new stock price.
     */
    public void update(Double newPrice) {

        if(Double.compare(queue.peek(), newPrice) == 0) {
            return;
        }

        if(queue.size() < period) {

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
