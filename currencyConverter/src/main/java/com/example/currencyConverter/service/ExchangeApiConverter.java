package com.example.currencyConverter.service;

import com.example.currencyConverter.model.ConverterModel;
import com.example.currencyConverter.model.ResponseModel;
import com.example.currencyConverter.util.Currency;

import java.math.BigDecimal;

public interface ExchangeApiConverter {

     ResponseModel getConvertedValue(Currency source, Currency target, BigDecimal amount);
     ConverterModel getConverterModel();
}
