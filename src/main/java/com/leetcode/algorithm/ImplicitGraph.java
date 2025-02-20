package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class LockPair {
    String node;
    int steps;
    LockPair(String node, int steps) {
        this.node = node;
        this.steps = steps;
    }
}

class FactorPair {
    String node;
    double ratio;
    FactorPair(String node, double ratio) {
        this.node = node;
        this.ratio = ratio;
    }
}

public class ImplicitGraph {
    Map<String, Map<String, Double>> graph = new HashMap<>();

    public int openLock(String[] deadends, String target) {
        Queue<LockPair> queue = new LinkedList<>();
        Set<String> seen = new HashSet<>();

        for (String s : deadends) {
            if (s.equals("0000")) {
                return -1;
            }
            seen.add(s);
        }

        queue.add(new LockPair("0000", 0));
        seen.add("0000");

        while (!queue.isEmpty()) {
            LockPair lockPair = queue.remove();
            String node = lockPair.node;
            int steps = lockPair.steps;
            if (node.equals(target)) {
                return steps;
            }

            for (String neighbor : neighbors(node)) {
                if (!seen.contains(neighbor)) {
                    seen.add(neighbor);
                    queue.add(new LockPair(neighbor, steps + 1));
                }
            }
        }

        return -1;
    }

    public List<String> neighbors(String node) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int num = (node.charAt(i) - '0');
            for (int change: new int[]{-1, 1}) {
                int x = (num + change + 10) % 10;
                ans.add(node.substring(0, i) + ("" + x) + node.substring(i + 1));
            }
        }

        return ans;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        for (int i = 0; i < equations.size(); i++) {
            String numerator = equations.get(i).get(0), denominator = equations.get(i).get(1);
            double val = values[i];

            if (!graph.containsKey(numerator)) {
                graph.put(numerator, new HashMap<>());
            }
            if (!graph.containsKey(denominator)) {
                graph.put(denominator, new HashMap<>());
            }

            graph.get(numerator).put(denominator, val);
            graph.get(denominator).put(numerator, 1 / val);
        }

        double[] ans = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            ans[i] = answerQuery(queries.get(i).get(0), queries.get(i).get(1));
        }

        return ans;
    }

    public double answerQuery(String start, String end) {
        // no info on this node
        if (!graph.containsKey(start)) {
            return -1;
        }

        Set<String> seen = new HashSet<>();
        Deque<FactorPair> stack = new LinkedList<>();
        seen.add(start);
        stack.push(new FactorPair(start, 1));
        while (!stack.isEmpty()) {
            FactorPair factorPair = stack.pop();
            String node = factorPair.node;
            double ratio = factorPair.ratio;
            if (node.equals(end)) {
                return ratio;
            }

            if (graph.containsKey(node)) {
                for (String neighbor: graph.get(node).keySet()) {
                    if (!seen.contains(neighbor)) {
                        seen.add(neighbor);
                        stack.push(new FactorPair(neighbor, ratio * graph.get(node).get(neighbor)));
                    }
                }
            }
        }

        return -1;
    }

    public int minMutation(String startGene, String endGene, String[] bank) {
        // Quick checks
        if (startGene.equals(endGene)) {
            return 0;
        }

        // Convert bank to a set for O(1) containment checks
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));

        // If endGene not in bank and different from startGene, no valid path
        if (!bankSet.contains(endGene)) {
            return -1;
        }

        // Possible gene characters
        char[] chars = new char[] {'A', 'C', 'G', 'T'};

        // Queue for BFS: each element is (currentGene, mutationsSoFar)
        Queue<String> queue = new LinkedList<>();
        queue.offer(startGene);

        // Visited set to avoid cycles
        Set<String> visited = new HashSet<>();
        visited.add(startGene);

        // Number of mutations so far
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            // We process each level fully, then increment level (the BFS layer)
            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                // If we have reached endGene, return the current level
                if (current.equals(endGene)) {
                    return level;
                }

                // Generate all possible one-character mutations
                for (int pos = 0; pos < current.length(); pos++) {
                    for (char c : chars) {
                        // Only mutate if character is different
                        if (current.charAt(pos) == c) continue;

                        // Build a new gene with this single-character change
                        String mutated = mutate(current, pos, c);

                        // If it's in bank and not visited, add to queue
                        if (bankSet.contains(mutated) && !visited.contains(mutated)) {
                            visited.add(mutated);
                            queue.offer(mutated);
                        }
                    }
                }
            }
            // After processing one BFS layer, we have increased the mutation count by 1
            level++;
        }

        // If we exit the loop, no path to endGene was found
        return -1;
    }

    // Helper method to replace a character at a given position
    private String mutate(String gene, int position, char newChar) {
        char[] arr = gene.toCharArray();
        arr[position] = newChar;
        return new String(arr);
    }
}
