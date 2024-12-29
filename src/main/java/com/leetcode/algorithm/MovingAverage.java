package com.leetcode.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

public class MovingAverage {
    private int windowSize;
    private Deque<Integer> queue = new ArrayDeque();
    private int sum = 0;

    public MovingAverage(int size) {
        this.windowSize = size;
    }

    public double next(int val) {
        queue.offer(val);
        if (queue.size() > windowSize) {
            sum = sum - queue.poll();
        }
        sum = sum + val;

//        int size = queue.size() < windowSize? queue.size(): windowSize;

        return (double) sum / queue.size();

    }
}
