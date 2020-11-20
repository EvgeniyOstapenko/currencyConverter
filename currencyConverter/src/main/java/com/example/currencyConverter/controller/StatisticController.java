package com.example.currencyConverter.controller;

import com.example.currencyConverter.domain.User;
import com.example.currencyConverter.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/stats")
public class StatisticController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping()
    public List<User> getBigRequestStats(@RequestParam String param) {
        return currencyService.getRequestStats(param);
    }

}
