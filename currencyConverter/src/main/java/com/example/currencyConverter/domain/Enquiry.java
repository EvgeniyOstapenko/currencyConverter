package com.example.currencyConverter.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Enquiry {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private Long amount;


}
