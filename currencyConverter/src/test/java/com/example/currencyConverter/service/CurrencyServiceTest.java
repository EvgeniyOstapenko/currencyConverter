package com.example.currencyConverter.service;

import com.example.currencyConverter.domain.Request;
import com.example.currencyConverter.model.ConverterModel;
import com.example.currencyConverter.repos.RequestRepo;
import com.example.currencyConverter.repos.UserRepo;
import com.example.currencyConverter.util.Currency;
import com.example.currencyConverter.util.Parameter;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

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

    @Autowired
    private CurrencyService currencyService;

    @Test
    void completeModel() {

        //GIVEN
        ConverterModel modelBefore = new ConverterModel();
        modelBefore.setUserId(1l);
        modelBefore.setSrcAmount(new BigDecimal(1000));
        modelBefore.setTargetCurrency(Currency.AED);

        ConverterModel modelAfter = new ConverterModel();
        modelAfter.setRequestId(1L);

        Request request = new Request();
        request.setId(1L);

        //WHEN
        when((requestRepo).save(any())).thenReturn(request);

        //THEN
        ConverterModel completedModel = currencyService.completeModel(modelBefore);

        assertThat(completedModel.getRequestId())
                .isNotNull()
                .isEqualTo(modelAfter.getRequestId());

    }

    @Test
    void getRequestStatsWithBigParameter() {

        //GIVEN
        final int BIG_NUMBER = 10_000;
        //WHEN
        currencyService.getRequestStats(Parameter.BIG);
        //THEN
        Mockito.verify(requestRepo, Mockito.times(1)).findUsersWithSpecialAmountOfMoneyInRequests(BIG_NUMBER);

    }

    @Test
    void getRequestStatsWithHugeParameter() {

        //GIVEN
        final int HUGE_NUMBER = 100_000;
        //WHEN
        currencyService.getRequestStats(Parameter.HUGE);
        //THEN
        Mockito.verify(requestRepo, Mockito.times(1)).findUsersWithSpecialAmountOfMoneyInRequests(HUGE_NUMBER);

    }

    @Test
    void getRequestStatsWithPopularParameter() {

        //WHEN
        currencyService.getRequestStats(Parameter.POPULAR);
        //THEN
        Mockito.verify(requestRepo, Mockito.times(1)).findPopularCurrencyInRequests();

    }
}