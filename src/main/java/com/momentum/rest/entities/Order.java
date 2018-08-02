package com.momentum.rest.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "orders")
public class Order implements Serializable {


    public Order() {
    }

    public Order(int strategyId, String crossoverStartType, Timestamp crossoverStartDatetime, double crossoverStartPrice) {
        this.strategyId = strategyId;
        this.crossoverStartType = crossoverStartType;
        this.crossoverStartDatetime = crossoverStartDatetime;
        this.crossoverStartPrice = crossoverStartPrice;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "strategy_id")
    private int strategyId;
    @Column(name = "crossover_start_type")
    private String crossoverStartType;
    @Column(name = "crossover_start_datetime")
    private Timestamp crossoverStartDatetime;
    @Column(name = "crossover_start_price")
    private double crossoverStartPrice;
    @Column(name = "crossover_end_type")
    private String crossoverEndType;
    @Column(name = "crossover_end_datetime")
    private Timestamp crossoverEndDatetime;
    @Column(name = "crossover_end_price")
    private Double crossoverEndPrice;
    @Column(name = "profit_loss")
    private Double profitLoss;

    public int getOrderID() {
        return orderId;
    }

    public void setOrderID(int orderID) {
        this.orderId = orderID;
    }

    public int getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(int strategyId) {
        this.strategyId = strategyId;
    }

    public String getCrossoverStartType() {
        return crossoverStartType;
    }

    public void setCrossoverStartType(String crossoverStartType) {
        this.crossoverStartType = crossoverStartType;
    }

    public Timestamp getCrossoverStartDatetime() {
        return crossoverStartDatetime;
    }

    public void setCrossoverStartDatetime(Timestamp crossoverStartDatetime) {
        this.crossoverStartDatetime = crossoverStartDatetime;
    }

    public double getCrossoverStartPrice() {
        return crossoverStartPrice;
    }

    public void setCrossoverStartPrice(double crossoverStartPrice) {
        this.crossoverStartPrice = crossoverStartPrice;
    }

    public String getCrossoverEndType() {
        return crossoverEndType;
    }

    public void setCrossoverEndType(String crossoverEndType) {
        this.crossoverEndType = crossoverEndType;
    }

    public Timestamp getCrossoverEndDatetime() {
        return crossoverEndDatetime;
    }

    public void setCrossoverEndDatetime(Timestamp crossoverEndDatetime) {
        this.crossoverEndDatetime = crossoverEndDatetime;
    }

    public double getCrossoverEndPrice() {
        return crossoverEndPrice;
    }

    public void setCrossoverEndPrice(double crossoverEndPrice) {
        this.crossoverEndPrice = crossoverEndPrice;
    }

    public double getProfitLoss() {
        return profitLoss;
    }

    @JsonIgnore
    public void setProfitLoss(Double profitLoss) {

        this.profitLoss = profitLoss;

    }
}