package com.momentum.rest.springboot.repos;

import com.momentum.rest.entities.Price;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;

public interface PriceRepository extends CrudRepository<Price, Integer> {

    Iterable<Price> findPricesByStock(@Param("stock") String stock);

    Iterable<Price> findPricesByPrice(@Param("price") double price);

    Iterable<Price> findPricesByDatetime(@Param("datetime") Timestamp datetime);

}