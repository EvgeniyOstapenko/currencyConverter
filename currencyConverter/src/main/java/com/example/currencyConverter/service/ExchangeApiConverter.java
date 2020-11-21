package com.example.currencyConverter.service;

import com.example.currencyConverter.model.ConverterModel;
import com.example.currencyConverter.model.ResponseExchangeModel;
import com.example.currencyConverter.util.Currency;

import java.math.BigDecimal;

public interface ExchangeApiConverter {

     ResponseExchangeModel getConvertedValue(Currency source, Currency target, BigDecimal amount, Long requestId);
     ConverterModel getConverterModel();
}
