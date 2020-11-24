package com.example.currencyConverter.controller;

import com.example.currencyConverter.model.ConverterModel;
import com.example.currencyConverter.model.ResponseExchangeModel;
import com.example.currencyConverter.service.CurrencyService;
import com.example.currencyConverter.util.Currency;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    static Logger log = Logger.getLogger(ExchangeController.class.getName());

    @Autowired
    private CurrencyService currencyService;

    @PostMapping
    public ResponseEntity<ResponseExchangeModel> convert(BigDecimal srcAmount,
                                                         Currency targetCurrency,
                                                         Long userId) {
        log.info("ExchangeController working..");

        ConverterModel model = currencyService.getModel(srcAmount, targetCurrency, userId);
        ResponseExchangeModel response = currencyService.getConvertedValue(model);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
