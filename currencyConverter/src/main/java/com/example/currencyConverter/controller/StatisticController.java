package com.example.currencyConverter.controller;

import com.example.currencyConverter.model.ResponseStatisticModel;
import com.example.currencyConverter.service.CurrencyService;
import com.example.currencyConverter.util.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<? extends ResponseStatisticModel>> getBigRequestStats(@RequestParam Parameter param) {
        List<? extends ResponseStatisticModel> response = currencyService.getRequestStats(param);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
