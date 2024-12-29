package com.leetcode.algorithm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockSpannerTest {
    @Test
    void testExample1() {
        StockSpanner stockSpanner = new StockSpanner();
        assertEquals(1, stockSpanner.next(100)); // return 1
        assertEquals(1, stockSpanner.next(80));  // return 1
        assertEquals(1, stockSpanner.next(60));  // return 1
        assertEquals(2, stockSpanner.next(70));  // return 2
        assertEquals(1, stockSpanner.next(60));  // return 1
        assertEquals(4, stockSpanner.next(75));  // return 4
        assertEquals(6, stockSpanner.next(85));  // return 6
    }

    @Test
    void testIncreasingPrices() {
        StockSpanner stockSpanner = new StockSpanner();
        assertEquals(1, stockSpanner.next(10));
        assertEquals(2, stockSpanner.next(20));
        assertEquals(3, stockSpanner.next(30));
        assertEquals(4, stockSpanner.next(40));
        assertEquals(5, stockSpanner.next(50));
    }

    @Test
    void testDecreasingPrices() {
        StockSpanner stockSpanner = new StockSpanner();
        assertEquals(1, stockSpanner.next(50));
        assertEquals(1, stockSpanner.next(40));
        assertEquals(1, stockSpanner.next(30));
        assertEquals(1, stockSpanner.next(20));
        assertEquals(1, stockSpanner.next(10));
    }

    @Test
    void testMixedPrices() {
        StockSpanner stockSpanner = new StockSpanner();
        assertEquals(1, stockSpanner.next(31)); // Span: 1
        assertEquals(2, stockSpanner.next(41)); // Span: 2
        assertEquals(3, stockSpanner.next(48)); // Span: 3
        assertEquals(4, stockSpanner.next(59)); // Span: 4
        assertEquals(5, stockSpanner.next(79)); // Span: 5
        assertEquals(6, stockSpanner.next(80)); // Span: 6
        assertEquals(7, stockSpanner.next(94)); // Span: 7
    }

    @Test
    void testSinglePrice() {
        StockSpanner stockSpanner = new StockSpanner();
        assertEquals(1, stockSpanner.next(1000));
    }

    @Test
    void testAllSamePrices() {
        StockSpanner stockSpanner = new StockSpanner();
        assertEquals(1, stockSpanner.next(50));
        assertEquals(2, stockSpanner.next(50));
        assertEquals(3, stockSpanner.next(50));
        assertEquals(4, stockSpanner.next(50));
        assertEquals(5, stockSpanner.next(50));
    }

    @Test
    void testLargeInput() {
        StockSpanner stockSpanner = new StockSpanner();
        int n = 10000;
        for (int i = 1; i <= n; i++) {
            assertEquals(i, stockSpanner.next(i));
        }
    }

}