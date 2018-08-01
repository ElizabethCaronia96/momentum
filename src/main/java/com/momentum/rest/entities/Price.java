package com.momentum.rest.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity @Table(name = "prices")


public class Price implements Serializable {

    public Price() {
    }

    public Price(String stock, double price, Timestamp datetime) {
        this.stock = stock;
        this.price = price;
        this.datetime = datetime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private int priceID;
    @Column(name = "stock")
    private String stock;
    @Column(name = "price")
    private double price;
    @Column(name = "datetime")
    private Timestamp datetime;

    public int getPriceID() {
        return priceID;
    }

    public void setPriceID(int priceID) {
        this.priceID = priceID;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }
}
