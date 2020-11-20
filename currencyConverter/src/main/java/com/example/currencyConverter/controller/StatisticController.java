package com.example.currencyConverter.controller;

import com.example.currencyConverter.service.CurrencyService;
import com.example.currencyConverter.util.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
@RequestMapping("/stats")
public class StatisticController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping()
    public Collection<Object> getBigRequestStats(@RequestParam Parameter param) {
        return currencyService.getRequestStats(param);
    }

}
