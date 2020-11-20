package com.example.currencyConverter.controller;

import com.example.currencyConverter.domain.Enquiry;
import com.example.currencyConverter.model.ConverterModel;
import com.example.currencyConverter.model.ResponseModel;
import com.example.currencyConverter.service.CurrencyService;
import com.example.currencyConverter.service.ExchangeApiService;
import com.example.currencyConverter.util.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ExchangeApiService exchangeApiService;

    @PostMapping
    public ResponseEntity<ResponseModel> doConvert(@ModelAttribute ConverterModel converterModel) {
        Currency sourceCurrency = Currency.USD;
        Currency targetCurrency = converterModel.getTargetCurrency();
        BigDecimal amountOfMoney = converterModel.getUSD();
        Long userId = converterModel.getUserId();

        currencyService.saveUserIfNotExist(userId);
        Enquiry enquiry = currencyService.saveEnquiry(sourceCurrency.name(), targetCurrency.toString(), amountOfMoney, userId);

        ResponseModel responseModel = exchangeApiService.getConvertedValue(sourceCurrency, targetCurrency, amountOfMoney);
        responseModel.setRequestId(enquiry.getId());

        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

}
