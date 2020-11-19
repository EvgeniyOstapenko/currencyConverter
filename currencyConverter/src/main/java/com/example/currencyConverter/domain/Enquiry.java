package com.example.currencyConverter.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Enquiry {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Long value;


    public void setValue(Long value) {
        this.value = value;
    }
}
