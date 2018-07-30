package com.momentum.strategies;

import com.momentum.jms.Order;
import com.momentum.jms.OrderSender;

public class TwoMV implements Strategy {
    private static final int MAX_STOCKS  = 1000;
    private String ticker;
    private int num_stocks;
    public TwoMV(String strategy, String stock, int num_stocks){
        ticker = stock;
        num_stocks = num_stocks;
    }

//question: isn't this likely to always make a move no matter what?
    @Override
    public String watch() {
        boolean triggerHit = false;
        OrderSender osend = new OrderSender();
        while(!triggerHit ){
            double sAv = getShortAverage();
            double lAv = getLongAverage();
//we should change every price to be double or float, not mixed
            if(sAv > lAv) {
                Order buy = new Order(true, getPrice(), (MAX_STOCKS-num_stocks), ticker);
                osend.send(buy);
                triggerHit = true;
            }
            else if (lAv > sAv){
                Order sell = new Order(false, getPrice(), num_stocks, ticker);
                osend.send(sell);
                triggerHit = true;
            }
        }

        return "filled";
    }


//as you can imagine this is dummy values rn
    public double getShortAverage(){

        return 1.0;
    }

    public double getLongAverage(){

        return 10.0;
    }

    public float getPrice(){
        return 10.99f;
    }
}
