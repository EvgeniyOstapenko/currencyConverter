package com.example.currencyConverter.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usr")
public class User {

    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

}
