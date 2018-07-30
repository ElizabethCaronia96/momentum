package com.momentum.rest.entities;


import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;

@Entity@Table(name="orders")
@NamedQueries(
        {
                @NamedQuery(name="orders.getAll",
                query = "select * from Order as o where o.status = 'completed'",
                hints = {@QueryHint(name="org.hibernate.cacheable", value="true")})
        }
)
public class Order  implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int order_id;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    @Column(name="strategy") private String strategy;
    @Column(name="buy") private;
    @Column(name="price")price private;
    @Column(name="num_stocks") private;
    @Column(name="datetime") private;
    @Column(name="status") private ;
}
