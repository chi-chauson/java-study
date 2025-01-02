package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphUtil {

    Map<Integer, List<Integer>> graph = new HashMap<>();
    boolean[] seen;

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];

            graph.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
            graph.computeIfAbsent(y, k -> new ArrayList<>()).add(x);

        }

        seen = new boolean[n];

        return dfs(source, destination);
    }

    private boolean dfs(int current, int destination) {
        if (current == destination) {
            return true;
        }

        if (!seen[current]) {
            seen[current] = true;
            for (int next : graph.getOrDefault(current, new ArrayList<>())) {
                if (dfs(next, destination)) {
                    return true;
                }
            }

        }

        return false;
    }

}
