package com.leetcode.algorithm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrefixSumTest {

    @Test
    void testExample1() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {1, 2, 3, 4};
        int[] expected = {1, 3, 6, 10};
        assertArrayEquals(expected, prefixSum.runningSum(nums), "Running sum should be [1, 3, 6, 10]");
    }

    @Test
    void testExample2() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {1, 1, 1, 1, 1};
        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, prefixSum.runningSum(nums), "Running sum should be [1, 2, 3, 4, 5]");
    }

    @Test
    void testExample3() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {3, 1, 2, 10, 1};
        int[] expected = {3, 4, 6, 16, 17};
        assertArrayEquals(expected, prefixSum.runningSum(nums), "Running sum should be [3, 4, 6, 16, 17]");
    }

    @Test
    void testSingleElement() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {5};
        int[] expected = {5};
        assertArrayEquals(expected, prefixSum.runningSum(nums), "Running sum should be [5]");
    }

    @Test
    void testAllNegativeNumbers() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {-1, -2, -3, -4};
        int[] expected = {-1, -3, -6, -10};
        assertArrayEquals(expected, prefixSum.runningSum(nums), "Running sum should be [-1, -3, -6, -10]");
    }

    @Test
    void testMixedPositiveAndNegativeNumbers() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {1, -2, 3, -4, 5};
        int[] expected = {1, -1, 2, -2, 3};
        assertArrayEquals(expected, prefixSum.runningSum(nums), "Running sum should be [1, -1, 2, -2, 3]");
    }

    @Test
    void testLargeNumbers() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {1000000, 2000000, 3000000};
        int[] expected = {1000000, 3000000, 6000000};
        assertArrayEquals(expected, prefixSum.runningSum(nums), "Running sum should be [1000000, 3000000, 6000000]");
    }

    @Test
    void testMaximumLength() {
        PrefixSum prefixSum = new PrefixSum();
        int size = 1000;
        int[] nums = new int[size];
        int[] expected = new int[size];
        nums[0] = 1;
        expected[0] = 1;
        for (int i = 1; i < size; i++) {
            nums[i] = 1;
            expected[i] = expected[i - 1] + 1;
        }
        assertArrayEquals(expected, prefixSum.runningSum(nums), "Running sum should be an array of incremental sums up to 1000");
    }

    @Test
    void testSingleNegativeElement() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {-10};
        int[] expected = {-10};
        assertArrayEquals(expected, prefixSum.runningSum(nums), "Running sum should be [-10]");
    }

    @Test
    void testZerosInArray() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {0, 0, 0, 0};
        int[] expected = {0, 0, 0, 0};
        assertArrayEquals(expected, prefixSum.runningSum(nums), "Running sum should be [0, 0, 0, 0]");
    }

    @Test
    void testExample4() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {-3, 2, -3, 4, 2};
        int expected = 5;
        int result = prefixSum.minStartValue(nums);
        assertEquals(expected, result, "Test Example 1 Failed");
    }

    @Test
    void testExample5() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {1, 2};
        int expected = 1;
        int result = prefixSum.minStartValue(nums);
        assertEquals(expected, result, "Test Example 2 Failed");
    }

    @Test
    void testExample6() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {1, -2, -3};
        int expected = 5;
        int result = prefixSum.minStartValue(nums);
        assertEquals(expected, result, "Test Example 3 Failed");
    }

    @Test
    void testAllPositive() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {2, 3, 4};
        int expected = 1;
        int result = prefixSum.minStartValue(nums);
        assertEquals(expected, result, "Test All Positive Numbers Failed");
    }

    @Test
    void testAllNegative() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {-1, -2, -3};
        int expected = 7; // 1 - (-6) = 7
        int result = prefixSum.minStartValue(nums);
        assertEquals(expected, result, "Test All Negative Numbers Failed");
    }

    @Test
    void testSinglePositive() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {10};
        int expected = 1;
        int result = prefixSum.minStartValue(nums);
        assertEquals(expected, result, "Test Single Positive Element Failed");
    }

    @Test
    void testSingleNegative() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {-5};
        int expected = 6; // 1 - (-5) = 6
        int result = prefixSum.minStartValue(nums);
        assertEquals(expected, result, "Test Single Negative Element Failed");
    }

    @Test
    void testWithZeros() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {0, 0, 0};
        int expected = 1;
        int result = prefixSum.minStartValue(nums);
        assertEquals(expected, result, "Test Array with Zeros Failed");
    }

    @Test
    void testMixedNumbers() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {3, -4, 2, -3, 1};
        int expected = 5; // Cumulative sums: 5, 1, 3, 0 (invalid), so startValue=6
        // Wait: let's compute:

        // If startValue = 5
        // 5 + 3 = 8
        // 8 -4 = 4
        // 4 +2 =6
        // 6 -3=3
        // 3 +1=4 => All >=1
        // So expected should be 1 - min prefix sum.

        // Let's compute prefix sums:
        // 3, -1,1, -2, -1
        // min = -2
        // startValue =1 - (-2) =3
        // So expected=3

        // Correcting expected
        expected = 3;
        int result = prefixSum.minStartValue(nums);
        assertEquals(expected, result, "Test Mixed Numbers Failed");
    }

    @Test
    void testLargeIntegers() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {100, -200, 100, -100, 50};
        // Compute prefix sums: 100, -100, 0, -100, -50
        // min = -100
        // startValue =1 - (-100) =101
        int expected = 101;
        int result = prefixSum.minStartValue(nums);
        assertEquals(expected, result, "Test Large Numbers Failed");
    }

    @Test
    void testMinimumStartValueOne() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {0, 0, 1};
        int expected = 1;
        int result = prefixSum.minStartValue(nums);
        assertEquals(expected, result, "Test Minimum Start Value One Failed");
    }


    @Test
    void testExample7() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {7,4,3,9,1,8,5,2,6};
        int k = 3;
        int[] expected = {-1,-1,-1,5,4,4,-1,-1,-1};
        assertArrayEquals(expected, prefixSum.getAverages(nums, k), "Test Example 1 Failed");
    }

    @Test
    void testExample8() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {100000};
        int k = 0;
        int[] expected = {100000};
        assertArrayEquals(expected, prefixSum.getAverages(nums, k), "Test Example 2 Failed");
    }

    @Test
    void testExample9() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {8};
        int k = 100000;
        int[] expected = {-1};
        assertArrayEquals(expected, prefixSum.getAverages(nums, k), "Test Example 3 Failed");
    }

    @Test
    void testGetAveragesWithAllPositive() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {1,2,3,4,5};
        int k = 1;
        int[] expected = {-1,2,3,4,-1};
        assertArrayEquals(expected, prefixSum.getAverages(nums, k), "Test All Positive Numbers Failed");
    }

    @Test
    void testGetAveragesWithAllNegative() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {-1,-2,-3,-4,-5};
        int k = 2;
        int[] expected = {-1,-1,-3,-1,-1};
        // Explanation: Only index 2 has enough elements on both sides.
        // Sum = (-3) + (-2) + (-1) + (-4) + (-5) = -15
        // Average = -15 / 5 = -3
        assertArrayEquals(expected, prefixSum.getAverages(nums, k), "Test All Negative Numbers Failed");
    }

    @Test
    void testGetAveragesWithSinglePositive() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {10};
        int k = 0;
        int[] expected = {10};
        assertArrayEquals(expected, prefixSum.getAverages(nums, k), "Test Single Positive Element Failed");
    }

    @Test
    void testGetAveragesWithSingleNegative() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {-5};
        int k = 0;
        int[] expected = {-5};
        assertArrayEquals(expected, prefixSum.getAverages(nums, k), "Test Single Negative Element Failed");
    }

    @Test
    void testGetAveragesWithZeros() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {0, 0, 0, 0, 0};
        int k = 2;
        int[] expected = {-1,-1,0,-1,-1};
        // Sum at index 2: 0+0+0+0+0=0, Average=0/5=0
        assertArrayEquals(expected, prefixSum.getAverages(nums, k), "Test Array with Zeros Failed");
    }

    @Test
    void testGetAveragesWithMixedNumbers() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {3, -4, 2, -3, 1};
        int k = 1;
        int[] expected = {-1, -1, -1, -1, -1}; // k=1 requires at least 1 element before and after
        // Wait, for k=1, n=5:
        // Indices 1,2,3 have valid averages
        // Let's compute:
        // For i=1: 3 + (-4) + 2 = 1 /3 = 0
        // For i=2: -4 + 2 + (-3) = -5 /3 = -1
        // For i=3: 2 + (-3) +1 =0 /3 =0
        // So expected: {-1,0,-1,0,-1}
        expected = new int[]{-1,0,-1,0,-1};
        assertArrayEquals(expected, prefixSum.getAverages(nums, k), "Test Mixed Numbers Failed");
    }

    @Test
    void testGetAveragesWithLargeK() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {1,2,3};
        int k = 100;
        int[] expected = {-1,-1,-1};
        assertArrayEquals(expected, prefixSum.getAverages(nums, k), "Test Large K Failed");
    }

    @Test
    void testGetAveragesWithKZero() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {5, -3, 7, 0};
        int k = 0;
        int[] expected = {5, -3, 7, 0};
        assertArrayEquals(expected, prefixSum.getAverages(nums, k), "Test k=0 Failed");
    }

    @Test
    void testGetAveragesWithEvenLength() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {1,2,3,4,5,6};
        int k = 2;
        int[] expected = {-1,-1,3,4,-1,-1};
        // Only indices 2 and 3 have valid averages
        // Index2: 1+2+3+4+5=15 /5=3
        // Index3:2+3+4+5+6=20 /5=4
        assertArrayEquals(expected, prefixSum.getAverages(nums, k), "Test Even Length Array Failed");
    }
}