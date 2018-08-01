package com.momentum.rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @RequestMapping("/greeting")
    @ResponseBody
    public String greeting(@RequestParam(name="strategy_type", required=true, defaultValue="2mv") String strategyType){
        return "greeting "+strategyType;
    }

}