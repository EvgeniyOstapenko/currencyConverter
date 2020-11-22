package com.example.currencyConverter.controller;


import com.example.currencyConverter.model.ConverterModel;
import com.example.currencyConverter.model.ResponseExchangeModel;
import com.example.currencyConverter.service.CurrencyService;
import com.example.currencyConverter.service.ExchangeApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ExchangeController.class)
public class ExchangeControllerTest {

    @MockBean
    private CurrencyService currencyService;

    @MockBean
    private ExchangeApiService exchangeApiService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void filterMessageTest() throws Exception {

        //GIVEN
        ConverterModel model = new ConverterModel();
        ResponseExchangeModel response = new ResponseExchangeModel();

        //WHEN
        when((currencyService).completeModel(model)).thenReturn(model);
        when((exchangeApiService).getConvertedValue(model)).thenReturn(response);

        mockMvc.perform(post("/exchange")
                .flashAttr("converterModel", model))
                .andDo(print())

        //THEN
                .andExpect(status().isOk());

        Mockito.verify(currencyService, Mockito.times(1)).completeModel(model);
        Mockito.verify(exchangeApiService, Mockito.times(1)).getConvertedValue(model);

    }

}