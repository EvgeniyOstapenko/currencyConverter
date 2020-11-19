package com.example.currencyConverter.service;

import com.example.currencyConverter.domain.Enquiry;
import com.example.currencyConverter.domain.User;
import com.example.currencyConverter.repos.EnquiryRepo;
import com.example.currencyConverter.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EnquiryRepo enquiryRepo;

    public User getUser(Long userId){
        Optional<User> userFromDb = userRepo.findById(userId);
        if(userFromDb.isPresent()){
            return userFromDb.get();
        }
        User user = new User();
        user.setId(userId);
        user.getInqueryId()
        userRepo.save(user);
        return user;

    }

    public Enquiry getEnquiry(User user, Long amount){
        Enquiry enquiry = new Enquiry();
        enquiry.setUser(user);
        enquiry.setAmount(amount);

        return enquiryRepo.save(new Enquiry());
    }


}
