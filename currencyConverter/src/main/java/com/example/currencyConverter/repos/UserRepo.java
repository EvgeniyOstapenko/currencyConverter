package com.example.currencyConverter.repos;

import com.example.currencyConverter.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

}

