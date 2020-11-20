package com.example.currencyConverter.model;

import com.example.currencyConverter.util.Currency;
import com.sun.jdi.PrimitiveValue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ConverterModel {

    private Currency srcCurrency;

    private Currency tgtCurrency;

    private BigDecimal srcAmount;

    private BigDecimal tgtAmount;

    private Long userId;


    public Currency getSrcCurrency() {
        return srcCurrency;
    }

    public void setSrcCurrency(Currency srcCurrency) {
        this.srcCurrency = srcCurrency;
    }

    public Currency getTgtCurrency() {
        return tgtCurrency;
    }

    public void setTgtCurrency(Currency tgtCurrency) {
        this.tgtCurrency = tgtCurrency;
    }

    public BigDecimal getSrcAmount() {
        return srcAmount;
    }

    public void setSrcAmount(BigDecimal srcAmount) {
        this.srcAmount = srcAmount;
    }

    public BigDecimal getTgtAmount() {
        return tgtAmount;
    }

    public void setTgtAmount(BigDecimal tgtAmount) {
        this.tgtAmount = tgtAmount;
    }

    public List<Currency> getSupportedCurrencies() {
        return Arrays.asList(Currency.values());
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
