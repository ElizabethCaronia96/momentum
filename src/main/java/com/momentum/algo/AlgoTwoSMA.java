package com.momentum.algo;

import java.util.ArrayList;

public class AlgoTwoSMA {

    SMA shortSMA;
    SMA longSMA;

    boolean shortSMALower;

    double purchasePrice;
    double currentPrice;

    /**
     * Executes the two SMA strategy for a trade request.
     * @param orderType "Auto" order type will place buy and sell trades when the strategy is triggered.
     *                  "Buy" order type will place only buy trades when the strategy is triggered.
     *                  "Sell" order type will place only sell trades when the strategy is triggered.
     * @param shortSMARange the range of the short SMA.
     * @param longSMARange the range of the long SMA.
     * @param exitPercent the profit or loss percent for the exit condition.
     */
    public void executeStrategy(String orderType, int shortSMARange, int longSMARange, double exitPercent) {

        if(!orderType.equalsIgnoreCase("Auto") && !orderType.equalsIgnoreCase("Buy") && !orderType.equalsIgnoreCase("Sell")) {
            System.out.println("ERROR: Trade request was not of order type 'Auto' or 'Buy' or 'Sell'.");
        }

        shortSMA = new SMA(shortSMARange);
        longSMA = new SMA(longSMARange);

        shortSMA.initialize(getPastPrices());
        longSMA.initialize(getPastPrices());

        boolean crossed = false;
        double newPrice = 0.0;

        while(!crossed) {

            setSMAComparison();

            newPrice = getNewPrice();
            shortSMA.update(newPrice);
            longSMA.update(newPrice);

            crossed = hasCrossed(orderType);
        }

        purchasePrice = newPrice;

        if(shortSMA.average >= longSMA.average) {
            placeOrder("Buy", purchasePrice);
            exitStrategy("Sell", exitPercent);
        }
        else {
            placeOrder("Sell", purchasePrice);
            exitStrategy("Buy", exitPercent);
        }
    }

    /**
     * Executes the exit condition for a trade request.
     * @param orderType "Auto" or "Buy" or "Sell" order type.
     * @param exitPercent the profit or loss percent for the exit condition.
     */
    public void exitStrategy(String orderType, double exitPercent) {

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

        placeOrder(orderType, currentPrice);
    }

    /**
     * This method is called to get the past stock prices needed to calculate the short SMA and long SMA when the strategy is first executed.
     * @return the past stock prices needed to initialize the SMA.
     */
    public ArrayList<Double> getPastPrices() {

        ArrayList<Double> array = new ArrayList<Double>();
        return array;
    }

    /**
     * This method is called to get the new stock price.
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
     * This method is called to check if the strategy has triggered (short SMA has crossed the long SMA).
     * @param orderType "Auto" or "Buy" or "Sell" order type.
     * @return true for order type "Auto" if the short SMA has crossed the long SMA.
     *         true for order type "Buy" if the short SMA has crossed above the long SMA.
     *         true for order type "Sell" if the short SMA has crossed below the long SMA.
     *         false otherwise.
     */
    public boolean hasCrossed(String orderType) {

        if(orderType.equalsIgnoreCase("Auto")) {

            if(shortSMALower && shortSMA.average >= longSMA.average) {
                return true;
            }
            if(!shortSMALower && shortSMA.average < longSMA.average) {
                return true;
            }
            return false;
        }
        if(orderType.equalsIgnoreCase("Buy")) {

            if(shortSMALower && shortSMA.average >= longSMA.average) {
                return true;
            }
            return false;
        }
        if(orderType.equalsIgnoreCase("Sell")) {

            if(!shortSMALower && shortSMA.average < longSMA.average) {
                return true;
            }
            return false;
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
