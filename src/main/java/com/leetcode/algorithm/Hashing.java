package com.leetcode.algorithm;

import com.netflix.spectator.atlas.impl.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Hashing {
    public boolean checkIfPangram(String sentence) {
        if (sentence == null || sentence.length() < 26) {
            return false;
        }

        Set<Character> seenLetters = new HashSet<>();
        for (char c : sentence.toCharArray()) {
            seenLetters.add(c);
        }

        return seenLetters.size() == 26;
    }

    public int missingNumber(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            if (!numSet.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    public int countElements(int[] arr) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : arr) {
            numSet.add(num);
        }

        int count = 0;
        for (int num : arr) {
            if (numSet.contains(num + 1)) {
                count++;
            }
        }

        return count;
    }

    public List<List<Integer>> findWinners(int[][] matches) {
        // Map to keep track of player -> number of losses
        HashMap<Integer, Integer> lossCountMap = new HashMap<>();

        // Populate the map with all players and count their losses
        for (int[] match : matches) {
            int winner = match[0];
            int loser = match[1];

            // Ensure the winner is in the map, even if with 0 losses
            lossCountMap.putIfAbsent(winner, 0);

            // For the loser, increment the loss count
            lossCountMap.put(loser, lossCountMap.getOrDefault(loser, 0) + 1);
        }

        // Prepare the lists for players with 0 and 1 losses
        List<Integer> noLosses = new ArrayList<>();
        List<Integer> oneLoss = new ArrayList<>();

        // Separate players by their number of losses
        for (int player : lossCountMap.keySet()) {
            int losses = lossCountMap.get(player);
            if (losses == 0) {
                noLosses.add(player);
            } else if (losses == 1) {
                oneLoss.add(player);
            }
        }

        // Sort the results in ascending order
        Collections.sort(noLosses);
        Collections.sort(oneLoss);

        // Build final answer
        List<List<Integer>> answer = new ArrayList<>();
        answer.add(noLosses);
        answer.add(oneLoss);

        return answer;
    }

    public int largestUniqueNumber(int[] nums) {
        // Map each number to its frequency
        Map<Integer, Integer> freqMap = new HashMap<>();

        // Count how many times each number appears
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Track the largest number that appears exactly once
        int largestUnique = -1;
        for (int num : freqMap.keySet()) {
            if (freqMap.get(num) == 1 && num > largestUnique) {
                largestUnique = num;
            }
        }

        return largestUnique;
    }

    public int maxNumberOfBalloons(String text) {
        // Frequency map for the characters in text
        Map<Character, Integer> freqMap = new HashMap<>();

        // Count characters in the input string
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // The word "balloon" consists of:
        // b -> 1
        // a -> 1
        // l -> 2
        // o -> 2
        // n -> 1

        // For the count of "balloon",
        // We need freq('b') / 1,
        // freq('a') / 1,
        // freq('l') / 2,
        // freq('o') / 2,
        // freq('n') / 1.

        int countB = freqMap.getOrDefault('b', 0);
        int countA = freqMap.getOrDefault('a', 0);
        int countL = freqMap.getOrDefault('l', 0);
        int countO = freqMap.getOrDefault('o', 0);
        int countN = freqMap.getOrDefault('n', 0);

        // Compute how many times "balloon" can be formed
        // The limiting factor is the minimum among these.
        int maxBalloons = Math.min(
                Math.min(countB, countA),
                Math.min(countL / 2, Math.min(countO / 2, countN))
        );

        return maxBalloons;
    }

    /**
     * Finds the maximum length of a contiguous subarray
     * with an equal number of 0 and 1 in nums.
     *
     * @param nums binary array (only 0 or 1)
     * @return maximum length of subarray with equal # of 0 and 1
     */
    public int findMaxLength(int[] nums) {
        // Key = running sum, Value = first index where that sum occurs
        Map<Integer, Integer> map = new HashMap<>();
        // Initialize the map with sum = 0 at index = -1
        map.put(0, -1);

        int runningSum = 0;
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            // Consider 0 as -1, 1 as +1
            runningSum += (nums[i] == 0) ? -1 : 1;

            // If runningSum is already in the map, update maxLength
            if (map.containsKey(runningSum)) {
                int firstIndex = map.get(runningSum);
                int length = i - firstIndex;  // distance between previous index and current
                if (length > maxLength) {
                    maxLength = length;
                }
            } else {
                // Otherwise store this runningSum with the current index
                map.put(runningSum, i);
            }
        }

        return maxLength;
    }
}
