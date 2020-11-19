package com.example.currencyConverter.controller;

import com.example.currencyConverter.domain.Enquiry;
import com.example.currencyConverter.domain.User;
import com.example.currencyConverter.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    @Autowired
    private CurrencyService currencyService;

        @GetMapping
        public Enquiry main(Model model,
                            @RequestParam Long userId,
                            Long sum,
                            String sourceCurrency,
                            String targetCurrency) {

        User user = currencyService.getUser(userId);
        Enquiry enquiry = currencyService.getEnquiry(user, sum);




        return enquiry;
        }

    }
