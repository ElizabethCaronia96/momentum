package com.momentum.rest.service;

import com.momentum.rest.entities.Price;

import java.util.List;

public interface PriceService {

    Iterable<Price> getPrices();
    Price addNewPrice(Price price);
    List getLastNPricesOfStock(String stock, int n);

}
