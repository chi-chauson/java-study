package com.leetcode.algorithm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovingAverageTest {
    private MovingAverage movingAverage;

    @BeforeEach
    public void setUp() {
        movingAverage = new MovingAverage(3);
    }

    @Test
    public void testSingleValue() {
        assertEquals(1.0, movingAverage.next(1), 0.00001); // Added delta for double comparison
    }

    @Test
    public void testWindowSizeExample() {
        assertEquals(1.0, movingAverage.next(1), 0.00001);
        assertEquals(5.5, movingAverage.next(10), 0.00001);
        assertEquals(4.666666666666667, movingAverage.next(3), 0.00001);
        assertEquals(6.0, movingAverage.next(5), 0.00001);
    }

    @Test
    public void testLargeWindow() {
        MovingAverage largeWindowMA = new MovingAverage(5);
        largeWindowMA.next(1);
        largeWindowMA.next(2);
        largeWindowMA.next(3);
        largeWindowMA.next(4);
        assertEquals(3.0, largeWindowMA.next(5), 0.00001);
        assertEquals(4.0, largeWindowMA.next(6), 0.00001);
    }

    @Test
    public void testNegativeValues() {
        MovingAverage negativeTest = new MovingAverage(2);
        negativeTest.next(-100000);
        assertEquals(-75000.0, negativeTest.next(-50000), 0.00001);
        assertEquals(-25000.0, negativeTest.next(0), 0.00001);

    }

    @Test
    public void testLargeValues() {
        MovingAverage largeValueTest = new MovingAverage(3);
        largeValueTest.next(100000);
        largeValueTest.next(100000);
        assertEquals(100000.0, largeValueTest.next(100000), 0.00001);
    }

    @Test
    public void testMixedValues() {
        // Test with a mix of positive and negative numbers
        MovingAverage ma = new MovingAverage(4);

        // After adding 5
        // Elements: [5]
        // Sum: 5
        // Average: 5 / 1 = 5.0
        assertEquals(5.0, ma.next(5), 1e-5, "After adding 5, average should be 5.0");

        // After adding 1
        // Elements: [5, 1]
        // Sum: 6
        // Average: 6 / 2 = 3.0
        assertEquals(3.0, ma.next(1), 1e-5, "After adding 1, average should be 3.0");

        // After adding -1
        // Elements: [5, 1, -1]
        // Sum: 5
        // Average: 5 / 3 ≈ 1.66667
        assertEquals(1.66667, ma.next(-1), 1e-5, "After adding -1, average should be approximately 1.66667");

        // After adding -5
        // Elements: [5, 1, -1, -5]
        // Sum: 0
        // Average: 0 / 4 = 0.0
        assertEquals(0.0, ma.next(-5), 1e-5, "After adding -5, average should be 0.0");

        // After adding 0
        // Elements: [1, -1, -5, 0] (5 is removed)
        // Sum: -5
        // Average: -5 / 4 = -1.25
        assertEquals(-1.25, ma.next(0), 1e-5, "After adding 0, average should be -1.25");
    }

}