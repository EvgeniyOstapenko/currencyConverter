package com.example.currencyConverter.service;

import com.example.currencyConverter.domain.Request;
import com.example.currencyConverter.model.ConverterModel;
import com.example.currencyConverter.model.ResponseStatisticModel;
import com.example.currencyConverter.repos.RequestRepo;
import com.example.currencyConverter.repos.UserRepo;
import com.example.currencyConverter.util.Currency;
import com.example.currencyConverter.util.Parameter;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
class CurrencyServiceTest {

    @MockBean
    private UserRepo userRepo;

    @MockBean
    private RequestRepo requestRepo;

    @InjectMocks
    private CurrencyService currencyService;

    @Test
    void completeModel() {

        //GIVEN
        ConverterModel modelBefore = new ConverterModel();
        ConverterModel modelAfter = new ConverterModel();
        modelAfter.setRequestId(1L);

        Request request = new Request();
        request.setId(1L);

        //WHEN
        when((requestRepo).save(any())).thenReturn(request);

        //THEN
        ConverterModel completedModel = currencyService.completeModel(modelBefore);

        assertThat(completedModel)
                .isNotNull()
                .isEqualTo(modelAfter);

    }

    @Test
    void getRequestStats() {

    }
}