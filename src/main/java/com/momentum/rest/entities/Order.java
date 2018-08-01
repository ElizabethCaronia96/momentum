package com.momentum.rest.entities;


import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Entity@Table(name="orders")

@NamedQueries(
        {
                @NamedQuery(name = "Order.getAll",
                        query = "select orderId from Order as o",
                         hints = {@QueryHint(name="org.hibernate.cacheable", value="true")})
        }
)
public class Order  implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "order_id")
    private int orderId;

    public int getOrder_id() {
        return orderId;
    }

    public void setOrder_id(int order_id) {
        this.orderId = order_id;
    }


    @Column(name = "num_stocks")

    private Integer  numstocks;


    @Column(name="status") private String status;
    @Column(name="stock") private String stock;

    public void setNumstocks(Integer numstocks) {
        this.numstocks = numstocks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(String strategyType) {
        this.strategyType = strategyType;
    }

    public Integer getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Integer strategyId) {
        this.strategyId = strategyId;
    }

    public Timestamp getDatetimeAdded() {
        return datetimeAdded;
    }

    public void setDatetimeAdded(Timestamp datetimeAdded) {
        this.datetimeAdded = datetimeAdded;
    }

    public String getEntryTime() {
        return entryType;
    }

    public void setEntryTime(String entryTime) {
        this.entryType = entryTime;
    }

    public Timestamp getEntrydatetime() {
        return entryDatetime;
    }

    public void setEntrydatetime(Timestamp entryDatetime) {
        this.entryDatetime = entryDatetime;
    }

    public Double getEntryprice() {
        return entryPrice;
    }

    public void setEntryprice(Double entryprice) {
        this.entryPrice = entryprice;
    }

    public String getExittype() {
        return exitType;
    }

    public void setExittype(String exittype) {
        this.exitType = exittype;
    }

    public Timestamp getExitDatetime() {
        return exitDatetime;
    }

    public void setExitDatetime(Timestamp exitDatetime) {
        this.exitDatetime = exitDatetime;
    }

    public Double getExitPrice() {
        return exitPrice;
    }

    public void setExitPrice(Double exitPrice) {
        this.exitPrice = exitPrice;
    }

    public Double getProfitLossPercent() {
        return profitLossPercent;
    }

    public void setProfitLossPercent(Double profitLossPercent) {
        this.profitLossPercent = profitLossPercent;
    }

    @Column(name="strateg_type") private String strategyType;
    @Column( name = "strategy_id") private Integer strategyId;
    @Column( name = "datetime_added") private Timestamp datetimeAdded;
    @Column( name = "entry_type") private String entryType;
    @Column( name ="entry_datetime") private Timestamp entryDatetime;
    @Column( name = "entry_price") private Double entryPrice;
    @Column( name = "exit_type") private  String exitType;
    @Column( name = "exit_datetime") private Timestamp exitDatetime;
    @Column( name = "exit_price") private Double exitPrice;
    @Column( name = "profit_loss_percent") private Double profitLossPercent;

   
    public Integer getNumstocks() {
        return numstocks;
    }

    public void setNumstocks(int num_stocks) {
        this.numstocks = num_stocks;
    }





    public Order() {

    }

    public Integer getStrategy_id() {
        return strategyId;
    }

    public void setStrategy_id(Integer strategy_id) {
        this.strategyId = strategy_id;
    }
}