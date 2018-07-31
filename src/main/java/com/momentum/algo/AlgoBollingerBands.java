package com.momentum.algo;

import java.util.ArrayList;

public class AlgoBollingerBands {

    SMAWithSD smaWithSD;

    int tradeCounter;
    ArrayList<Double> buyPrices;
    ArrayList<Double> sellPrices;
    /**
     * The stock price of the first executed buy or sell trade. Total profit/loss accumulated over the strategy
     * is measured against this price for the strategy exit condition.
     */
    double initialPrice;
    double profit;

    /**
     * Executes the two SMA strategy for a trade request.
     * @param orderType "Auto" order type will place buy and sell trades when the strategy is triggered.
     *                  "Buy" order type will place only buy trades when the strategy is triggered.
     *                  "Sell" order type will place only sell trades when the strategy is triggered.
     * @param smaPeriod the time period of the SMA.
     * @param stdDevMult the multiple of the standard deviation that sets the low and high bands about the SMA.
     * @param exitPercent the profit or loss percent for the exit condition.
     */
    public void executeStrategy(String orderType, int smaPeriod, double stdDevMult, double exitPercent) {

        if(!orderType.equalsIgnoreCase("Auto") && !orderType.equalsIgnoreCase("Buy") && !orderType.equalsIgnoreCase("Sell")) {
            System.out.println("ERROR: Trade request was not of order type 'Auto' or 'Buy' or 'Sell'.");
        }

        smaWithSD = new SMAWithSD(smaPeriod);

        smaWithSD.initialize(getPastPrices());

        boolean exit = false;
        tradeCounter = 0;
        profit = 0.0;

        // loop on exit condition
        while(!exit) {

            boolean crossed = false;
            double newPrice = 0.0;

            // loop on stock price hitting low or high band
            // TODO: force bollinger bands strategy to alternate between buy and sell trades
            while(!crossed) {

                newPrice = getNewPrice();
                smaWithSD.update(newPrice);

                crossed = hasCrossed(orderType, newPrice, stdDevMult);
            }

            // execute trade
            if(newPrice <= smaWithSD.average - smaWithSD.stdDev * stdDevMult) {
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
                profit += (sellPrices.get(tradeCounter/2) - buyPrices.get(tradeCounter/2));
            }

            exit = exitCondition(exitPercent);
        }
    }

    /**
     * Executes the exit condition for a trade request.
     * @param exitPercent the profit or loss percent for the exit condition.
     * @return true if the strategy exit condition is reached.
     *         false otherwise.
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
     * This method is called to check if the strategy has triggered (stock price hits low band or high band).
     * @param orderType "Auto" or "Buy" or "Sell" order type.
     * @param newPrice the new stock price to check against the low and high bands.
     * @param stdDevMult the multiple of the standard deviation that sets the low and high bands about the SMA.
     * @return true for order type "Auto" if the stock price hits the low band or the high band.
     *         true for order type "Buy" if the stock price hits the low band.
     *         true for order type "Sell" if the stock price hits the high band.
     *         false otherwise.
     */
    public boolean hasCrossed(String orderType, Double newPrice, Double stdDevMult) {

        if(orderType.equalsIgnoreCase("Auto")) {

            if(newPrice <= smaWithSD.average - smaWithSD.stdDev * stdDevMult) {
                return true;
            }
            if(newPrice >= smaWithSD.average + smaWithSD.stdDev * stdDevMult) {
                return true;
            }
            return false;
        }
        if(orderType.equalsIgnoreCase("Buy")) {

            if(newPrice <= smaWithSD.average - smaWithSD.stdDev * stdDevMult) {
                return true;
            }
            return false;
        }
        if(orderType.equalsIgnoreCase("Sell")) {

            if(newPrice >= smaWithSD.average + smaWithSD.stdDev * stdDevMult) {
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
