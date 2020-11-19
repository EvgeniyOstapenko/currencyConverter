package com.example.currencyConverter.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Enquiry {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private User user;

    private Long amount;

    public void setUser(User user) {
        this.user = user;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
