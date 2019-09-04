package com.frequentis.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.frequentis.training.p1.Chair;
import com.frequentis.training.p1.Furniture;
import com.frequentis.training.p1.Price;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PropertyAnalyzerTest {

    private final static double DELTA = 0.00001;
    private PropertyAnalyzer sut;

    @BeforeEach
    void setUp() {
        sut = new PropertyAnalyzer();
    }

    @Test
    void getPropertyComplexityRatio_FurnitureClassIsGiven_shallReturnTheRatio() {
        // Given
        Class<?> testClass = Furniture.class;
        double expected = 66.6666666666;

        // When
        double actual = sut.getPropertyComplexityRatio(testClass);

        // Then
        assertEquals(expected, actual, DELTA);
    }

    @Test
    void getPropertyComplexityRatio_ChairClassIsGiven_shallReturnTheRatio() {
        // Given
        Class<?> testClass = Chair.class;
        double expected = 75;

        // When
        double actual = sut.getPropertyComplexityRatio(testClass);

        // Then
        assertEquals(expected, actual, DELTA);
    }

    @Test
    void getPropertyComplexityRatio_PriceClassIsGiven_shallReturnTheRatio() {
        // Given
        Class<?> testClass = Price.class;
        double expected = 100;

        // When
        double actual = sut.getPropertyComplexityRatio(testClass);

        // Then
        assertEquals(expected, actual, DELTA);
    }

    @Test
    void getPackagePropertyComplexityRatio_packageIsGiven_shallReturnTheRatio() {
        // Given
        String packageName = "com.frequentis.training.p1";
        double expected = 79.1666666666;

        // When
        double actual = sut.getPackagePropertyComplexityRatio(packageName);

        // Then
        assertEquals(expected, actual, DELTA);
    }
}