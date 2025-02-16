package com.leetcode.algorithm;

import java.util.ArrayList;
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
}
