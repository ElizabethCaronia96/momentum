package com.momentum.jms;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Order {

    private String buyXML;
    private String idXML;
    private String priceXML;
    private String sizeXML;
    private String stockXML;
    private String whenAsDateXML;

    public String getBuyXML() {
        return buyXML;
    }

    public String getIdXML() {
        return idXML;
    }

    public String getPriceXML() {
        return priceXML;
    }

    public String getSizeXML() {
        return sizeXML;
    }

    public String getStockXML() {
        return stockXML;
    }

    public String getWhenAsDateXML() {
        return whenAsDateXML;
    }

    public Order(boolean buy, float price, int size, String stock) {

        if (buy) {
            this.buyXML = "true";
        } else {
            this.buyXML = "false";
        }

        this.priceXML = Float.toString(price);
        this.sizeXML = Integer.toString(size);
        this.stockXML = stock;

        TimeZone tz = TimeZone.getTimeZone("ETC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(tz);
        this.whenAsDateXML = df.format(new Date());
    }
}
