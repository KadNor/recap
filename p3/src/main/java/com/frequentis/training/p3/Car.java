package com.frequentis.training.p3;

public interface Car {

    /**
     * Attempts to drive # miles, returns true for success or false for fail
     */
    boolean turnOnAndDrive(int milesToDrive);

    /**
     * Returns the number of miles this car has driven.
     */
    int getMileage();

    /**
     * Returns the amount of gas that the tank contains.
     */
    double getGasTankState();

    /**
     * Returns true if the car is broken down, and false otherwise.
     */
    boolean isBrokenDown();

    /**
     * Sets the car as no longer broken down
     */
    void repair();

    /**
     * Adds the given number of gallons of gas to the car’s gas tank
     */
    void fillGas(double numberOfGallons);
}
