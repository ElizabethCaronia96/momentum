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
import java.sql.SQLOutput;
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
        return stratRepo.findStrategiesByStrategyId(strategyId);
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

    @Override
    public void updateStatus(int strategyId, String status) {
        System.out.println("sid: "+ strategyId);
        List<Strategies> s = getStratByStrategyId(strategyId);
        System.out.println("s "+ s);
        s.get(0).setStatus(status);
        if (status == "in entry"){
            s.get(0).setEntryTime(new Timestamp(System.currentTimeMillis()));
        }
        else
            s.get(0).setExitTime(new Timestamp(System.currentTimeMillis()));

        stratRepo.save(s.get(0));
    }


    @Override
    public void updateStatus(int strategyId, String status, Double profit) {
        List<Strategies> s = getStratByStrategyId(strategyId);
        s.get(0).setStatus(status);
        s.get(0).setProfitLoss(profit);
        if (status == "in entry"){
            s.get(0).setEntryTime(new Timestamp(System.currentTimeMillis()));
        }
        else
            s.get(0).setExitTime(new Timestamp(System.currentTimeMillis()));

        stratRepo.save(s.get(0));
    }



    public Map<Strategies, Object> getAllActiveHelper(List<Strategies> allActive) {
        Map map = new HashMap<Strategies, Object>();
        for (Strategies a : allActive) {
            if (a.getType().equals("2ma")) {
                map.put(a, twoMARepo.findTwoMAByStrategyId(a.getTypeId()));
            }
            else {
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
