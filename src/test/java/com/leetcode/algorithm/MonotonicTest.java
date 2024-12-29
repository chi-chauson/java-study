package com.leetcode.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonotonicTest {
    private final Monotonic monotonic = new Monotonic();

    @Test
    void testExample1() {
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        int[] expected = {-1, 3, -1};
        int[] actual = monotonic.nextGreaterElement(nums1, nums2);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testExample2() {
        int[] nums1 = {2, 4};
        int[] nums2 = {1, 2, 3, 4};
        int[] expected = {3, -1};
        int[] actual = monotonic.nextGreaterElement(nums1, nums2);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testEmptyNums1() {
        int[] nums1 = {};
        int[] nums2 = {1, 2, 3};
        int[] expected = {};
        int[] actual = monotonic.nextGreaterElement(nums1, nums2);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testSingleElementNums1() {
        int[] nums1 = {2};
        int[] nums2 = {1, 2, 3};
        int[] expected = {3};
        int[] actual = monotonic.nextGreaterElement(nums1, nums2);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testNoGreaterElements() {
        int[] nums1 = {4,3,2,1};
        int[] nums2 = {1,2,3,4};
        int[] expected = {-1,4,3,2};
        int[] actual = monotonic.nextGreaterElement(nums1, nums2);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testNums1EqualsNums2() {
        int[] nums1 = {1,2,3,4};
        int[] nums2 = {1,2,3,4};
        int[] expected = {2,3,4,-1};
        int[] actual = monotonic.nextGreaterElement(nums1, nums2);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testLargeInput() {
        int[] nums1 = new int[500];
        int[] nums2 = new int[1000];
        int[] expected = new int[500];
        for (int i = 0; i < 500; i++) {
            nums1[i] = i;
            nums2[i] = i;
            expected[i] = i + 1;
        }
        for(int i = 500; i < 1000; i++) {
            nums2[i] = i;
        }
        int[] actual = monotonic.nextGreaterElement(nums1, nums2);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testLargeInput2() {
        int[] nums1 = new int[500];
        int[] nums2 = new int[1000];
        int[] expected = new int[500];

        // nums1 is interleaved with nums2
        for (int i = 0; i < 500; i++) {
            nums1[i] = i*2;
            nums2[i*2] = i*2;
            nums2[i*2+1] = i*2+1;
            expected[i] = i*2+1;
        }
        assertArrayEquals(expected, monotonic.nextGreaterElement(nums1, nums2));
    }

    @Test
    void testNums1SubsetAtEndNums2() {
        int[] nums1 = {2,3,4};
        int[] nums2 = {1,2,3,4};
        int[] expected = {3,4,-1};
        assertArrayEquals(expected, monotonic.nextGreaterElement(nums1, nums2));
    }

}