package com.leetcode.algorithm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TwoPointersTest {

    @Test
    public void testReverseEmptyString() {
        TwoPointers util = new TwoPointers();
        char[] input = {};
        char[] expected = {};
        util.reverseString(input);
        assertArrayEquals(expected, input, "Reversing an empty array should result in an empty array.");
    }

    @Test
    public void testReverseSingleCharacter() {
        TwoPointers util = new TwoPointers();
        char[] input = {'a'};
        char[] expected = {'a'};
        util.reverseString(input);
        assertArrayEquals(expected, input, "Reversing a single-character array should result in the same array.");
    }

    @Test
    public void testReverseEvenLengthString() {
        TwoPointers util = new TwoPointers();
        char[] input = {'h', 'e', 'l', 'l', 'o', '!', '!'};
        char[] expected = {'!', '!', 'o', 'l', 'l', 'e', 'h'};
        util.reverseString(input);
        assertArrayEquals(expected, input, "The array should be reversed correctly for even-length strings.");
    }

    @Test
    public void testReverseOddLengthString() {
        TwoPointers util = new TwoPointers();
        char[] input = {'J', 'u', 'n', 'i', 't'};
        char[] expected = {'t', 'i', 'n', 'u', 'J'};
        util.reverseString(input);
        assertArrayEquals(expected, input, "The array should be reversed correctly for odd-length strings.");
    }

    @Test
    public void testReverseStringWithSpaces() {
        TwoPointers util = new TwoPointers();
        char[] input = {' ', 'a', ' ', 'b', ' '};
        char[] expected = {' ', 'b', ' ', 'a', ' '};
        util.reverseString(input);
        assertArrayEquals(expected, input, "The array with spaces should be reversed correctly.");
    }

    @Test
    public void testReverseStringWithSpecialCharacters() {
        TwoPointers util = new TwoPointers();
        char[] input = {'@', '#', '$', '%', '^'};
        char[] expected = {'^', '%', '$', '#', '@'};
        util.reverseString(input);
        assertArrayEquals(expected, input, "The array with special characters should be reversed correctly.");
    }

    @Test
    public void testReverseAlreadyReversedString() {
        TwoPointers util = new TwoPointers();
        char[] input = {'d', 'l', 'r', 'o', 'w'};
        char[] expected = {'w', 'o', 'r', 'l', 'd'};
        util.reverseString(input);
        assertArrayEquals(expected, input, "Reversing an already reversed array should yield the original array.");
    }

    @Test
    public void testExample1() {
        TwoPointers util = new TwoPointers();

        int[] nums = {-4, -1, 0, 3, 10};
        int[] expected = {0, 1, 9, 16, 100};

        // Testing Two-Pointer Solution
        assertArrayEquals(expected, util.sortedSquares(nums),
                "Two-Pointer: The squared and sorted array should be [0, 1, 9, 16, 100]");

        // Testing Stream + Sort Solution
        assertArrayEquals(expected, util.sortedSquares(nums),
                "Stream + Sort: The squared and sorted array should be [0, 1, 9, 16, 100]");
    }

    @Test
    public void testExample2() {
        TwoPointers util = new TwoPointers();

        int[] nums = {-7, -3, 2, 3, 11};
        int[] expected = {4, 9, 9, 49, 121};

        // Testing Two-Pointer Solution
        assertArrayEquals(expected, util.sortedSquares(nums),
                "Two-Pointer: The squared and sorted array should be [4, 9, 9, 49, 121]");

        // Testing Stream + Sort Solution
        assertArrayEquals(expected, util.sortedSquares(nums),
                "Stream + Sort: The squared and sorted array should be [4, 9, 9, 49, 121]");
    }

    @Test
    public void testAllNegative() {
        TwoPointers util = new TwoPointers();

        int[] nums = {-5, -4, -3, -2, -1};
        int[] expected = {1, 4, 9, 16, 25};

        // Testing Two-Pointer Solution
        assertArrayEquals(expected, util.sortedSquares(nums),
                "Two-Pointer: The squared and sorted array should be [1, 4, 9, 16, 25]");

        // Testing Stream + Sort Solution
        assertArrayEquals(expected, util.sortedSquares(nums),
                "Stream + Sort: The squared and sorted array should be [1, 4, 9, 16, 25]");
    }

    @Test
    public void testSingleElement() {
        TwoPointers util = new TwoPointers();

        int[] nums = {-1};
        int[] expected = {1};

        // Testing Two-Pointer Solution
        assertArrayEquals(expected, util.sortedSquares(nums),
                "Two-Pointer: The squared and sorted array should be [1]");

        // Testing Stream + Sort Solution
        assertArrayEquals(expected, util.sortedSquares(nums),
                "Stream + Sort: The squared and sorted array should be [1]");
    }


    @Test
    public void testZeros() {
        TwoPointers util = new TwoPointers();

        int[] nums = {0, 0, 0};
        int[] expected = {0, 0, 0};

        // Testing Two-Pointer Solution
        assertArrayEquals(expected, util.sortedSquares(nums),
                "Two-Pointer: The squared and sorted array should be [0, 0, 0]");

        // Testing Stream + Sort Solution
        assertArrayEquals(expected, util.sortedSquares(nums),
                "Stream + Sort: The squared and sorted array should be [0, 0, 0]");
    }


    @Test
    public void testMixedValues() {
        TwoPointers util = new TwoPointers();

        int[] nums = {-3, -1, 0, 1, 2};
        int[] expected = {0, 1, 1, 4, 9};

        // Testing Two-Pointer Solution
        assertArrayEquals(expected, util.sortedSquares(nums),
                "Two-Pointer: The squared and sorted array should be [0, 1, 1, 4, 9]");

        // Testing Stream + Sort Solution
        assertArrayEquals(expected, util.sortedSquares(nums),
                "Stream + Sort: The squared and sorted array should be [0, 1, 1, 4, 9]");
    }


}