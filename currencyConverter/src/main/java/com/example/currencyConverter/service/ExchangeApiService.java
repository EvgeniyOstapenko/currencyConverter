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
public class ExchangeApiService implements ExchangeApiConverter{
    @Autowired
    private Environment env;


    @Override
    public ConverterModel getConverterModel() {
        ConverterModel cm = new ConverterModel();
        return cm;
    }

    @Override
    public ResponseModel getConvertedValue(Currency source, Currency target, BigDecimal amount) {

        ResponseModel responseModel = new ResponseModel();

        String rateKey = source.getCode()+target.getCode();


        MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
        uriVariables.add("access_key", env.getProperty("99a8d9eaa093228e6ef8f601850f322a"));
        uriVariables.add("currencies", target.getCode());
        uriVariables.add("source", source.getCode());
        uriVariables.add("format", "1");

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(env.getProperty("http://apilayer.net/api/live"))
                .queryParams(uriVariables).build();

        RestTemplate restTemplate = new RestTemplate();
        ConversionRates rates = restTemplate.getForObject(uriComponents.toUri(), ConversionRates.class);

        if(rates.getSuccess()) {
            String cRate = rates.getQuotes().get(rateKey);
            BigDecimal bdr = new BigDecimal(cRate);
            responseModel.setConvertedValue(bdr.multiply(amount));
        } else {
            responseModel.setError(rates.getError());
            responseModel.setConvertedValue(BigDecimal.ZERO);
        }

        return responseModel;
    }

}

