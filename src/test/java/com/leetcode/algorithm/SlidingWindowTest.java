package com.leetcode.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlidingWindowTest {
    // Instance of the class to be tested
    private final SlidingWindow slidingWindow = new SlidingWindow();

    /**
     * Test Case 1:
     * Input: nums = [1,12,-5,-6,50,3], k = 4
     * Expected Output: 12.75000
     */
    @Test
    public void testExample1() {
        int[] nums = {1, 12, -5, -6, 50, 3};
        int k = 4;
        double expected = 12.75000;
        double actual = slidingWindow.findMaxAverage(nums, k);
        assertEquals(expected, actual, 1e-5, "Test Case 1 Failed");
    }

    /**
     * Test Case 2:
     * Input: nums = [5], k = 1
     * Expected Output: 5.00000
     */
    @Test
    public void testExample2() {
        int[] nums = {5};
        int k = 1;
        double expected = 5.00000;
        double actual = slidingWindow.findMaxAverage(nums, k);
        assertEquals(expected, actual, 1e-5, "Test Case 2 Failed");
    }

    /**
     * Test Case 3:
     * Input: nums = [0,4,0,3,2], k = 1
     * Expected Output: 4.00000
     */
    @Test
    public void testAdditional1() {
        int[] nums = {0, 4, 0, 3, 2};
        int k = 1;
        double expected = 4.00000;
        double actual = slidingWindow.findMaxAverage(nums, k);
        assertEquals(expected, actual, 1e-5, "Test Case 3 Failed");
    }

    /**
     * Test Case 4:
     * Input: nums = [-1,-12,-5,-6,-50,-3], k = 4
     * Expected Output: -18.00000
     * Explanation: (-1 -12 -5 -6) / 4 = -24 / 4 = -6
     * Next window: (-12 -5 -6 -50) / 4 = -73 / 4 = -18.25
     * Next window: (-5 -6 -50 -3) / 4 = -64 / 4 = -16
     * Maximum average is -6
     */
    @Test
    public void testAllNegativeNumbers() {
        int[] nums = {-1, -12, -5, -6, -50, -3};
        int k = 4;
        double expected = -6.0;
        double actual = slidingWindow.findMaxAverage(nums, k);
        assertEquals(expected, actual, 1e-5, "Test Case 4 Failed");
    }

    /**
     * Test Case 5:
     * Input: nums = [1,1,1,1,1,1], k = 6
     * Expected Output: 1.00000
     */
    @Test
    public void testAllElementsSame() {
        int[] nums = {1, 1, 1, 1, 1, 1};
        int k = 6;
        double expected = 1.00000;
        double actual = slidingWindow.findMaxAverage(nums, k);
        assertEquals(expected, actual, 1e-5, "Test Case 5 Failed");
    }

    /**
     * Test Case 6:
     * Input: nums = [10, -10, 10, -10, 10], k = 2
     * Expected Output: 10.00000
     * Explanation: The maximum average is (10 + (-10)) / 2 = 0, but (10 + (-10)) / 2 is not the maximum.
     * Actually, windows (10, -10) = 0, (-10, 10) = 0, (10, -10) = 0, (-10, 10) = 0
     * All averages are 0. So expected is 0.00000
     * Correction: Since all window sums are 0, average is 0
     */
    @Test
    public void testAlternatingPositiveNegative() {
        int[] nums = {10, -10, 10, -10, 10};
        int k = 2;
        double expected = 0.0;
        double actual = slidingWindow.findMaxAverage(nums, k);
        assertEquals(expected, actual, 1e-5, "Test Case 6 Failed");
    }

    /**
     * Test Case 7:
     * Input: nums = [1,2,3,4,5,6,7,8,9,10], k = 5
     * Expected Output: 8.0
     * Explanation: The last window [6,7,8,9,10] has sum 40, average 8.0
     */
    @Test
    public void testIncreasingSequence() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int k = 5;
        double expected = 8.0;
        double actual = slidingWindow.findMaxAverage(nums, k);
        assertEquals(expected, actual, 1e-5, "Test Case 7 Failed");
    }

    /**
     * Test Case 8:
     * Input: nums = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}, k = 2
     * Expected Output: 2147483647.0
     */
    @Test
    public void testMaxIntegerValues() {
        int[] nums = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        int k = 2;
        double expected = (double) Integer.MAX_VALUE;
        double actual = slidingWindow.findMaxAverage(nums, k);
        assertEquals(expected, actual, 1e-5, "Test Case 8 Failed");
    }

    /**
     * Test Case 9:
     * Input: nums = {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE}, k = 2
     * Expected Output: -2147483648.0
     */
    @Test
    public void testMinIntegerValues() {
        int[] nums = {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
        int k = 2;
        double expected = (double) Integer.MIN_VALUE;
        double actual = slidingWindow.findMaxAverage(nums, k);
        assertEquals(expected, actual, 1e-5, "Test Case 9 Failed");
    }

    /**
     * Test Case 10:
     * Input: nums = {100000, 100000, 100000, 100000, 100000}, k = 3
     * Expected Output: 100000.0
     */
    @Test
    public void testLargeNumbers() {
        int[] nums = {100000, 100000, 100000, 100000, 100000};
        int k = 3;
        double expected = 100000.0;
        double actual = slidingWindow.findMaxAverage(nums, k);
        assertEquals(expected, actual, 1e-5, "Test Case 10 Failed");
    }

    /**
     * Test Case 11:
     * Input: nums = {1, -1, 1, -1, 1, -1, 1}, k = 3
     * Expected Output: 0.33333
     * Explanation: The window [1, -1, 1] has sum 1, average ≈0.33333
     */
    @Test
    public void testAlternatingOnes() {
        int[] nums = {1, -1, 1, -1, 1, -1, 1};
        int k = 3;
        double expected = (1 - 1 + 1) / 3.0; // 1/3 ≈0.33333
        double actual = slidingWindow.findMaxAverage(nums, k);
        assertEquals(expected, actual, 1e-5, "Test Case 11 Failed");
    }

    /**
     * Test Case 12:
     * Input: nums = {1, 2}, k = 2
     * Expected Output: 1.5
     */
    @Test
    public void testMinimumK() {
        int[] nums = {1, 2};
        int k = 2;
        double expected = 1.5;
        double actual = slidingWindow.findMaxAverage(nums, k);
        assertEquals(expected, actual, 1e-5, "Test Case 12 Failed");
    }

    /**
     * Test Case 13:
     * Input: nums = {Integer.MAX_VALUE, Integer.MIN_VALUE}, k = 1
     * Expected Output: 2147483647.0
     */
    @Test
    public void testMixedExtremeValuesK1() {
        int[] nums = {Integer.MAX_VALUE, Integer.MIN_VALUE};
        int k = 1;
        double expected = (double) Integer.MAX_VALUE;
        double actual = slidingWindow.findMaxAverage(nums, k);
        assertEquals(expected, actual, 1e-5, "Test Case 13 Failed");
    }

    /**
     * Test Case 14:
     * Input: nums = {Integer.MAX_VALUE, Integer.MIN_VALUE}, k = 2
     * Expected Output: (2147483647 + (-2147483648)) / 2 = -0.5
     */
    @Test
    public void testMixedExtremeValuesK2() {
        int[] nums = {Integer.MAX_VALUE, Integer.MIN_VALUE};
        int k = 2;
        double expected = ((double) Integer.MAX_VALUE + (double) Integer.MIN_VALUE) / 2.0;
        double actual = slidingWindow.findMaxAverage(nums, k);
        assertEquals(expected, actual, 1e-5, "Test Case 14 Failed");
    }

    /**
     * Test Case 15:
     * Input: nums = {1, 2, 3, 4, 5}, k = 5
     * Expected Output: 3.0
     */
    @Test
    public void testFullArrayWindow() {
        int[] nums = {1, 2, 3, 4, 5};
        int k = 5;
        double expected = 3.0;
        double actual = slidingWindow.findMaxAverage(nums, k);
        assertEquals(expected, actual, 1e-5, "Test Case 15 Failed");
    }

    /**
     * Test Case 1:
     * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
     * Expected Output: 6
     * Explanation: Flipping the 0's at indices 5 and 10 yields six consecutive 1's.
     */
    @Test
    public void testExample3() {
        int[] nums = {1,1,1,0,0,0,1,1,1,1,0};
        int k = 2;
        int expected = 6;
        int actual = slidingWindow.longestOnes(nums, k);
        assertEquals(expected, actual, "Failed Test Example 1: Expected maximum consecutive 1's to be 6.");
    }

    /**
     * Test Case 2:
     * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
     * Expected Output: 10
     * Explanation: Flipping the 0's at indices 4,5,10 yields ten consecutive 1's.
     */
    @Test
    public void testExample4() {
        int[] nums = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int k = 3;
        int expected = 10;
        int actual = slidingWindow.longestOnes(nums, k);
        assertEquals(expected, actual, "Failed Test Example 2: Expected maximum consecutive 1's to be 10.");
    }

    /**
     * Test Case 3:
     * Input: nums = [1,1,1,1,1], k = 0
     * Expected Output: 5
     * Explanation: No flips needed as all elements are already 1.
     */
    @Test
    public void testAllOnesNoFlips() {
        int[] nums = {1,1,1,1,1};
        int k = 0;
        int expected = 5;
        int actual = slidingWindow.longestOnes(nums, k);
        assertEquals(expected, actual, "Failed Test AllOnesNoFlips: Expected maximum consecutive 1's to be 5.");
    }

    /**
     * Test Case 4:
     * Input: nums = [0,0,0,0,0], k = 5
     * Expected Output: 5
     * Explanation: Flip all 0's to 1's to get five consecutive 1's.
     */
    @Test
    public void testAllZerosWithFlips() {
        int[] nums = {0,0,0,0,0};
        int k = 5;
        int expected = 5;
        int actual = slidingWindow.longestOnes(nums, k);
        assertEquals(expected, actual, "Failed Test AllZerosWithFlips: Expected maximum consecutive 1's to be 5.");
    }

    /**
     * Test Case 5:
     * Input: nums = [1,0,1,0,1], k = 1
     * Expected Output: 3
     * Explanation: Flip the 0 at index 3 to get [1,0,1,1,1], which has three consecutive 1's.
     */
    @Test
    public void testAlternatingOnesAndZeros() {
        int[] nums = {1,0,1,0,1};
        int k = 1;
        int expected = 3;
        int actual = slidingWindow.longestOnes(nums, k);
        assertEquals(expected, actual, "Failed Test AlternatingOnesAndZeros: Expected maximum consecutive 1's to be 3.");
    }

    /**
     * Test Case 6:
     * Input: nums = [1], k = 0
     * Expected Output: 1
     * Explanation: Single element is 1, no flips needed.
     */
    @Test
    public void testSingleOneNoFlip() {
        int[] nums = {1};
        int k = 0;
        int expected = 1;
        int actual = slidingWindow.longestOnes(nums, k);
        assertEquals(expected, actual, "Failed Test SingleOneNoFlip: Expected maximum consecutive 1's to be 1.");
    }

    /**
     * Test Case 7:
     * Input: nums = [0], k = 1
     * Expected Output: 1
     * Explanation: Flip the single 0 to 1.
     */
    @Test
    public void testSingleZeroWithFlip() {
        int[] nums = {0};
        int k = 1;
        int expected = 1;
        int actual = slidingWindow.longestOnes(nums, k);
        assertEquals(expected, actual, "Failed Test SingleZeroWithFlip: Expected maximum consecutive 1's to be 1.");
    }

    /**
     * Test Case 8:
     * Input: nums = [1,0,1,1,1,0,0,1,1,1,1,0,1], k = 3
     * Expected Output: 10
     * Explanation: Flip the 0's at indices 5,6,11 to get ten consecutive 1's.
     */
    @Test
    public void testMixedOnesAndZeros() {
        int[] nums = {1,0,1,1,1,0,0,1,1,1,1,0,1};
        int k = 3;
        int expected = 11;
        int actual = slidingWindow.longestOnes(nums, k);
        assertEquals(expected, actual, "Failed Test MixedOnesAndZeros: Expected maximum consecutive 1's to be 11.");
    }

    /**
     * Test Case 9:
     * Input: nums = [1,1,1,1,1,1,1,1], k = 2
     * Expected Output: 8
     * Explanation: All elements are 1, window size is 8.
     */
    @Test
    public void testAllOnesWithFlips() {
        int[] nums = {1,1,1,1,1,1,1,1};
        int k = 2;
        int expected = 8;
        int actual = slidingWindow.longestOnes(nums, k);
        assertEquals(expected, actual, "Failed Test AllOnesWithFlips: Expected maximum consecutive 1's to be 8.");
    }

    /**
     * Test Case 10:
     * Input: nums = [0,1,0,1,0,1,0,1,0], k = 4
     * Expected Output: 8
     * Explanation: Flip the 0's at indices 0,2,4,6 to get eight consecutive 1's.
     */
    @Test
    public void testFrequentFlips() {
        int[] nums = {0,1,0,1,0,1,0,1,0};
        int k = 4;
        int expected = 8;
        int actual = slidingWindow.longestOnes(nums, k);
        assertEquals(expected, actual, "Failed Test FrequentFlips: Expected maximum consecutive 1's to be 8.");
    }

    /**
     * Test Case 11:
     * Input: nums = [1,0,1,1,1,0,0,1,1,1,1,0,1], k = 3
     * Expected Output: 10
     * Explanation: Flip the 0's at indices 5,6,11 to get ten consecutive 1's.
     */
    @Test
    public void testAnotherMixedCase() {
        int[] nums = {1,0,1,1,1,0,0,1,1,1,1,0,1};
        int k = 3;
        int expected = 11;
        int actual = slidingWindow.longestOnes(nums, k);
        assertEquals(expected, actual, "Failed Test AnotherMixedCase: Expected maximum consecutive 1's to be 11.");
    }

    /**
     * Test Case 12:
     * Input: nums = [0,1,1,1,0,1,1,0,1,1,1,1,0,1,1], k = 2
     * Expected Output: 11
     * Explanation: Flip the 0's at indices 0 and 4 or 6 and 12 to get eleven consecutive 1's.
     */
    @Test
    public void testComplexMixedCase() {
        int[] nums = {0,1,1,1,0,1,1,0,1,1,1,1,0,1,1};
        int k = 2;
        int expected = 11;
        int actual = slidingWindow.longestOnes(nums, k);
        assertEquals(expected, actual, "Failed Test ComplexMixedCase: Expected maximum consecutive 1's to be 11.");
    }

}