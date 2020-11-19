package com.example.currencyConverter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StatisticController {

    @GetMapping("/stats")
    public String main(@RequestParam Long userId,
                       Integer sum, String sourceCurrency,
                       String targetCurrency) {




        return "main";
    }
}
