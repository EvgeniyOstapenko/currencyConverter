package com.example.currencyConverter.domain;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usr")
@Data
public class User {

    @Id
    private Long id;

    private Long enquiry_id;


    public void setId(Long id) {
        this.id = id;
    }

}
