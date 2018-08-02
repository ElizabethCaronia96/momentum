package com.momentum.rest.service;

import com.google.common.collect.Lists;
import com.momentum.rest.dao.StrategiesRepository;
import com.momentum.rest.entities.Order;
import com.momentum.rest.entities.Strategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.List;

@Service
public class StrategiesServiceImpl implements StrategiesService{

    @PersistenceContext
    private EntityManager em;


    @Autowired
    private StrategiesRepository stratRepo;

    @Override
    public List<Strategies> getStratByStrategyId(int strategyId) {
        return null;
    }

    @Override
    public List<Strategies> getStratsByType(String type) {
        return null;
    }

    @Override
    public List<Strategies> getStratByTypeId(Integer typeId) {
        return null;
    }

    @Override
    public List<Strategies> getStratByStock(String stock) {
        return null;
    }

    @Override
    public List<Strategies> getStratBySize(Integer size) {
        return null;
    }

    @Override
    public List<Strategies> getStratByStatus(String status) {
        return null;
    }

    @Override
    public List<Strategies> getStratByAddedTime(Timestamp addedTime) {
        return null;
    }

    @Override
    public List<Strategies> getStratByEntryTime(Timestamp entryTime) {
        return null;
    }

    @Override
    public List<Strategies> getStratByExitTime(Timestamp exitTime) {
        return null;
    }

    @Override
    public List<Strategies> getStratByProfitLoss(Double profitLoss) {
        return null;
    }

    @Override
    public List<Strategies> getAllActive() {

            String q = String.format("SELECT s. FROM Strategies s WHERE s.status<>\'finished\' ORDER BY s.strategy_id DESC");
            Query query = em.createQuery(q);

            return query.getResultList();


    }
}
