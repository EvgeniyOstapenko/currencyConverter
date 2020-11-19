package com.example.currencyConverter.domain;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    private Long id;

    private Long inqueryId;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInqueryId() {
        return inqueryId;
    }
}
