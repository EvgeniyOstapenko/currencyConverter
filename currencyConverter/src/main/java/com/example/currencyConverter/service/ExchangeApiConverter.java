package com.example.currencyConverter.service;

import com.example.currencyConverter.model.ConverterModel;
import com.example.currencyConverter.model.ResponseExchangeModel;

public interface ExchangeApiConverter {

     ResponseExchangeModel getConvertedValue(ConverterModel converterModel);
}
