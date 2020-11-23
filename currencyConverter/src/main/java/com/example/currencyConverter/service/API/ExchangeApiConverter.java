package com.example.currencyConverter.service.API;

import com.example.currencyConverter.model.ResponseExchangeModel;
import com.example.currencyConverter.util.Currency;

import java.math.BigDecimal;

public interface ExchangeApiConverter {

     ResponseExchangeModel getConvertedValueFromApi(Currency source, Currency target, BigDecimal amount, Long requestId);

}
