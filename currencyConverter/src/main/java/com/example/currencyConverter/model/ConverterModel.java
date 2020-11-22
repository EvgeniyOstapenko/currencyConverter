package com.example.currencyConverter.model;

import com.example.currencyConverter.util.Currency;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class ConverterModel {

    private BigDecimal srcAmount;

    private Currency targetCurrency;

    private Long userId;

    @ApiModelProperty(hidden = true)
    private Long requestId;


    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
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

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long enquiry) {
        this.requestId = enquiry;
    }


    public BigDecimal getSrcAmount() {
        return srcAmount;
    }

    @Override
    public String toString() {
        return "ConverterModel{" +
                "srcAmount=" + srcAmount +
                ", targetCurrency=" + targetCurrency +
                ", userId=" + userId +
                '}';
    }

    public void setSrcAmount(BigDecimal srcAmount) {
        this.srcAmount = srcAmount;
    }
}
