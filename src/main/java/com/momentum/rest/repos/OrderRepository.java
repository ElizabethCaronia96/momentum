package com.momentum.rest.repos;

import com.momentum.jms.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository {

    public List<Order> findOrderById(@Param("artist") String artist);

}
