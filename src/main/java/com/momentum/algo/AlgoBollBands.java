package com.momentum.algo;

import java.util.ArrayList;

public class AlgoBollBands {

    SMAWithSD smaWithSD;

    double purchasePrice;
    double currentPrice;

    public void executeStrategy(String orderType, int smaRange, double stdDev, double exitPercent) {

        if(!orderType.equalsIgnoreCase("Buy") && !orderType.equalsIgnoreCase("Sell")) {
            System.out.println("ERROR: Trade request was not a buy or a sell order.");
        }

        smaWithSD = new SMAWithSD(smaRange);

        smaWithSD.initialize(getPastPrices());

        boolean crossed = false;
        double newPrice = 0.0;

        while(!crossed) {

            newPrice = getNewPrice();
            smaWithSD.update(newPrice);

            crossed = hasCrossed(newPrice);
        }

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

    public boolean hasCrossed(Double newPrice) {

        if(newPrice >= smaWithSD.average + smaWithSD.stdDev * 2.0) {
            return true;
        }
        if(newPrice <= smaWithSD.average - smaWithSD.stdDev * 2.0) {
            return true;
        }
        return false;
    }
}
