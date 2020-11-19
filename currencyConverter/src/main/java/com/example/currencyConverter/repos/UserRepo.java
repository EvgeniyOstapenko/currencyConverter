package com.example.currencyConverter.repos;

import com.example.currencyConverter.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findById();
}

