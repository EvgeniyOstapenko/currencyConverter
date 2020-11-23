package com.example.currencyConverter.controller;

import com.example.currencyConverter.CurrencyConverterApplication;
import com.example.currencyConverter.service.CurrencyService;
import com.example.currencyConverter.util.Parameter;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(StatisticController.class)
class StatisticControllerTest {

    @MockBean
    private CurrencyService currencyService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getBigRequestStatsWithPopularParameter() throws Exception {

        //WHEN
        mockMvc.perform(get("/stats")
                .param("param", String.valueOf(Parameter.POPULAR)))
                .andDo(print())

        //THEN
                .andExpect(status().isOk());

        Mockito.verify(currencyService, Mockito.times(1)).getRequestStats(Parameter.POPULAR);
    }

    @Test
    void getBigRequestStatsWithBigParameter() throws Exception {

        //WHEN
        mockMvc.perform(get("/stats")
                .param("param", String.valueOf(Parameter.BIG)))
                .andDo(print())

                //THEN
                .andExpect(status().isOk());

        Mockito.verify(currencyService, Mockito.times(1)).getRequestStats(Parameter.BIG);
    }

    @Test
    void getBigRequestStatsWithHugeParameter() throws Exception {

        //WHEN
        mockMvc.perform(get("/stats")
                .param("param", String.valueOf(Parameter.HUGE)))
                .andDo(print())

                //THEN
                .andExpect(status().isOk());

        Mockito.verify(currencyService, Mockito.times(1)).getRequestStats(Parameter.HUGE);
    }
}