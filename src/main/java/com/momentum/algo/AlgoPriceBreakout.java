package com.momentum.algo;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AlgoPriceBreakout {

    public void executeStrategy(int period, double exitPercent) {

        ArrayList<Double> previousPrices = getPricesInPeriod();
        ArrayList<Double> currentPrices = getPricesInPeriod();

        boolean narrowed = false;

        while(!narrowed) {

            narrowed = hasNarrowed(getHigh(currentPrices), getLow(currentPrices), getOpen(previousPrices), getClose(previousPrices));
        }
    }

    /**
     * This method is called to get all the prices within a fixed time period.
     * @return the prices in the time period.
     */
    public ArrayList<Double> getPricesInPeriod() {

        ArrayList<Double> array = new ArrayList<Double>();
        return array;
    }

    public double getOpen(ArrayList<Double> prices) {

        return prices.get(0);
    }

    public double getClose(ArrayList<Double> prices) {

        return prices.get(prices.size() - 1);
    }

    public double getHigh(ArrayList<Double> prices) {

        double max = 0.0;

        for(Double price: prices) {

            if(price > max) {
                max = price;
            }
        }

        return max;
    }

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
     * This method is called to check if the strategy has triggered (high/low fits inside previous period's open/close).
     * @param high current period's high price.
     * @param low current period's low price.
     * @param open previous period's open price.
     * @param close previous period's close price.
     * @return true if high/low fits inside previous period's open/close.
     */
    public boolean hasNarrowed(double high, double low, double open, double close) {

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
}
