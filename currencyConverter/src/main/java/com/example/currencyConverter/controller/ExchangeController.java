package com.example.currencyConverter.controller;

import com.example.currencyConverter.CurrencyConverterApplication;
import com.example.currencyConverter.model.ConverterModel;
import com.example.currencyConverter.model.ResponseExchangeModel;
import com.example.currencyConverter.service.CurrencyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    static Logger log = Logger.getLogger(CurrencyConverterApplication.class.getName());

    @Autowired
    private CurrencyService currencyService;

    @PostMapping
    public ResponseEntity<ResponseExchangeModel> convert(@ModelAttribute ConverterModel converterModel) {
        log.info("ExchangeController working..");

        ConverterModel model = currencyService.completeModel(converterModel);
        ResponseExchangeModel responseExchangeModel = currencyService.getConvertedValue(model);
        return new ResponseEntity<>(responseExchangeModel, HttpStatus.OK);
    }

}
