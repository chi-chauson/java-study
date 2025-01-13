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

    /**
     * Determines if there is a valid path between source and destination in a graph.
     *
     * @param n          the number of nodes in the graph
     * @param edges      the list of edges representing the graph
     * @param source     the starting node
     * @param destination the target node
     * @return true if a valid path exists, false otherwise
     */
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

        // Initialize a visited array to keep track of visited cells
        boolean[][] visited = new boolean[rows][cols];

        // Iterate through each cell in the grid
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // If the cell is land ('1') and not visited, perform DFS
                if (grid[row][col] == '1' && !visited[row][col]) {
                    numberOfIslands++;
                    dfsNumIslands(grid, row, col, rows, cols, visited);
                }
            }
        }

        return numberOfIslands;
    }

    /**
     * Depth-First Search to mark all connected land cells starting from (row, col).
     *
     * @param grid    the 2D grid
     * @param row     current row index
     * @param col     current column index
     * @param rows    total number of rows in the grid
     * @param cols    total number of columns in the grid
     * @param visited a 2D boolean array to track visited cells
     */
    private void dfsNumIslands(char[][] grid, int row, int col, int rows, int cols, boolean[][] visited) {
        // Check for out-of-bounds, water cells, or already visited cells
        if (row < 0 || row >= rows || col < 0 || col >= cols ||
                grid[row][col] == '0' || visited[row][col]) {
            return;
        }

        // Mark the current cell as visited
        visited[row][col] = true;

        // Explore all four adjacent directions
        dfsNumIslands(grid, row - 1, col, rows, cols, visited); // Up
        dfsNumIslands(grid, row + 1, col, rows, cols, visited); // Down
        dfsNumIslands(grid, row, col - 1, rows, cols, visited); // Left
        dfsNumIslands(grid, row, col + 1, rows, cols, visited); // Right
    }

    /**
     * Counts the number of connected components in an undirected graph.
     *
     * @param n     the number of nodes in the graph
     * @param edges the list of edges representing the graph
     * @return the number of connected components
     */
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

        // Initialize a visited array to keep track of visited cells
        boolean[][] visited = new boolean[rows][cols];

        // Iterate through each cell in the grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // If the cell is land and not visited, perform DFS
                if (grid[r][c] == 1 && !visited[r][c]) {
                    int area = dfsMaxAreaOfIsland(grid, r, c, rows, cols, visited);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }

    /**
     * Depth-First Search to calculate the area of an island.
     *
     * @param grid    the binary matrix representing the grid
     * @param r       current row
     * @param c       current column
     * @param rows    total number of rows in the grid
     * @param cols    total number of columns in the grid
     * @param visited a 2D boolean array to track visited cells
     * @return the area of the island
     */
    private int dfsMaxAreaOfIsland(int[][] grid, int r, int c, int rows, int cols, boolean[][] visited) {
        // Check for out of bounds, water cells, or already visited cells
        if (r < 0 || c < 0 || r >= rows || c >= cols ||
                grid[r][c] == 0 || visited[r][c]) {
            return 0;
        }

        // Mark the cell as visited
        visited[r][c] = true;

        int area = 1; // Current cell

        // Explore all four directions
        area += dfsMaxAreaOfIsland(grid, r - 1, c, rows, cols, visited); // Up
        area += dfsMaxAreaOfIsland(grid, r + 1, c, rows, cols, visited); // Down
        area += dfsMaxAreaOfIsland(grid, r, c - 1, rows, cols, visited); // Left
        area += dfsMaxAreaOfIsland(grid, r, c + 1, rows, cols, visited); // Right

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

        // Start DFS from node 0 if it's not restricted
        if (!restrictedSet.contains(0)) {
            stack.push(0);
            visited[0] = true;
        }

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

    /**
     * Finds the length of the shortest clear path in a binary matrix from the top-left
     * cell (0, 0) to the bottom-right cell (n-1, n-1). A clear path consists of cells
     * with value 0, and movement is allowed in 8 directions.
     *
     * @param grid the n x n binary matrix
     * @return the length of the shortest clear path, or -1 if no such path exists
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        // If the start or end cell is blocked, return -1
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
            return -1;
        }

        // Directions: up, down, left, right, and the four diagonals
        int[][] directions = {
                {-1,  0}, // Up
                { 1,  0}, // Down
                { 0, -1}, // Left
                { 0,  1}, // Right
                {-1, -1}, // Up-Left
                {-1,  1}, // Up-Right
                { 1, -1}, // Down-Left
                { 1,  1}  // Down-Right
        };

        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();

        // Start BFS from (0,0) with path length 1
        queue.offer(new int[]{0, 0, 1});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int pathLength = current[2];

            // If we've reached the destination, return the path length
            if (row == n - 1 && col == n - 1) {
                return pathLength;
            }

            // Explore all 8 directions
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check boundaries and if the cell is unvisited and clear
                if (isValid(newRow, newCol, n) && !visited[newRow][newCol] && grid[newRow][newCol] == 0) {
                    queue.offer(new int[]{newRow, newCol, pathLength + 1});
                    visited[newRow][newCol] = true;
                }
            }
        }

        // If destination is not reachable
        return -1;
    }

    /**
     * Checks if the given cell coordinates are within the grid boundaries.
     *
     * @param row the row index
     * @param col the column index
     * @param n the size of the grid
     * @return true if the cell is within bounds, false otherwise
     */
    private boolean isValid(int row, int col, int n) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }
}



