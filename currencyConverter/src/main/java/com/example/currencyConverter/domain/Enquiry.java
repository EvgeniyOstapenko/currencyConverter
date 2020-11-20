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

    private Long userId;

    private String sourceCurrency;

    private String targetCurrency;

    public void setValue(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }
}
