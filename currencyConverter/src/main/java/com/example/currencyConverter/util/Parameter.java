package com.example.currencyConverter.util;

public enum Parameter {

    BIG("BIG","10_000_USD"),
    HUGE("HUGE","100_000_USD"),
    POPULAR("POPULAR", "Rating of currency conversion directions by popularity");

    private final String code;
    private final String description;

    Parameter(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
