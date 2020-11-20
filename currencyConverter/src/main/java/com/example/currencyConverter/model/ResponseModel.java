package com.example.currencyConverter.model;

import java.math.BigDecimal;

public class ResponseModel {

    private BigDecimal convertedValue;
    private Error error;

    public BigDecimal getConvertedValue() {
        return convertedValue;
    }

    public void setConvertedValue(BigDecimal convertedValue) {
        this.convertedValue = convertedValue;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
