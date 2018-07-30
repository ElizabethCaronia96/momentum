package com.momentum.jms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "trade")
public class BrokerReply {

    @XmlElement(name = "buy")
    private boolean buy;
    @XmlElement(name = "id")
    private int id;
    @XmlElement(name = "price")
    private double price;
    @XmlElement(name = "size")
    private int size;
    private String stock;
    private String whenAsDate;

    public boolean isBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy.equals("true");
    }

    public int getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = Double.parseDouble(price);
    }

    public int getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = Integer.parseInt(size);
    }

    @XmlElement(name = "stock")
    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    @XmlElement(name = "whenAsDate")
    public String getWhenAsDate() {
        return whenAsDate;
    }

    public void setWhenAsDate(String whenAsDate) {
        this.whenAsDate = whenAsDate;
    }

}