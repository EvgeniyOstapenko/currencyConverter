package com.example.currencyConverter.integration;


import com.example.currencyConverter.CurrencyConverterApplication;
import com.example.currencyConverter.controller.StatisticController;
import com.example.currencyConverter.repos.RequestRepo;
import com.example.currencyConverter.repos.UserRepo;
import com.example.currencyConverter.service.CurrencyService;
import com.example.currencyConverter.util.Parameter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-request-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-request-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class StatisticControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getBigRequestStatsWithBigParameter() throws Exception {

        //WHEN
        this.mockMvc.perform(get("/stats").param("param", String.valueOf(Parameter.BIG)))
                .andDo(print())
        //THEN
                .andExpect(MockMvcResultMatchers.content().string(containsString("[{\"userId\":4},{\"userId\":3},{\"userId\":2}]")));

    }

    @Test
    void getBigRequestStatsWithHugeParameter() throws Exception {

        //WHEN
        this.mockMvc.perform(get("/stats").param("param", String.valueOf(Parameter.HUGE)))
                .andDo(print())
        //THEN
                .andExpect(MockMvcResultMatchers.content().string(containsString("[{\"userId\":4}]")));

    }

    @Test
    void getBigRequestStatsWithPopularParameter() throws Exception {

        //WHEN
        this.mockMvc.perform(get("/stats").param("param", String.valueOf(Parameter.POPULAR)))
                .andDo(print())
                //THEN
                .andExpect(MockMvcResultMatchers.content().string(containsString("[{\"currency\":\"BHD [ Bahraini Dinar ]\"},{\"currency\":\"AED [ United Arab Emirates Dirham ]")));

    }

}
