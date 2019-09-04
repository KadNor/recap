package com.frequentis.training.p3;

public class Ferrari implements Car {

    private final static int MILES_PER_GALLON = 25;

    private float gasTankState;
    private int mileage;
    private boolean brokenDown;

    public Ferrari(final float gasTankState, final float mileage) {
        if (gasTankState < 0 || mileage < 0) {
            throw new IllegalArgumentException("The gas tank state and mileage must be greater or equal than 0.");
        }

        this.gasTankState = gasTankState;
        this.mileage = (int) mileage;
    }

    public boolean turnOnAndDrive(final int milesToDrive) {
        boolean drivenWithoutProblems = false;

        if (milesToDrive >= 0 && !brokenDown) {
            if (Math.random() < calculateChanceToBreakDown()) {
                brokenDown = true;
            } else {
                drivenWithoutProblems = driveMilesWithGas(milesToDrive, milesToDrive * MILES_PER_GALLON);
            }
        }

        return drivenWithoutProblems;
    }

    public int getMileage() {
        return mileage;
    }

    public double getGasTankState() {
        return gasTankState;
    }

    public boolean isBrokenDown() {
        return brokenDown;
    }

    public void repair() {
        brokenDown = false;
    }

    public void fillGas(final double numberOfGallons) {
        if (numberOfGallons > 0) {
            gasTankState += numberOfGallons;
        }
    }

    private boolean driveMilesWithGas(final int milesToDrive, final int requiredGas) {
        boolean gasRemained = true;

        if (gasTankState < requiredGas) {
            mileage += gasTankState / MILES_PER_GALLON;
            gasTankState = 0;

            gasRemained = false;
        } else {
            mileage += milesToDrive;
            gasTankState -= requiredGas;
        }

        return gasRemained;
    }

    private float calculateChanceToBreakDown() {
        float chanceToBreakDown = 0.1f;

        if (mileage > 10000) {
            chanceToBreakDown = mileage / 10000;
            if (mileage % 10000 != 0) {
                chanceToBreakDown++;
            }
        }

        return chanceToBreakDown / 10f;
    }
}
