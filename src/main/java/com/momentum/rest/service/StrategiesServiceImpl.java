package com.momentum.rest.service;

import com.google.common.collect.Lists;
import com.momentum.rest.dao.BBRepository;
import com.momentum.rest.dao.StrategiesRepository;
import com.momentum.rest.dao.TwoMARepository;
import com.momentum.rest.entities.Order;
import com.momentum.rest.entities.Strategies;
import com.momentum.rest.entities.TwoMA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.*;

@Service
public class StrategiesServiceImpl implements StrategiesService{

    @PersistenceContext
    private EntityManager em;


    @Autowired
    private StrategiesRepository stratRepo;

    @Autowired
    private TwoMARepository twoMARepo;

    @Autowired
    private BBRepository bbRepo;

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
/*

    public List<Integer, String, String, Integer, Integer, Double> getAllActive2MA() {

        String q = String.format("select s.strategyId, s.type, s.stock, tMA.longAvgRange, tMA.shortAvgRange, tMA.percentToExit from Strategies s left outer join TwoMA tMA on s.typeId = tMa.strategyId where  s.type = \'2ma\' and s.status<>\'finished\' order by s.addedTime asc ");
            Query query = em.createQuery(q);
        List<Integer, String, String, Integer, Integer, Double> activeBB = query.getResultList();
            return query.getResultList();


    }

    public List<Integer, String, String, Integer, Integer, Double> getAllActiveBB() {
        Collection<Integer, String, String, Integer, Integer, Double> activeBB = new Collection<Integer>();
        String q = String.format("select s.strategyId, s.type, s.stock, bb.movingAvgRange, bb.stdDevMultiple from Strategies s left outer join BB bb on s.typeId = bb.strategyId where  s.type = \'bb\' and s.status<>\'finished\' order by s.addedTime asc");
        Query query = em.createQuery(q);
        activeBB.addAll(query.getResultList());
        return activeBB;
    } */

    @Override
    public Map<Strategies, Object> getAllActive() {
        String[] possibleStatii = {"pending", "in entry", "in close"};
        ArrayList<Strategies> allActive = new ArrayList<>();
        for (String s : possibleStatii) {
            allActive.addAll(stratRepo.findStrategiesByStatus(s));

        }
        return getAllActiveHelper(allActive);

    }
    @Override
    public Map<Strategies, Object> getAllDone() {

        ArrayList<Strategies> allActive = new ArrayList<>();

        allActive.addAll(stratRepo.findStrategiesByStatus("finished"));


        return getAllActiveHelper(allActive);

    }

    public Map<Strategies, Object> getAllActiveHelper(List<Strategies> allActive) {
        Map map = new HashMap<Strategies, Object>();
        for (Strategies a : allActive) {
            if (a.getType().equals("2ma")) {
             //   System.out.println("2ma");
                map.put(a, twoMARepo.findTwoMAByStrategyId(a.getTypeId()));
            }
            else {
              //  System.out.println("bb");
                System.out.println(bbRepo.findBBByStrategyId(a.getTypeId()));

                map.put(a, bbRepo.findBBByStrategyId(a.getTypeId()));
            }

        }
        return map;
    }

    @Override
    public List<Strategies> getAllStrats(){
        return  stratRepo.findAll();
    }
}
