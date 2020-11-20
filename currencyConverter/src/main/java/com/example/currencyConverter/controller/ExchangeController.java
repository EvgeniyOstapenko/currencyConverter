package com.example.currencyConverter.controller;

import com.example.currencyConverter.domain.Enquiry;
import com.example.currencyConverter.domain.User;
import com.example.currencyConverter.model.ConverterModel;
import com.example.currencyConverter.model.ResponseModel;
import com.example.currencyConverter.service.CurrencyService;
import com.example.currencyConverter.service.ExchangeApiService;
import com.example.currencyConverter.util.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;


@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ExchangeApiService exchangeApiService;

        @GetMapping
        public Enquiry exchange(Model model,
                            @RequestParam Long userId,
                            @RequestParam Long value,
                            @RequestParam String sourceCurrency,
                            @RequestParam String targetCurrency) {

        User user = currencyService.getUser(userId);
        Enquiry enquiry = currencyService.createEnquiry(value, userId, sourceCurrency, targetCurrency);



        return enquiry;
    }

    @RequestMapping(value = "/convert", method = RequestMethod.POST)
    public ResponseEntity<ResponseModel> doConvert(@ModelAttribute ConverterModel converterModel,
                                                   Model model,
                                                   HttpServletRequest request) {

        Currency sourceCurrency = converterModel.getSrcCurrency();
        Currency targetCurrency = converterModel.getTgtCurrency();
        BigDecimal amountOfMoney = converterModel.getSrcAmount();
        Long userId = converterModel.getUserId();

        User user = currencyService.getUser(userId);
        Enquiry enquiry = currencyService.createEnquiry(amountOfMoney, userId, sourceCurrency, targetCurrency);

        HttpStatus status = HttpStatus.OK;
        model.addAttribute("converterModel", converterModel);
        ResponseModel responseModel = exchangeApiService.getConvertedValue(sourceCurrency, targetCurrency, amountOfMoney);

        return new ResponseEntity<ResponseModel>(responseModel, status);
    }

}
