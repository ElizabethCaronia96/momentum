package com.momentum.jms;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Order {

    private String buyXML;

    public String getBuyXML() {
        return "<buy>"+buyXML+"</buy>\n";
    }

    public String getIdXML() {
        return "<id>"+ idXML+"</id>\n";
    }

    public String getPriceXML() {
        return "<price>"+priceXML+"</price>\n";
    }

    public String getSizeXML() {
        return "<size>"+sizeXML+"</size>\n";
    }

    public String getStockXML() {
        return "<stock>"+stockXML+"</stock>\n";
    }

    public String getWhenAsDateXML() {
        return "<whenAsDate>"+whenAsDateXML+"</whenAsDate>\n";
    }

    public String parseOrder(){
       return "<trade>\n" +
                getBuyXML()+
               getIdXML()+
                getPriceXML() +
               getSizeXML() +
                getStockXML() +
               getWhenAsDateXML()+
                "</trade>";

    }
    private String idXML;
    private String priceXML;
    private String sizeXML;
    private String stockXML;
    private String whenAsDateXML;

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
