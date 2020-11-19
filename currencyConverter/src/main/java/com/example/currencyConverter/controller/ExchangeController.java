package com.example.currencyConverter.controller;

import com.example.currencyConverter.domain.User;
import com.example.currencyConverter.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController("/exchange")
public class ExchangeController {

    @Autowired
    private CurrencyService currencyService;

        @GetMapping
        public String main(Model model,
                           @RequestParam Long userId,
                           Integer sum,
                           String sourceCurrency,
                           String targetCurrency) {

        User user = new User();





            return "main";
        }

    }
