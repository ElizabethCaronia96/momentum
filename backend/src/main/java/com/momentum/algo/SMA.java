package com.momentum.algo;

import java.util.ArrayList;
import java.util.LinkedList;

public class SMA {

    LinkedList<Double> queue;
    int range;
    double average;

    /**
     * Constructor initializing the queue of stock prices and the SMA range.
     * @param range the SMA range.
     */
    public SMA(int range) {
        queue = new LinkedList<Double>();
        this.range = range;
    }

    /**
     * Initializes the SMA with the past stock prices.
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

        double sum = 0;
        for(Double price : queue) {
            sum += price;
        }

        average = sum / queue.size();
    }

    /**
     * Updates the SMA with the new stock price.
     * @param newPrice the new stock price.
     */
    public void update(double newPrice) {

        if(queue.size() < range) {

            queue.add(newPrice);

            double sum = 0;
            for(Double price : queue) {
                sum += price;
            }

            average = sum / queue.size();
        }
        else {

            double head = queue.remove();

            queue.add(newPrice);

            average = average - (head/queue.size()) + (newPrice/queue.size());
        }
    }
}
