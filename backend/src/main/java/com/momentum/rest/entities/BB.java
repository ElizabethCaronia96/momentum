package com.momentum.rest.entities;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity@Table(name="strat_bb")
public class BB {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="strategy_id")
    private int  strategyId;

    @Column(name="moving_avg_range")
    private Integer movingAvgRange;

    public int getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(int strategyId) {
        this.strategyId = strategyId;
    }

    public Integer getMovingAvgRange() {
        return movingAvgRange;
    }

    public void setMovingAvgRange(Integer movingAvgRange) {
        this.movingAvgRange = movingAvgRange;
    }

    public Double getStdDevMultiple() {
        return stdDevMultiple;
    }

    public void setStdDevMultiple(Double stdDevMultiple) {
        this.stdDevMultiple = stdDevMultiple;
    }

    @Column(name="std_dev_multiple")
    private Double stdDevMultiple;

    @Column(name="percent_to_exit")
    private Double percentToExit;

    public Double getPercentToExit() {
        return percentToExit;
    }

    public void setPercentToExit(Double percentToExit) {
        this.percentToExit = percentToExit;
    }


}
