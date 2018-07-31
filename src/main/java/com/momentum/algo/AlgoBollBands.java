package com.momentum.algo;

import java.util.ArrayList;

public class AlgoBollBands {

    SMAWithSD smaWithSD;

    double purchasePrice;
    double currentPrice;

    /**
     * Executes the two SMA strategy for a trade request.
     * @param orderType "Auto" order type will place buy and sell trades when the strategy is triggered.
     *                  "Buy" order type will place only buy trades when the strategy is triggered.
     *                  "Sell" order type will place only sell trades when the strategy is triggered.
     * @param smaRange the range of the SMA.
     * @param stdDevMult the multiple of the standard deviation that sets the low and high bands about the SMA.
     * @param exitPercent the profit or loss percent for the exit condition.
     */
    public void executeStrategy(String orderType, int smaRange, double stdDevMult, double exitPercent) {

        if(!orderType.equalsIgnoreCase("Auto") && !orderType.equalsIgnoreCase("Buy") && !orderType.equalsIgnoreCase("Sell")) {
            System.out.println("ERROR: Trade request was not of order type 'Auto' or 'Buy' or 'Sell'.");
        }

        smaWithSD = new SMAWithSD(smaRange);

        smaWithSD.initialize(getPastPrices());

        boolean crossed = false;
        double newPrice = 0.0;

        while(!crossed) {

            newPrice = getNewPrice();
            smaWithSD.update(newPrice);

            crossed = hasCrossed(orderType, newPrice, stdDevMult);
        }

        purchasePrice = newPrice;

        if(purchasePrice <= smaWithSD.average - smaWithSD.stdDev * stdDevMult) {
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
