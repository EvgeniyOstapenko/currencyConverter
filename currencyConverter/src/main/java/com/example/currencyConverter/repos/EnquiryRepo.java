package com.example.currencyConverter.repos;

import com.example.currencyConverter.domain.Enquiry;
import org.springframework.data.repository.CrudRepository;

public interface EnquiryRepo extends CrudRepository<Enquiry, Long> {
}
