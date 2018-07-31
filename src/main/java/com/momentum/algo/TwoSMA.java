package com.momentum.algo;

import java.util.ArrayList;

public class TwoSMA {

    SMA shortSMA;
    SMA longSMA;

    boolean shortSMALower;

    double purchasePrice;
    double currentPrice;

    /**
     * Executes the two SMA strategy for a BUY request.
     * @param shortSMARange the range of the short SMA.
     * @param longSMARange the range of the long SMA.
     * @param exitPercent the profit or loss percent for the exit condition.
     */
    public void executeBuyStrategy(int shortSMARange, int longSMARange, double exitPercent) {

        shortSMA = new SMA(shortSMARange);
        longSMA = new SMA(longSMARange);

        shortSMA.initialize(getPastPrices());
        longSMA.initialize(getPastPrices());

        setSMAComparison();

        boolean crossed = false;
        double newPrice = 0.0;

        while(!crossed) {

            newPrice = getNewPrice();
            shortSMA.update(newPrice);
            longSMA.update(newPrice);

            crossed = hasCrossed();
        }

        purchasePrice = newPrice;
        placeOrder("Buy", purchasePrice);

        exitBuyStrategy(exitPercent);
    }

    /**
     * Executes the exit condition for a BUY request.
     * @param exitPercent the profit or loss percent for the exit condition.
     */
    public void exitBuyStrategy(double exitPercent) {

        boolean exit = false;

        while(!exit) {

            currentPrice = getNewPrice();

            if(currentPrice * (1.00 + exitPercent) >= purchasePrice) {
                exit = true;
            }
            else if(currentPrice * (1.00 - exitPercent) <= purchasePrice) {
                exit = true;
            }
        }

        placeOrder("Sell", currentPrice);
    }

    /**
     * @return the past stock prices needed to initialize the SMA.
     */
    public ArrayList<Double> getPastPrices() {

        ArrayList<Double> array = new ArrayList<Double>();
        return array;
    }

    /**
     * @return the new stock price needed to update the SMA.
     */
    public double getNewPrice() {

        return 0.0;
    }

    /**
     * Sets the value of the boolean variable comparing the short SMA and long SMA.
     */
    public void setSMAComparison() {

        if(shortSMA.average < longSMA.average) {
            shortSMALower = true;
        }
        else {
            shortSMALower = false;
        }
    }

    /**
     *
     * @return true if the short SMA has crossed the long SMA.
     */
    public boolean hasCrossed() {

        if(shortSMALower && shortSMA.average >= longSMA.average) {
            return true;
        }
        else if(!shortSMALower && shortSMA.average < longSMA.average) {
            return true;
        }
        return false;
    }

    /**
     * This method is called to place the trade order when the strategy is triggered.
     * @param orderType buy or sell order.
     * @param price the purchase price.
     */
    public void placeOrder(String orderType, double price) {

        // place the trade order here
    }
}
