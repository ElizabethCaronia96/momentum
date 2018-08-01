package com.momentum.algo;

import java.util.ArrayList;

public class AlgoTwoMovingAverages {

    SMA shortSMA;
    SMA longSMA;

    boolean shortSMAIsLower;

    /**
     * The number of trades that have been executed.
     */
    int tradeCounter;
    /**
     * The price of every buy and sell trade is added to these lists.
     */
    ArrayList<Double> buyPrices;
    ArrayList<Double> sellPrices;
    /**
     * The stock price of the first executed buy or sell trade. Total profit/loss accumulated over the strategy
     * is measured against this price for the strategy exit condition.
     */
    double initialPrice;
    /**
     * The total profit accumulated over the strategy lifetime.
     */
    double profit;

    /**
     * Executes the two SMA strategy for a trade request.
     * @param orderType "Auto" order type will place buy and sell trades when the strategy is triggered.
     *                  "Buy" order type will place only buy trades when the strategy is triggered.
     *                  "Sell" order type will place only sell trades when the strategy is triggered.
     * @param shortSMAPeriod the time period of the short SMA.
     * @param longSMAPeriod the time period of the long SMA.
     * @param exitPercent the profit or loss percent for the exit condition.
     */
    public void executeStrategy(String orderType, int shortSMAPeriod, int longSMAPeriod, double exitPercent) {

        if(!orderType.equalsIgnoreCase("Auto") && !orderType.equalsIgnoreCase("Buy") && !orderType.equalsIgnoreCase("Sell")) {
            System.out.println("ERROR: Trade request was not of order type 'Auto' or 'Buy' or 'Sell'.");
        }

        shortSMA = new SMA(shortSMAPeriod);
        longSMA = new SMA(longSMAPeriod);

        shortSMA.initialize(getPastPrices());
        longSMA.initialize(getPastPrices());

        boolean exit = false;
        tradeCounter = 0;
        profit = 0.0;

        // loop on exit condition
        while(!exit) {

            boolean crossed = false;
            double newPrice = 0.0;

            // loop on short sma crossing long sma
            while(!crossed) {

                setSMAComparison();

                newPrice = getNewPrice();
                shortSMA.update(newPrice);
                longSMA.update(newPrice);

                crossed = hasCrossed(orderType);
            }

            // execute trade
            if(shortSMA.average >= longSMA.average) {
                placeOrder("Buy", newPrice);
                buyPrices.add(newPrice);
            }
            else {
                placeOrder("Sell", newPrice);
                sellPrices.add(newPrice);
            }

            tradeCounter++;
            if(tradeCounter == 1) {
                initialPrice = newPrice;
            }
            if(tradeCounter % 2 == 0) {
                profit += (sellPrices.get(tradeCounter/2 - 1) - buyPrices.get(tradeCounter/2 - 1));
            }

            exit = exitCondition(exitPercent);
        }
    }

    /**
     * Executes the exit condition for a trade request.
     * @param exitPercent the profit or loss percent for the exit condition.
     * @return true if the strategy exit condition is reached.
     *         false otherwise
     */
    public boolean exitCondition(double exitPercent) {

        if(Math.abs(profit) >= initialPrice * exitPercent) {

            return true;
        }
        return false;
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
            shortSMAIsLower = true;
        }
        else {
            shortSMAIsLower = false;
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

            if(shortSMAIsLower && shortSMA.average >= longSMA.average) {
                return true;
            }
            if(!shortSMAIsLower && shortSMA.average < longSMA.average) {
                return true;
            }
            return false;
        }
        if(orderType.equalsIgnoreCase("Buy")) {

            if(shortSMAIsLower && shortSMA.average >= longSMA.average) {
                return true;
            }
            return false;
        }
        if(orderType.equalsIgnoreCase("Sell")) {

            if(!shortSMAIsLower && shortSMA.average < longSMA.average) {
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
