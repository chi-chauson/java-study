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

    /**
     * Finds the number of steps in the shortest path from the entrance
     * to the nearest exit in the maze.
     *
     * @param maze      An m x n matrix of '.' and '+' characters.
     * @param entrance  An array of two integers [entranceRow, entranceCol].
     * @return          The shortest number of steps to an exit, or -1 if none exists.
     */
    public static int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;

        // Directions: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Queue will hold {row, col, distance}
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{entrance[0], entrance[1], 0});

        // Mark entrance as visited by replacing '.' with '+'
        // or we can maintain a separate visited array
        maze[entrance[0]][entrance[1]] = '+';

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int distance = current[2];

            // Explore neighbors
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check boundaries
                if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n) {
                    continue;
                }
                // If it's a wall or visited, skip
                if (maze[newRow][newCol] == '+') {
                    continue;
                }

                // If we're on the border, it's an exit (but not the entrance cell)
                if (isBorderCell(newRow, newCol, m, n)) {
                    return distance + 1;
                }

                // Mark cell as visited and add to queue
                maze[newRow][newCol] = '+';
                queue.offer(new int[]{newRow, newCol, distance + 1});
            }
        }

        // No exit found
        return -1;
    }

    /**
     * Helper method to check if a given cell is on the border of the maze.
     */
    private static boolean isBorderCell(int row, int col, int m, int n) {
        return row == 0 || row == m - 1 || col == 0 || col == n - 1;
    }

    /**
     * Returns the least number of dice rolls required to reach square n^2
     * on the given boustrophedon-labeled Snakes and Ladders board.
     * If it is not possible to reach the last square, returns -1.
     *
     * @param board An n x n matrix, where board[i][j] = -1 for no snake/ladder,
     *              or board[i][j] in [1, n^2] as the destination of a snake or ladder.
     * @return Minimum number of dice rolls to reach square n^2, or -1 if impossible.
     */
    public static int snakesAndLadders(int[][] board) {
        int n = board.length;
        // Flatten the board into a 1D array finalPos where finalPos[square]
        // tells where you actually land if you move to 'square'.
        int[] finalPos = new int[n * n + 1];  // 1-based indexing (square 1..n^2)

        // Fill finalPos by default to each index (i.e. no snake/ladder).
        for (int i = 1; i <= n * n; i++) {
            finalPos[i] = i;
        }

        // Populate finalPos according to the board.
        // board[0][0] is top-left, board[n-1][0] is bottom-left.
        // We must compute the 'label' of each board[i][j].
        // r = (n-1 - i) gives the row index from the bottom (0-based).
        // If r is even, we go left to right; if odd, we go right to left.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int r = n - 1 - i; // row from the bottom
                int label;
                if (r % 2 == 0) {
                    // left to right
                    label = r * n + (j + 1);
                } else {
                    // right to left
                    label = r * n + (n - j);
                }
                // If there's a ladder or snake at board[i][j],
                // set finalPos[label] to its destination
                if (board[i][j] != -1) {
                    finalPos[label] = board[i][j];
                }
            }
        }

        // We now perform a BFS from square 1 to square n^2.
        boolean[] visited = new boolean[n * n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;

        int moves = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // We'll explore all nodes at the current BFS 'level',
            // and increment 'moves' after processing them.
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                // If we've reached the final square, return the number of moves.
                if (curr == n * n) {
                    return moves;
                }

                // Try the next 6 squares (dice rolls).
                for (int roll = 1; roll <= 6; roll++) {
                    int next = curr + roll;
                    if (next <= n * n) {
                        int nextPos = finalPos[next];
                        if (!visited[nextPos]) {
                            visited[nextPos] = true;
                            queue.offer(nextPos);
                        }
                    }
                }
            }
            moves++;
        }

        // If we exhaust the BFS without reaching the last square,
        // it is impossible to reach n^2.
        return -1;
    }
}



