package com.momentum.algo;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AlgoPriceBreakout {

    /**
     * Executes the Price Breakout strategy.
     * @param period the open/close high/low time period.
     * @param exitPercent the profit or loss percent for the exit condition.
     */
    public void executeStrategy(int period, double exitPercent) {

        ArrayList<Double> previousPrices = null;
        ArrayList<Double> currentPrices = getPricesInNextPeriod(period);
        ArrayList<Double> squeezePrices;

        boolean squeezed = false;

        // loop on high/low squeeze
        while(!squeezed) {

            previousPrices = currentPrices;
            currentPrices = getPricesInNextPeriod(period);

            squeezed = isSqueezed(getHigh(currentPrices), getLow(currentPrices), getOpen(previousPrices), getClose(previousPrices));
        }

        squeezePrices = previousPrices;

        boolean breakout = false;

        // loop on high/low breakout
        while(!breakout) {

            currentPrices = getPricesInNextPeriod(period);
            breakout = isBreakout(getHigh(currentPrices), getLow(currentPrices), getOpen(squeezePrices), getClose(squeezePrices));
        }

        // execute trade
        double purchasePrice = getClose(currentPrices);

        if(getClose(currentPrices) >= getOpen(currentPrices)) {

            placeOrder("Buy", purchasePrice);
            exitCondition("Sell", purchasePrice, exitPercent);
        }
        else {

            placeOrder("Sell", purchasePrice);
            exitCondition("Buy", purchasePrice, exitPercent);
        }
    }

    /**
     * Executes the exit condition for buy or sell trade position.
     * @param orderType "Buy" or "Sell" order.
     * @param purchasePrice the stock price the buy or sell trade position was taken at.
     * @param exitPercent the profit or loss percent for the exit condition.
     */
    public void exitCondition(String orderType, double purchasePrice, double exitPercent) {

        boolean exit = false;
        double currentPrice = 0.0;

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
     * This method is called to get all the prices within the next fixed time period.
     * @param period the time period in which to get all the stock prices.
     * @return the prices in the time period.
     */
    public ArrayList<Double> getPricesInNextPeriod(int period) {

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
     * Gets the open price for a time period.
     * @param prices the list of prices.
     * @return the open price.
     */
    public double getOpen(ArrayList<Double> prices) {

        return prices.get(0);
    }

    /**
     * Gets the close price for a time period.
     * @param prices the list of prices.
     * @return the close price.
     */
    public double getClose(ArrayList<Double> prices) {

        return prices.get(prices.size() - 1);
    }

    /**
     * Gets the high price for a time period.
     * @param prices the list of prices.
     * @return the high price.
     */
    public double getHigh(ArrayList<Double> prices) {

        double max = 0.0;

        for(Double price: prices) {

            if(price > max) {
                max = price;
            }
        }

        return max;
    }

    /**
     * Gets the low price for a time period.
     * @param prices the list of prices.
     * @return the low price.
     */
    public double getLow(ArrayList<Double> prices) {

        double min = Double.MAX_VALUE;

        for(Double price: prices) {

            if(price < min) {
                min = price;
            }
        }

        return min;
    }

    /**
     * This method is called to check if the 1st part of the strategy has triggered (high/low fits inside previous period's open/close).
     * @param high current period's high price.
     * @param low current period's low price.
     * @param open previous period's open price.
     * @param close previous period's close price.
     * @return true if high/low fits inside previous period's open/close.
     */
    public boolean isSqueezed(double high, double low, double open, double close) {

        if(open == close) {

            if(high == open && low == open) {
                return true;
            }
            return false;
        }
        if(open < close) {

            if(high >= open && high <= close && low >= open && low <= close) {
                return true;
            }
            return false;
        }
        if(open > close) {

            if(high <= open && high >= close && low <= open && low >= close) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * This method is called to check if the 2nd part of the strategy has triggered (high/low goes outside previous period's open/close).
     * @param high current period's high price.
     * @param low current period's low price.
     * @param open previous period's open price.
     * @param close previous period's close price.
     * @return true if high/low fits inside previous period's open/close.
     */
    public boolean isBreakout(double high, double low, double open, double close) {

        if(open == close) {

            if(high == open && low == open) {
                return false;
            }
            return true;
        }
        if(open < close) {

            if(high >= open && high <= close && low >= open && low <= close) {
                return false;
            }
            return true;
        }
        if(open > close) {

            if(high <= open && high >= close && low <= open && low >= close) {
                return false;
            }
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
