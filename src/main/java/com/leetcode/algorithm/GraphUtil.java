package com.leetcode.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class GraphUtil {

    private Map<Integer, List<Integer>> graph = new HashMap<>();
    private boolean[] seen;

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        buildGraphEdgesMap(n, edges);
        return dfsValidPaths(source, destination);
    }

    private boolean dfsValidPaths(int current, int destination) {
        if (current == destination) {
            return true;
        }

        if (!seen[current]) {
            seen[current] = true;
            for (int next : graph.getOrDefault(current, new ArrayList<>())) {
                if (dfsValidPaths(next, destination)) {
                    return true;
                }
            }

        }

        return false;
    }

    /**
     * Returns the number of islands in the given 2D binary grid.
     *
     * @param grid a 2D array representing the map where '1's are land and '0's are water
     * @return the number of distinct islands
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numberOfIslands = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // Iterate through each cell in the grid
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // If the cell is land ('1'), perform DFS to mark the entire island
                if (grid[row][col] == '1') {
                    numberOfIslands++;
                    dfsNumIslands(grid, row, col, rows, cols);
                }
            }
        }

        return numberOfIslands;
    }

    /**
     * Depth-First Search to mark all connected land cells starting from (row, col).
     *
     * @param grid the 2D grid
     * @param row  current row index
     * @param col  current column index
     * @param rows total number of rows in the grid
     * @param cols total number of columns in the grid
     */
    private void dfsNumIslands(char[][] grid, int row, int col, int rows, int cols) {
        // Check for out-of-bounds or if the cell is water ('0')
        if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] == '0') {
            return;
        }

        // Mark the current cell as visited by setting it to '0'
        grid[row][col] = '0';

        // Explore all four adjacent directions
        dfsNumIslands(grid, row - 1, col, rows, cols); // Up
        dfsNumIslands(grid, row + 1, col, rows, cols); // Down
        dfsNumIslands(grid, row, col - 1, rows, cols); // Left
        dfsNumIslands(grid, row, col + 1, rows, cols); // Right
    }

    public int countComponents(int n, int[][] edges) {
        buildGraphEdgesMap(n, edges);

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!seen[i]) {
                count++;
                seen[i] = true;
                dfsCountComponents(i);
            }
        }
        return count;
    }

    private void dfsCountComponents(int node) {
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!seen[neighbor]) {
                seen[neighbor] = true;
                dfsCountComponents(neighbor);
            }
        }
    }

    private void buildGraphEdgesMap(int n, int[][] edges) {
        graph.clear();
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];

            graph.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
            graph.computeIfAbsent(y, k -> new ArrayList<>()).add(x);

        }

        seen = new boolean[n];
    }


    /**
     * Returns the maximum area of an island in the given binary grid.
     * An island is a group of 1's connected 4-directionally.
     *
     * @param grid the binary matrix representing the grid
     * @return the maximum area of an island, or 0 if there is no island
     */
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int maxArea = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // Iterate through each cell in the grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // If the cell is land, perform DFS to find the area
                if (grid[r][c] == 1) {
                    int area = dfs(grid, r, c, rows, cols);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }

    /**
     * Depth-First Search to calculate the area of an island.
     *
     * @param grid the binary matrix representing the grid
     * @param r    current row
     * @param c    current column
     * @param rows total number of rows in the grid
     * @param cols total number of columns in the grid
     * @return the area of the island
     */
    private int dfs(int[][] grid, int r, int c, int rows, int cols) {
        // Check for out of bounds or water cells
        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] == 0) {
            return 0;
        }

        // Mark the cell as visited by setting it to 0
        grid[r][c] = 0;

        int area = 1; // Current cell

        // Explore all four directions
        area += dfs(grid, r - 1, c, rows, cols); // Up
        area += dfs(grid, r + 1, c, rows, cols); // Down
        area += dfs(grid, r, c - 1, rows, cols); // Left
        area += dfs(grid, r, c + 1, rows, cols); // Right

        return area;
    }

    /**
     * Returns the maximum number of nodes that can be reached from node 0 without visiting any restricted nodes.
     *
     * @param n          the number of nodes in the tree
     * @param edges      the list of edges representing the tree
     * @param restricted the list of restricted nodes
     * @return the number of reachable nodes from node 0 without visiting restricted nodes
     */
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        // Create adjacency list using HashMap
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adj.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        // Convert restricted array to a set for O(1) lookups
        Set<Integer> restrictedSet = new HashSet<>();
        for (int node : restricted) {
            restrictedSet.add(node);
        }

        // Iterative DFS using a Deque
        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        int count = 0;

        // Start DFS from node 0
        stack.push(0);
        visited[0] = true;

        while (!stack.isEmpty()) {
            int current = stack.pop();
            count++;

            for (int neighbor : adj.get(current)) {
                if (!visited[neighbor] && !restrictedSet.contains(neighbor)) {
                    stack.push(neighbor);
                    visited[neighbor] = true;
                }
            }
        }

        return count;
    }
}



