package com.example.currencyConverter.controller;


import com.example.currencyConverter.model.ConverterModel;
import com.example.currencyConverter.model.ResponseExchangeModel;
import com.example.currencyConverter.service.CurrencyService;
import com.example.currencyConverter.util.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ExchangeController.class)
public class ExchangeControllerTest {

    @MockBean
    private CurrencyService currencyService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void filterMessageTest() throws Exception {

        //GIVEN
        ConverterModel model = new ConverterModel();
        ResponseExchangeModel response = new ResponseExchangeModel();
        BigDecimal srcAmount = new BigDecimal(1000);

        //WHEN
        when((currencyService).getModel(srcAmount, Currency.AED, 1L)).thenReturn(model);
        when((currencyService).getConvertedValue(model)).thenReturn(response);

        mockMvc.perform(post("/exchange")
                .param("srcAmount", "1000")
                .param("targetCurrency", "AED")
                .param("userId", "1"))
                .andDo(print())

        //THEN
                .andExpect(status().isOk());

        Mockito.verify(currencyService, Mockito.times(1)).getModel(srcAmount, Currency.AED, 1L);

    }

}