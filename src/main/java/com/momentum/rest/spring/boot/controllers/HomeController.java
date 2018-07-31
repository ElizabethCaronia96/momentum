package com.momentum.rest.spring.boot.controllers;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="strateg_type", required=true, defaultValue="2mv") String strategType){
        return "greeting "+strategType;
    }

}