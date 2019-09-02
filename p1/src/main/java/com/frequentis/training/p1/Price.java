package com.frequentis.training.p1;

public class Price {

    public enum Currency {
        EUR, RON
    }

    private double value;
    private Currency currency;

    public void setValue(double value) {
        this.value = value;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }
}
