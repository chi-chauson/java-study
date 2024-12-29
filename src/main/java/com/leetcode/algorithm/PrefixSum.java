package com.leetcode.algorithm;

import java.util.Arrays;

public class PrefixSum {
    public int[] runningSum(int[] nums) {
        int[] sums = new int[nums.length];
        sums[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        return sums;
    }

    public int minStartValue(int[] nums) {
        int[] prefixSums = new int[nums.length];
        prefixSums[0] = nums[0];
        int min = Math.min(0, prefixSums[0]);
        for (int i = 1; i < nums.length; i++) {
            prefixSums[i] = prefixSums[i - 1] + nums[i];
            if (prefixSums[i] < min) {
                min = prefixSums[i];
            }
        }

        return 1 - min;
    }

    public int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        int[] output = new int[n];
        Arrays.fill(output, -1); // Initialize all elements to -1

        if (k == 0) {
            // If k is 0, the average is the element itself
            return nums.clone(); // Return a clone to prevent modifying the original array
        }

        int windowSize = 2 * k + 1;
        if (windowSize > n) {
            // If the window size is larger than the array, all averages remain -1
            return output;
        }

        // Compute prefix sums with an extra element at the start
        long[] prefixes = new long[n + 1];
        prefixes[0] = 0;
        for (int i = 0; i < n; i++) {
            prefixes[i + 1] = prefixes[i] + nums[i];
        }

        // Iterate through each index to compute the k-radius average
        for (int i = k; i < n - k; i++) {
            long sum = prefixes[i + k + 1] - prefixes[i - k];
            int average = (int)(sum / windowSize); // Integer division truncates towards zero
            output[i] = average;
        }

        return output;

    }

//    public int[] getAverages(int[] nums, int k) {
//        if (k == 0) {
//            return nums;
//        }
//
//        int n = nums.length;
//        int[] prefixes = new int[n];
//        int[] output = new int[n];
//        prefixes[0] = nums[0];
//        output[0] = -1;
//        for (int i = 1; i < n; i++) {
//            prefixes[i] = prefixes[i - 1] + nums[i];
//            if (i < k || i > n - k - 1) {
//                output[i] = -1;
//            }
//        }
//
//        for (int i = 0; i < n; i++) {
//            if (output[i] != -1) {
//                output[i] = (prefixes[i + k] - prefixes[i - k]) / (2 * k);
//            }
//        }
//
//        return output;
//
//    }
}
