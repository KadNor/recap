package com.frequentis.training.p1;

public class Chair extends Furniture {

    public boolean withSeatBack;

    public Chair() {
    }

    public Chair(Price price, String material, String producer, boolean withSeatBack) {
        this.price = price;
        this.material = material;
        this.producer = producer;
        this.withSeatBack = withSeatBack;
    }

    public boolean isWithSeatBack() {
        return withSeatBack;
    }

    public void setWithSeatBack(boolean withSeatBack) {
        this.withSeatBack = withSeatBack;
    }
}
