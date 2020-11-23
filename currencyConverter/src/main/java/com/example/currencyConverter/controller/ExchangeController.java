package com.example.currencyConverter.controller;

import com.example.currencyConverter.model.ConverterModel;
import com.example.currencyConverter.model.ResponseExchangeModel;
import com.example.currencyConverter.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    @Autowired
    private CurrencyService currencyService;

    @PostMapping
    public ResponseEntity<ResponseExchangeModel> convert(@ModelAttribute ConverterModel converterModel) {

        ConverterModel model = currencyService.completeModel(converterModel);
        ResponseExchangeModel responseExchangeModel = currencyService.getConvertedValue(model);
        return new ResponseEntity<>(responseExchangeModel, HttpStatus.OK);
    }

}
