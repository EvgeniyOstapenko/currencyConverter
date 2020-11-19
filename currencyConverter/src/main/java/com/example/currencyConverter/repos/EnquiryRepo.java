package com.example.currencyConverter.repos;

import com.example.currencyConverter.domain.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnquiryRepo extends JpaRepository<Enquiry, Long> {
}
