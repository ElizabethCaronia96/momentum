package com.momentum.rest.dao;

import com.momentum.rest.entities.Price;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface PriceRepository extends CrudRepository<Price, Integer> {

    Iterable<Price> findPricesByStock(@Param("stock") String stock);
    Iterable<Price> findPricesByPrice(@Param("price") double price);
    Iterable<Price> findPricesByDatetime(@Param("datetime") Timestamp datetime);
    List<Double> findLastNPricesByStock(@Param("searchStock") String searchTerm);

}