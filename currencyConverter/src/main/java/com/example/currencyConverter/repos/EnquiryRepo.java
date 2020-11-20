package com.example.currencyConverter.repos;

import com.example.currencyConverter.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnquiryRepo extends JpaRepository<Request, Long> {
}
