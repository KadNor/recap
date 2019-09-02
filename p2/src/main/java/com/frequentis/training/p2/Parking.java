package com.frequentis.training.p2;

public interface Parking {

    boolean isParkingPlaceAvailable(Vehicle vehicle);

    boolean parkCar();

    boolean parkBus();

    int getAvailablePlaces();
}
