package com.momentum.rest.entities;


import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity@Table(name="orders")
@NamedQueries(
        {
                @NamedQuery(name = "Order.getAll",
                        query = "select order_id from Order as o where o.num_stocks = 1000",
                hints = {@QueryHint(name="org.hibernate.cacheable", value="true")})
        }
)
public class Order  implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "order_id")
    private int order_id;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    @Column(name = "strategy_type")
    private String strategy_type;



    public int getNum_stocks() {
        return num_stocks;
    }

    public void setNum_stocks(int num_stocks) {
        this.num_stocks = num_stocks;
    }



    @Column(name = "num_stocks")
    private int num_stocks;


    public Order() {

    }

    public String getStrategy_type() {
        return strategy_type;
    }

    public void setStrategy_type(String strategy_type) {
        this.strategy_type = strategy_type;
    }
}
