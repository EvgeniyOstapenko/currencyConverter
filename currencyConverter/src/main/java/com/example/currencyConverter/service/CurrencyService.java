package com.example.currencyConverter.service;

import com.example.currencyConverter.domain.Request;
import com.example.currencyConverter.domain.User;
import com.example.currencyConverter.model.ConverterModel;
import com.example.currencyConverter.model.ResponseStatisticCurrencyModel;
import com.example.currencyConverter.model.ResponseStatisticModel;
import com.example.currencyConverter.model.ResponseStatisticUserModel;
import com.example.currencyConverter.repos.RequestRepo;
import com.example.currencyConverter.repos.UserRepo;
import com.example.currencyConverter.util.Currency;
import com.example.currencyConverter.util.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.currencyConverter.util.Parameter.BIG;
import static com.example.currencyConverter.util.Parameter.HUGE;

@Service
public class CurrencyService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RequestRepo requestRepo;

    public void saveUserIfNotExist(Long userId) {
        Optional<User> userFromDb = userRepo.findById(userId);
        if (userFromDb.isEmpty()) {
            User user = new User();
            user.setId(userId);
            userRepo.save(user);
        }
    }

    public ConverterModel saveRequest(ConverterModel converterModel) {
        String sourceCurrency = Currency.USD.name();
        String targetCurrency = converterModel.getTargetCurrency().toString();
        BigDecimal amountOfMoney = converterModel.getUSD();
        Long userId = converterModel.getUserId();

        saveUserIfNotExist(converterModel.getUserId());
        Request request = saveRequest(sourceCurrency, targetCurrency, amountOfMoney, userId);

        converterModel.setRequestId(request.getId());
        return converterModel;
    }

    public Request saveRequest(String sourceCurrency, String targetCurrency, BigDecimal amountOfMoney, Long userId) {
        Request request = new Request();
        request.setMoney(amountOfMoney);
        request.setUserId(userId);
        request.setSourceCurrency(sourceCurrency);
        request.setTargetCurrency(targetCurrency);

        return requestRepo.save(request);
    }

    public List<? extends ResponseStatisticModel> getRequestStats(Parameter param) {

        if (param.equals(BIG)) {
            return getUserListWithSpecialAmountOfMoney(10_000);

        }
        if (param.equals(HUGE)) {
            return getUserListWithSpecialAmountOfMoney(100_000);
        }

        return getTargetCurrencySorterByPopularity();
    }

    private List<ResponseStatisticCurrencyModel> getTargetCurrencySorterByPopularity() {
        List<Currency> popular = requestRepo.findPopularCurrencyInRequests();
        return popular.stream().map(currency -> getResponseStatisticCurrencyModel(currency.getCode(),
                currency.getDescription())).collect(Collectors.toList());
    }

    private List<ResponseStatisticUserModel> getUserListWithSpecialAmountOfMoney(Integer param) {
        List<Long> users = requestRepo.findUsersWithSpecialAmountOfMoneyInRequests(param);
        return users.stream().map(this::getResponseStatisticUserModel).collect(Collectors.toList());
    }

    private ResponseStatisticUserModel getResponseStatisticUserModel(long userId){
        ResponseStatisticUserModel model = new ResponseStatisticUserModel();
        model.setUserId(userId);
        return model;
    }

    private ResponseStatisticCurrencyModel getResponseStatisticCurrencyModel(String code, String description){
        ResponseStatisticCurrencyModel model = new ResponseStatisticCurrencyModel();
        model.setCurrency(String.format("%s [ %s ]", code, description));
        return model;
    }

}
