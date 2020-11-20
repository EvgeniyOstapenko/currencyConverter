package com.example.currencyConverter.controller;

import com.example.currencyConverter.domain.Request;
import com.example.currencyConverter.model.ConverterModel;
import com.example.currencyConverter.model.ResponseModel;
import com.example.currencyConverter.service.CurrencyService;
import com.example.currencyConverter.service.ExchangeApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ExchangeApiService exchangeApiService;

    @PostMapping
    public ResponseEntity<ResponseModel> convert(@ModelAttribute ConverterModel converterModel) {

        ConverterModel model = currencyService.saveRequest(converterModel);
        ResponseModel responseModel = exchangeApiService.getConvertedValue(model);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

}
