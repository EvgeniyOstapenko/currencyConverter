package com.example.currencyConverter.service.API;

import com.example.currencyConverter.CurrencyConverterApplication;
import com.example.currencyConverter.model.ConversionRates;
import com.example.currencyConverter.model.ResponseExchangeModel;
import com.example.currencyConverter.util.Currency;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

@Component
public class ExchangeApiService implements ExchangeApiConverter {

    static Logger log = Logger.getLogger(CurrencyConverterApplication.class.getName());

    public static final String API_SECRET_KEY = "99a8d9eaa093228e6ef8f601850f322a";
    public static final String APILAYER_BASE_URL = "http://apilayer.net/api/live";

    @Override
    public ResponseExchangeModel getConvertedValueFromApi(Currency sourceCurrency, Currency targetCurrency,
                                                           BigDecimal amountOfMoney, Long requestId) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseExchangeModel responseExchangeModel = new ResponseExchangeModel();

        MultiValueMap<String, String> uriVariables = getUriVariables(sourceCurrency, targetCurrency);

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(APILAYER_BASE_URL).queryParams(uriVariables).build();
        ConversionRates conversionRates = restTemplate.getForObject(uriComponents.toUri(), ConversionRates.class);

        if (conversionRates.getSuccess()) {
            getConvertedValue(sourceCurrency, targetCurrency, amountOfMoney, responseExchangeModel, conversionRates);
        } else {
            getError(responseExchangeModel, conversionRates);
            log.error("Got an exception from apilayer.net api service", conversionRates.getError());
        }

        responseExchangeModel.setRequestId(requestId);
        return responseExchangeModel;
    }

    private void getError(ResponseExchangeModel responseExchangeModel, ConversionRates conversionRates) {
        responseExchangeModel.setError(conversionRates.getError());
        responseExchangeModel.setConvertedValue(BigDecimal.ZERO);
    }

    private void getConvertedValue(Currency sourceCurrency, Currency targetCurrency, BigDecimal amountOfMoney,
                                   ResponseExchangeModel responseExchangeModel, ConversionRates conversionRates) {
        String sourceTargetCurrency = sourceCurrency.getCode() + targetCurrency.getCode();
        String conversionRate = conversionRates.getQuotes().get(sourceTargetCurrency);
        BigDecimal bdConversionRate = new BigDecimal(conversionRate);
        responseExchangeModel.setConvertedValue(bdConversionRate.multiply(amountOfMoney));
    }

    private MultiValueMap<String, String> getUriVariables(Currency sourceCurrency, Currency targetCurrency) {
        MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<>();
        uriVariables.add("access_key", API_SECRET_KEY);
        uriVariables.add("currencies", targetCurrency.getCode());
        uriVariables.add("source", sourceCurrency.getCode());
        uriVariables.add("format", "1");
        return uriVariables;
    }

}

