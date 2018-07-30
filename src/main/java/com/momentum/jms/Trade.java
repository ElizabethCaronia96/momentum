package com.momentum.jms;

import java.util.Date;

public class Trade {

    String buy;
    int id;
    double price;
    int size;
    String stock;
    Date date;

    public Trade(String buy, int id, double price, int size, String stock, Date date) {
        this.buy = buy;
        this.id = id;
        this.price = price;
        this.size = size;
        this.stock = stock;
        this.date = date;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
