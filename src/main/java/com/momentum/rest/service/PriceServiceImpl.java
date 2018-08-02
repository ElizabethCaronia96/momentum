package com.momentum.rest.service;

import com.google.common.collect.Lists;
import com.momentum.rest.dao.PriceRepository;
import com.momentum.rest.entities.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService{

    @Autowired
    private PriceRepository dao;

    @PersistenceContext
    private EntityManager em;


    @Override
    public Iterable<Price> getPrices() {
        return dao.findAll();
    }

    @Override
    public Price addNewPrice(Price price) {
        return dao.save(price);
    }

    public List<Double> getLastNPricesOfStock(String stock, int n) {

        String q = String.format("SELECT p.price FROM Price p WHERE p.stock=\'%s\' ORDER BY p.priceID DESC", stock);
        Query query = em.createQuery(q).setMaxResults(n);

        List<Double> prices = query.getResultList();
        List<Double> pricesFinal = Lists.reverse(prices);

        return pricesFinal;
    }
}
