package com.example.currencyConverter.service;

import com.example.currencyConverter.domain.Enquiry;
import com.example.currencyConverter.domain.User;
import com.example.currencyConverter.repos.EnquiryRepo;
import com.example.currencyConverter.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrencyService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EnquiryRepo enquiryRepo;

    public User getUser(Long userId) {
        Optional<User> userFromDb = userRepo.findById(userId);
        if (userFromDb.isPresent()) {
            return userFromDb.get();
        }
        User user = new User();
        user.setId(userId);
        userRepo.save(user);
        return user;

    }

    public Enquiry createEnquiry(Long value, Long userId, String sourceCurrency, String targetCurrency) {
        Enquiry enquiry = new Enquiry();
        enquiry.setValue(value);
        enquiry.setUserId(userId);
        enquiry.setSourceCurrency(sourceCurrency);
        enquiry.setTargetCurrency(targetCurrency);

        return enquiryRepo.save(enquiry);
    }

    public List<User> getRequestStats(String param) {

        if (param.equals("BIG")) {
            return getUserListWithSpecialAmountOfMoney(10_000L);

        }
        if (param.equals("HUGE")) {
            return getUserListWithSpecialAmountOfMoney(100_000L);
        }

        return null;
    }

    private List<User> getUserListWithSpecialAmountOfMoney(Long amount) {
        List<Enquiry> users = enquiryRepo.findAll();
        return users.stream()
                .filter(enquiry -> enquiry.getValue() >= amount)
                .map(Enquiry::getUserId)
                .map(id -> userRepo.findById(id).get())
                .collect(Collectors.toList());
    }


}
