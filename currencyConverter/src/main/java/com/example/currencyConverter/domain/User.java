package com.example.currencyConverter.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }
}
