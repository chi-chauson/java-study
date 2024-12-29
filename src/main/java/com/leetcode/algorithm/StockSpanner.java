package com.leetcode.algorithm;

import java.util.Deque;
import java.util.LinkedList;

public class StockSpanner {
    private Deque<int[]> stack = new LinkedList<>();

    public StockSpanner() {
    }

    /**
     * Returns the span of the stock's price given today's price.
     *
     * @param price The current day's stock price.
     * @return The span of the stock's price.
     */
    public int next(int price) {
        int span = 1;
        // Pop elements from the stack while the current price is higher or equal
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1];
        }
        // Push the current price and its span onto the stack
        stack.push(new int[]{price, span});
        return span;
    }
}
