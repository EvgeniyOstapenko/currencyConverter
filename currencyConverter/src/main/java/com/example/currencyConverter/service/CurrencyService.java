package com.example.currencyConverter.service;

import com.example.currencyConverter.domain.User;
import com.example.currencyConverter.repos.EnquiryRepo;
import com.example.currencyConverter.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EnquiryRepo enquiryRepo;

    public User User(Long userId){
        User user = userRepo.findById();
        if(user != null){
            return user;
        }
        user = new User();
        user.setId(userId);
        userRepo.save(user);
        return user;

    }


}
