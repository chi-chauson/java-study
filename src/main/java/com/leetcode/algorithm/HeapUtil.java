package com.leetcode.algorithm;

import java.util.Collections;
import java.util.PriorityQueue;

public class HeapUtil {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int p: piles) {
            maxHeap.offer(p);
        }

        for (int i = 0; i < k; i++) {
            int max = maxHeap.poll();
            maxHeap.offer(max - Math.floorDiv(max, 2));
        }

        return maxHeap.stream().reduce(0, Integer::sum);
    }

    public int connectSticks(int[] sticks) {
        // Edge case: if there is only one (or zero) stick, cost is 0
        if (sticks == null || sticks.length <= 1) {
            return 0;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int s : sticks) {
            minHeap.offer(s);
        }

        int totalCost = 0;
        while (minHeap.size() > 1)
        {
            int x = minHeap.poll();
            int y = minHeap.poll();
            int cost = x + y;
            totalCost += cost;
            minHeap.offer(cost);
        }

        return totalCost;
    }

}
