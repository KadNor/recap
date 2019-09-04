package com.frequentis.training.p2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParkingServiceTest {

    private static final boolean[][] ENOUGH_SPACE_FOR_A_BUS = new boolean[][]{
            {false, false, false, false, false},
            {true, true, true, true, true}
    };
    private static final boolean[][] NO_FREE_PLACES = new boolean[][]{
            {false, false, false, false, false}
    };
    private static final boolean[][] ENOUGH_PLACE_FOR_A_CAR_OR_BUS = new boolean[][]{
            {false, true, true, true, true, true, false, true}
    };
    private static final boolean[][] ENOUGH_PLACE_FOR_A_BUS = new boolean[][]{
            {false, false, true, true, true, true, true, true, true, true},
            {false, false, false, false, false, false, false, true, true, false}
    };

    private Parking sut;

    @Test
    public void parkBus_busParkedAlreadyAndNotEnoughPlaceLeft_returnsFalse() {
        // Given
        sut = new ParkingService(ENOUGH_PLACE_FOR_A_BUS);
        boolean actualReturnValue = sut.parkBus();
        assertTrue(actualReturnValue);

        // When
        boolean busParked = sut.parkBus();

        // Then
        assertFalse(busParked);
    }


    @Test
    public void parkBus_carParkedBefore_returnsFalse() {
        // Given
        sut = new ParkingService(ENOUGH_PLACE_FOR_A_CAR_OR_BUS);
        boolean actualReturnValue = sut.parkCar();
        assertTrue(actualReturnValue);

        // When
        boolean busParked = sut.parkBus();

        // Then
        assertFalse(busParked);
    }


    @Test
    public void isParkingPlaceAvailable_vehicleIsParkingAndThereAreFreePlace_shallNotModifyAvailablePlaces() {
        // Given
        sut = new ParkingService(ENOUGH_SPACE_FOR_A_BUS);
        int expected = sut.getAvailablePlaces();
        // When
        sut.isParkingPlaceAvailable(Vehicle.CAR);

        // Then
        assertEquals(expected, sut.getAvailablePlaces());
    }

    @Test
    public void isParkingPlaceAvailable_carIsParkingAndThereAreFreePlace_shallReturnTrue() {
        // Given
        sut = new ParkingService(ENOUGH_SPACE_FOR_A_BUS);
        int expected = sut.getAvailablePlaces();

        // When & Then
        assertTrue(sut.isParkingPlaceAvailable(Vehicle.CAR));
        assertEquals(expected, sut.getAvailablePlaces());
    }

    @Test
    public void isParkingPlaceAvailable_carIsParkingAndThereAreNotEnoughFreePlace_shallReturnFalse() {
        // Given
        sut = new ParkingService(NO_FREE_PLACES);

        // When & Then
        assertFalse(sut.isParkingPlaceAvailable(Vehicle.CAR));
    }

    @Test
    public void isParkingPlaceAvailable_busIsParkingAndThereAreFreePlace_shallReturnTrue() {
        // Given
        sut = new ParkingService(ENOUGH_SPACE_FOR_A_BUS);

        // When & Then
        assertTrue(sut.isParkingPlaceAvailable(Vehicle.BUS));
    }

    @Test
    public void isParkingPlaceAvailable_busIsParkingAndThereAreNotEnoughFreePlace_shallReturnFalse() {
        // Given
        sut = new ParkingService(NO_FREE_PLACES);

        // When & Then
        assertFalse(sut.isParkingPlaceAvailable(Vehicle.BUS));
    }

    @Test
    public void parkCar_carIsParkingAndThereAreEnoughFreePlace_shallReturnTrueAndModifyTheNumberOfAvailablePlaces() {
        // Given
        sut = new ParkingService(ENOUGH_SPACE_FOR_A_BUS);
        int expected = sut.getAvailablePlaces() - 1;

        // When
        boolean actualReturnValue = sut.parkCar();

        // Then
        assertTrue(actualReturnValue);
        assertEquals(expected, sut.getAvailablePlaces());
    }

    @Test
    public void parkCar_carIsParkingAndThereAreNotEnoughFreePlace_shallReturnFalseAndDoNotModifyTheNumberOfAvailablePlaces() {
        // Given
        sut = new ParkingService(NO_FREE_PLACES);
        int expected = sut.getAvailablePlaces();

        // When
        boolean actualReturnValue = sut.parkCar();

        // Then
        assertFalse(actualReturnValue);
        assertEquals(expected, sut.getAvailablePlaces());
    }

    @Test
    public void parkBus_busIsParkingAndThereAreEnoughFreePlace_shallReturnTrueAndModifyTheNumberOfAvailablePlaces() {
        // Given
        sut = new ParkingService(ENOUGH_SPACE_FOR_A_BUS);
        int expected = sut.getAvailablePlaces() - 5;

        // When
        boolean actualReturnValue = sut.parkBus();

        // Then
        assertTrue(actualReturnValue);
        assertEquals(expected, sut.getAvailablePlaces());
    }

    @Test
    public void parkBus_busIsParkingAndThereAreNotEnoughFreePlace_shallReturnTrueAndModifyTheNumberOfAvailablePlaces() {
        // Given
        sut = new ParkingService(NO_FREE_PLACES);
        int expected = sut.getAvailablePlaces();

        // When
        boolean actualReturnValue = sut.parkBus();

        // Then
        assertFalse(actualReturnValue);
        assertEquals(expected, sut.getAvailablePlaces());
    }
}
