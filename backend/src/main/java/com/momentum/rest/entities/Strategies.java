package com.momentum.rest.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="strategies")
public class Strategies {

    public int getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(int strategyId) {
        this.strategyId = strategyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Timestamp addedTime) {
        this.addedTime = addedTime;
    }

    public Timestamp getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Timestamp entryTime) {
        this.entryTime = entryTime;
    }

    public Timestamp getExitTime() {
        return exitTime;
    }

    public void setExitTime(Timestamp exitTime) {
        this.exitTime = exitTime;
    }

    public Double getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(Double profitLoss) {
        this.profitLoss = profitLoss;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="strategy_id")
    private int strategyId;

    @Column(name="type")
    private String type;

    @Column(name="type_id")
    private Integer typeId;

    @Column(name="stock")
    private String stock;

    @Column(name="size")
    private Integer size;

    @Column(name="status")
    private String status;

    @Column(name="added_time")
    private Timestamp addedTime;

    @Column(name="entry_time")
    private Timestamp entryTime;

    @Column(name="exit_time")
    private Timestamp exitTime;

    @Column(name="profit_loss")
    private Double profitLoss;

}
