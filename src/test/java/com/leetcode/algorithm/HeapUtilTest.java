package com.leetcode.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapUtilTest {
    @Test
    void testExample1() {
        HeapUtil util = new HeapUtil();
        int[] piles = {5, 4, 9};
        int k = 2;
        int result = util.minStoneSum(piles, k);
        assertEquals(12, result, "Expected the total stones to be 12 after 2 operations.");
    }

    @Test
    void testExample2() {
        HeapUtil util = new HeapUtil();
        int[] piles = {4, 3, 6, 7};
        int k = 3;
        int result = util.minStoneSum(piles, k);
        assertEquals(12, result, "Expected the total stones to be 12 after 3 operations.");
    }

    @Test
    void testSinglePile() {
        HeapUtil util = new HeapUtil();
        int[] piles = {10};
        int k = 5;
        // Operations:
        // 10 -> 10 - floor(10/2) = 5
        // 5 -> 5 - floor(5/2) = 3
        // 3 -> 3 - floor(3/2) = 2
        // 2 -> 2 - floor(2/2) = 1
        // 1 -> 1 - floor(1/2) = 1 (no change, so remains 1)
        // sum = 1
        int result = util.minStoneSum(piles, k);
        assertEquals(1, result, "Expected the total stones to be 1 after 5 operations.");
    }

    @Test
    void testAlreadySmallPiles() {
        HeapUtil util = new HeapUtil();
        int[] piles = {1, 1, 1};
        int k = 10; // Even many operations won't reduce them below 1 each time
        // Removing floor(1/2) = 0 from each 1, so they remain 1
        // sum = 3
        int result = util.minStoneSum(piles, k);
        assertEquals(3, result, "Expected the total stones to be 3 as 1 remains 1 after operation.");
    }

    @Test
    void testLargerRandom() {
        HeapUtil util = new HeapUtil();
        int[] piles = {10, 15, 20};
        int k = 3;
        // Rough check:
        // Largest = 20 -> 10, Largest = 15 -> 8, Largest = 10 -> 5
        // The array might transform as follows (depending on picks):
        // 1) pick 20 -> 10 (piles = 10,15,10)
        // 2) pick 15 -> 8 (piles = 10,8,10)
        // 3) pick 10 -> 5 (piles = 5,8,10) or maybe the other 10 -> 5
        // final sum = 5+8+10 = 23 or 5+8+5=18 (depending on picks).
        //
        // However, the algorithm always picks the top from the heap:
        // 1) 20 -> 10 (heap now has 10, 10, 15)
        // 2) 15 -> 8  (heap now has 10, 10, 8)
        // 3) 10 -> 5  (heap now has 10, 8, 5)
        // sum = 23
        //
        // Let's verify the final sum we get from the method:
        int result = util.minStoneSum(piles, k);
        // We can check the exact sum the method yields:
        // By the logic above, the final sum should be 23
        assertEquals(23, result, "Expected the total stones to be 23 after 3 operations.");
    }

    @Test
    public void testConnectSticksExample1() {
        HeapUtil util = new HeapUtil();
        int[] sticks = {2, 4, 3};
        // Explanation of cost:
        // First connect 2 and 3 at a cost of 5 -> new sticks {5, 4}
        // Then connect 5 and 4 at a cost of 9 -> total cost = 5 + 9 = 14
        assertEquals(14, util.connectSticks(sticks));
    }

    @Test
    public void testConnectSticksExample2() {
        HeapUtil util = new HeapUtil();
        int[] sticks = {1, 8, 3, 5};
        // Explanation of cost:
        // Connect 1 and 3 (cost 4), new sticks {4, 8, 5}
        // Connect 4 and 5 (cost 9), new sticks {9, 8}
        // Connect 9 and 8 (cost 17), total cost = 4 + 9 + 17 = 30
        assertEquals(30, util.connectSticks(sticks));
    }

    @Test
    public void testSingleStick() {
        HeapUtil util = new HeapUtil();
        int[] sticks = {5};
        // With only one stick, no cost required.
        assertEquals(0, util.connectSticks(sticks));
    }

    @Test
    public void testMultipleSticks() {
        HeapUtil util = new HeapUtil();
        int[] sticks = {1, 2, 3, 4, 5};
        // We'll rely on the algorithm to compute the minimum cost.
        // It's helpful to know the expected result to verify correctness.
        // Step by step might be:
        // 1) Combine (1,2)->3, cost=3, array -> [3,3,4,5]
        // 2) Combine (3,3)->6, cost=6, total=9, array-> [4,5,6]
        // 3) Combine (4,5)->9, cost=9, total=18, array-> [6,9]
        // 4) Combine (6,9)->15, cost=15, total=33
        assertEquals(33, util.connectSticks(sticks));
    }

    @Test
    public void testEmptyArray() {
        HeapUtil util = new HeapUtil();
        int[] sticks = {};
        // With no sticks, cost is 0
        assertEquals(0, util.connectSticks(sticks));
    }

    @Test
    public void testNullInput() {
        HeapUtil util = new HeapUtil();
        // Optional test to ensure method handles null gracefully
        assertEquals(0, util.connectSticks(null));
    }

}