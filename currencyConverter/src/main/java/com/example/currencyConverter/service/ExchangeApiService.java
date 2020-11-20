package com.example.currencyConverter.service;

import com.example.currencyConverter.model.ConversionRates;
import com.example.currencyConverter.model.ConverterModel;
import com.example.currencyConverter.model.ResponseModel;
import com.example.currencyConverter.util.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

@Service
public class ExchangeApiService implements ExchangeApiConverter {

    public static final String API_SECRET_KEY = "99a8d9eaa093228e6ef8f601850f322a";
    public static final String APILAYER_BASE_URL = "http://apilayer.net/api/live";

    @Autowired
    private Environment environment;


    @Override
    public ConverterModel getConverterModel() {
        return new ConverterModel();
    }

    public ResponseModel getConvertedValue(ConverterModel converterModel){
        Currency sourceCurrency = Currency.USD;
        Currency targetCurrency = converterModel.getTargetCurrency();
        BigDecimal amountOfMoney = converterModel.getUSD();
        Long requestId = converterModel.getRequestId();

        return getConvertedValue(sourceCurrency, targetCurrency, amountOfMoney, requestId);
    }

    @Override
    public ResponseModel getConvertedValue(Currency sourceCurrency, Currency targetCurrency,
                                           BigDecimal amountOfMoney, Long requestId) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseModel responseModel = new ResponseModel();

        MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<>();
        uriVariables.add("access_key", API_SECRET_KEY);
        uriVariables.add("currencies", targetCurrency.getCode());
        uriVariables.add("source", sourceCurrency.getCode());
        uriVariables.add("format", "1");

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(APILAYER_BASE_URL).queryParams(uriVariables).build();
        ConversionRates conversionRates = restTemplate.getForObject(uriComponents.toUri(), ConversionRates.class);

        if (conversionRates.getSuccess()) {
            String sourceTargetCurrency = sourceCurrency.getCode() + targetCurrency.getCode();
            String conversionRate = conversionRates.getQuotes().get(sourceTargetCurrency);
            BigDecimal bdConversionRate = new BigDecimal(conversionRate);
            responseModel.setConvertedValue(bdConversionRate.multiply(amountOfMoney));
        } else {
            responseModel.setError(conversionRates.getError());
            responseModel.setConvertedValue(BigDecimal.ZERO);
        }

        responseModel.setRequestId(requestId);
        return responseModel;
    }

}

