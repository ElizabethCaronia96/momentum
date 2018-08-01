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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private int orderId;


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Integer getStrategy_id() {
        return strategyId;
    }

    public void setStrategy_id(Integer strategy_id) {
        this.strategyId = strategy_id;
    }

    public Timestamp getCrossStartDate() {
        return crossStartDate;
    }

    public void setCrossStartDate(Timestamp crossStartDate) {
        this.crossStartDate = crossStartDate;
    }

    public Double getCrossStartPrice() {
        return crossStartPrice;
    }

    public void setCrossStartPrice(Double crossStartPrice) {
        this.crossStartPrice = crossStartPrice;
    }

    public String getCrossEndType() {
        return crossEndType;
    }

    public void setCrossEndType(String crossEndType) {
        this.crossEndType = crossEndType;
    }

    public Timestamp getCrossEndDate() {
        return crossEndDate;
    }

    public void setCrossEndDate(Timestamp crossEndDate) {
        this.crossEndDate = crossEndDate;
    }

    public Double getCrossEndPrice() {
        return crossEndPrice;
    }

    public void setCrossEndPrice(Double crossEndPrice) {
        this.crossEndPrice = crossEndPrice;
    }

    @Column(name="strategy_id") private Integer strategyId;
    @Column(name="crossover_start_type") private String crossStartType;

    @Column(name="crossover_start_datetime") private Timestamp crossStartDate;
    @Column(name="crossover_start_price") private Double crossStartPrice;
    @Column(name="crossover_end_type") private     String crossEndType;
    @Column(name="crossover_end_datetime") private  Timestamp crossEndDate;
    @Column(name="crossover_end_price") private Double crossEndPrice;


    public String getCrossStartType() {
        return crossStartType;
    }

    public void setCrossStartType(String crossStartType) {
        this.crossStartType = crossStartType;
    }
}