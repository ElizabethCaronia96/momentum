package com.momentum.rest.entities;

import javax.persistence.*;

@Entity@Table(name="strat_2ma")
public class TwoMA {
    public int getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(int strategyId) {
        this.strategyId = strategyId;
    }

    public Integer getLongAvgRange() {
        return longAvgRange;
    }

    public void setLongAvgRange(Integer longAvgRange) {
        this.longAvgRange = longAvgRange;
    }

    public Integer getShortAvgRange() {
        return shortAvgRange;
    }

    public void setShortAvgRange(Integer shortAvgRange) {
        this.shortAvgRange = shortAvgRange;
    }

    public Double getPercentToExit() {
        return percentToExit;
    }

    public void setPercentToExit(Double percentToExit) {
        this.percentToExit = percentToExit;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="strategy_id")
    private int strategyId;

    @Column(name="long_avg_range")
    private Integer longAvgRange;

    @Column(name="short_avg_range")
    private Integer shortAvgRange;

    @Column(name="percent_to_exit")
    private Double percentToExit;


}
