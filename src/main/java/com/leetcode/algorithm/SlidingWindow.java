package com.leetcode.algorithm;

public class SlidingWindow {
    public double findMaxAverage(int[] nums, int k) {
        int left = 0;
        double ans = Double.NEGATIVE_INFINITY; // Initialize to the smallest possible value
        double curr = 0;

        for (int right = 0; right < nums.length; right++) {
            // Add the current element to the current window sum
            curr += nums[right];

            // Once we've added k elements, start sliding the window
            if (right - left + 1 == k) {
                // Calculate the average for the current window
                double currentAverage = curr / k;
                // Update the maximum average if needed
                if (currentAverage > ans) {
                    ans = currentAverage;
                }
                // Subtract the element going out of the window
                curr -= nums[left];
                // Move the left pointer to the right
                left++;
            }
        }

        return ans;
    }

    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int ans = 0;
        int numOfZeros = 0;

        for (int right = 0; right < nums.length; right++) {
            // If the current element is 0, increment the zero count
            if (nums[right] == 0) {
                numOfZeros++;
            }

            // If the number of zeros in the window exceeds k, shrink the window from the left
            while (numOfZeros > k) {
                if (nums[left] == 0) {
                    numOfZeros--;
                }
                left++;
            }

            // Update the maximum window size
            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }

//    public int fn(int[] arr) {
//        int left = 0, ans = 0, curr = 0;
//
//        for (int right = 0; right < arr.length; right++) {
//            // do logic here to add arr[right] to curr
//
//            while (WINDOW_CONDITION_BROKEN) {
//                // remove arr[left] from curr
//                left++;
//            }
//
//            // update ans
//        }
//
//        return ans;
//    }
}
