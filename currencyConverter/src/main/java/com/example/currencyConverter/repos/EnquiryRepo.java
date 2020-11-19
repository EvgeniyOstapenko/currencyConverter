package com.example.currencyConverter.repos;

import com.example.currencyConverter.domain.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnquiryRepo extends JpaRepository<Enquiry, Long> {
}
