package com.momentum.algo;

import java.util.ArrayList;

public class TestAlgoTwoMovingAverages {

    public static double dummyPrice = 1000000.0;
    public static double upperLimit = dummyPrice * 1.1;
    public static double lowerLimit = dummyPrice * 0.9;
    public static boolean upTrend = true;

    public static void main(String[] args) {

//        AlgoTwoMovingAverages test1 = new AlgoTwoMovingAverages();
//        test1.executeStrategy("Auto", 2, 10, 0.20);


    }

    public static ArrayList<Double> dummyPastPrices(int period) {

        ArrayList<Double> result = new ArrayList<Double>();

        for(double d = dummyPrice; d < dummyPrice + period; d++) {
            result.add(d);
        }

        return result;
    }

    public static double dummyNewPrice() {

        if(upTrend) {
            dummyPrice+=(dummyPrice*0.01);
            if(dummyPrice >= upperLimit) {
                upTrend = false;
            }
        }
        else {
            dummyPrice-=(dummyPrice*0.01);
            if(dummyPrice <= lowerLimit) {
                upTrend = true;
            }
        }
        return dummyPrice;
    }
}
