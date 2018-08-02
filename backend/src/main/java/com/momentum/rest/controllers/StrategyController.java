package com.momentum.rest.controllers;

import com.momentum.rest.entities.Order;
import com.momentum.rest.entities.Strategies;
import com.momentum.rest.service.OrderService;
import com.momentum.rest.service.StrategiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(path="strategies")
public class StrategyController {

    @Autowired
    private StrategiesService stratServ;


    @GetMapping(path="/open-strat")
    @ResponseBody
    public Set<Strategies> getUnfinishedStrategies() {
        return stratServ.getAllActive().keySet();
    }

    @GetMapping(path="/closed-strat")
    @ResponseBody
    public Set<Strategies> getFinishedStrategies(){
        return stratServ.getAllDone().keySet();
    }
}
