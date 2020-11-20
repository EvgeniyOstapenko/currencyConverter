package com.example.currencyConverter.model;

import com.example.currencyConverter.util.Currency;

import java.math.BigDecimal;

public class ConverterModel {

    private BigDecimal srcAmount;

    private Currency tgtCurrency;

    private Long userId;


    public Currency getTgtCurrency() {
        return tgtCurrency;
    }

    public void setTgtCurrency(Currency tgtCurrency) {
        this.tgtCurrency = tgtCurrency;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public BigDecimal getUSD() {
        return srcAmount;
    }

    public void setUSD(BigDecimal srcAmount) {
        this.srcAmount = srcAmount;
    }
}
