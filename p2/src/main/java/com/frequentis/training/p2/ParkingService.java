package com.frequentis.training.p2;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

public class ParkingService implements Parking {

    private class ParkingLocation {
        private int row;
        private int column;

        private ParkingLocation(final int row, final int column) {
            this.row = row;
            this.column = column;
        }
    }

    private final boolean[][] parkingPlaces;
    private int availableSpaces;

    public ParkingService() {
        this.parkingPlaces = new boolean[10][10];
        this.availableSpaces = 100;
    }

    public ParkingService(final boolean[][] parkingPlaces) {
        this.parkingPlaces = Arrays.stream(parkingPlaces).map(boolean[]::clone).toArray(boolean[][]::new);
        this.availableSpaces = calculateAvailableSpaces();
    }

    @Override
    public boolean isParkingPlaceAvailable(final Vehicle vehicle) {
        if (vehicle.equals(Vehicle.CAR)) {
            return availableSpaces > 0;
        } else {
            return availableSpaces >= 5 && getFirstAvailableParkingPlaceForBus().isPresent();
        }
    }

    @Override
    public boolean parkCar() {
        if (availableSpaces > 0) {
            for (int i = 0; i < parkingPlaces.length; ++i) {
                for (int j = 0; j < parkingPlaces[i].length; ++j) {
                    if (parkingPlaces[i][j]) {
                        parkingPlaces[i][j] = false;
                        availableSpaces--;

                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public boolean parkBus() {
        if (availableSpaces >= 5) {
            Optional<ParkingLocation> parkingLocation = getFirstAvailableParkingPlaceForBus();

            if (parkingLocation.isPresent()) {
                int i = parkingLocation.get().row;

                for (int j = parkingLocation.get().column; j < parkingLocation.get().column + 5; ++j) {
                    parkingPlaces[i][j] = false;
                }

                availableSpaces -= 5;

                return true;
            }
        }

        return false;
    }

    @Override
    public int getAvailablePlaces() {
        return availableSpaces;
    }

    private Optional<ParkingLocation> getFirstAvailableParkingPlaceForBus() {
        for (int i = 0; i < parkingPlaces.length; ++i) {
            int numberOfEmptyPlaces = 0;

            for (int j = 0; j < parkingPlaces[i].length; ++j) {
                if (parkingPlaces[i][j]) {
                    numberOfEmptyPlaces++;
                } else {
                    numberOfEmptyPlaces = 0;
                }

                if (numberOfEmptyPlaces == 5) {
                    return Optional.of(new ParkingLocation(i, j - 4));
                }
            }
        }

        return Optional.empty();
    }

    private int calculateAvailableSpaces() {
        return Arrays.stream(parkingPlaces)
                     .mapToInt(row -> (int) IntStream.range(0, row.length)
                                                     .mapToObj(i -> row[i]).filter(p -> p)
                                                     .count())
                     .sum();
    }
}
