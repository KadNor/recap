package com.frequentis.training.p3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FerrariTest {

    private static final float STANDARD_MILEAGE = 10000f;
    private static final float BREAK_DOWN_MILEAGE = 100001f;
    private static final int NEGATIVE_MILES = -1;

    private Car sut;

    @Test
    public void turnOnAndDrive_theNumberOfMilesIsNegative_shallReturnFalse() {
        // Given
        sut = new Ferrari(10.0f, STANDARD_MILEAGE);

        // When & Then
        assertFalse(sut.turnOnAndDrive(NEGATIVE_MILES));
    }

    @Test
    public void turnOnAndDrive_theMileageIsTooBig_shallBreakDown() {
        // When
        breakDownCar();

        // Then
        assertTrue(sut.isBrokenDown());
    }

    @Test
    public void turnOnAndDrive_theMileageIsTooBig_shallReturnFalse() {
        // Given
        sut = new Ferrari(10f, BREAK_DOWN_MILEAGE);

        // When & Then
        assertFalse(sut.turnOnAndDrive(0));
    }

    @Test
    public void turnOnAndDrive_thereIsNotEnoughGas_shallReturnFalse() {
        // Given
        sut = new Ferrari(0f, STANDARD_MILEAGE);

        // When & Then
        assertFalse(sut.turnOnAndDrive(1));
    }

    @Test
    public void repair_brokenDown_shallFixIt() {
        // Given
        breakDownCar();

        // When
        sut.repair();

        // Then
        assertFalse(sut.isBrokenDown());
    }

    @Test
    public void fillGas_carWithNoGas_shallIncreaseTheAmountOfGasInTheTank() {
        // Given
        sut = new Ferrari(0f, STANDARD_MILEAGE);
        double expected = 100f;

        // When
        sut.fillGas(expected);
        double actual = sut.getGasTankState();

        // Then
        assertEquals(expected, actual, 0.01);
    }

    private void breakDownCar() {
        sut = new Ferrari(0f, BREAK_DOWN_MILEAGE);
        sut.turnOnAndDrive(0);
    }
}
