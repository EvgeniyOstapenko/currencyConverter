package com.example.currencyConverter.service;

import com.example.currencyConverter.domain.Request;
import com.example.currencyConverter.domain.User;
import com.example.currencyConverter.model.ConverterModel;
import com.example.currencyConverter.repos.EnquiryRepo;
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
    private EnquiryRepo enquiryRepo;

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

        return enquiryRepo.save(request);
    }

    public List getRequestStats(Parameter param) {

        if (param.equals(BIG)) {
            BigDecimal bigMoney = BigDecimal.valueOf(10_000);
            return getUserListWithSpecialAmountOfMoney(bigMoney);

        }
        if (param.equals(HUGE)) {
            BigDecimal hugeMoney = BigDecimal.valueOf(100_000);
            return getUserListWithSpecialAmountOfMoney(hugeMoney);
        }

        return null;

//        return getTargetCurrencySorterByPopularity();
    }

    private List<User> getTargetCurrencySorterByPopularity() {
        List<Request> enquiries = enquiryRepo.findAll();
        return enquiries.stream()
                .map(Request::getUserId)
                .map(id -> userRepo.findById(id).get())
                .collect(Collectors.toList());
    }

    private List<User> getUserListWithSpecialAmountOfMoney(BigDecimal amount) {
        List<Request> enquiries = enquiryRepo.findAll();
        return enquiries.stream()
                .filter(enquiry -> enquiry.getMoney().compareTo(amount) >= 0)
                .map(Request::getUserId)
                .map(id -> userRepo.findById(id).get())
                .collect(Collectors.toList());
    }

}
