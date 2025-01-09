package com.leetcode.algorithm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphUtilTest {
    private final GraphUtil graphUtil = new GraphUtil();

    @Test
    void testTriangleGraphPathExists() {
        int n = 3;
        int[][] edges = {{0,1}, {1,2}, {2,0}};
        int source = 0;
        int destination = 2;
        assertTrue(graphUtil.validPath(n, edges, source, destination));
    }

    @Test
    void testDisconnectedGraphPathDoesNotExist() {
        int n = 6;
        int[][] edges = {{0,1}, {0,2}, {3,5}, {5,4}, {4,3}};
        int source = 0;
        int destination = 5;
        assertFalse(graphUtil.validPath(n, edges, source, destination));
    }

    @Test
    void testSingleVertexSameSourceDestination() {
        int n = 1;
        int[][] edges = {};
        int source = 0;
        int destination = 0;
        assertTrue(graphUtil.validPath(n, edges, source, destination));
    }

    @Test
    void testSingleVertexDifferentSourceDestination() {
        int n = 2;
        int[][] edges = {};
        int source = 0;
        int destination = 1;
        assertFalse(graphUtil.validPath(n, edges, source, destination));
    }

    @Test
    void testLargeConnectedGraphPathExists() {
        int n = 1000;
        int[][] edges = new int[n-1][2];
        for(int i=0;i<n-1;i++) {
            edges[i][0] = i;
            edges[i][1] = i+1;
        }
        int source = 0;
        int destination = n-1;
        assertTrue(graphUtil.validPath(n, edges, source, destination));
    }

    @Test
    void testLargeDisconnectedGraphPathDoesNotExist() {
        int n = 1000;
        int[][] edges = new int[n-2][2];
        for(int i=0;i<n-2;i++) {
            edges[i][0] = i;
            edges[i][1] = i+1;
        }
        int source = 0;
        int destination = n-1;
        assertFalse(graphUtil.validPath(n, edges, source, destination));
    }

    @Test
    void testGraphWithNoEdgesSourceEqualsDestination() {
        int n = 5;
        int[][] edges = {};
        int source = 3;
        int destination = 3;
        assertTrue(graphUtil.validPath(n, edges, source, destination));
    }

    @Test
    void testGraphWithNoEdgesSourceNotEqualsDestination() {
        int n = 5;
        int[][] edges = {};
        int source = 1;
        int destination = 4;
        assertFalse(graphUtil.validPath(n, edges, source, destination));
    }

    @Test
    void testPathExistsDirectEdge() {
        int n = 4;
        int[][] edges = {{0,1}, {1,2}, {2,3}};
        int source = 1;
        int destination = 2;
        assertTrue(graphUtil.validPath(n, edges, source, destination));
    }

    @Test
    void testPathExistsIndirectEdge() {
        int n = 5;
        int[][] edges = {{0,1}, {1,2}, {2,3}, {3,4}};
        int source = 0;
        int destination = 4;
        assertTrue(graphUtil.validPath(n, edges, source, destination));
    }

    @Test
    void testPathDoesNotExistBetweenComponents() {
        int n = 7;
        int[][] edges = {
                {0,1}, {1,2}, {2,3},
                {4,5}, {5,6}
        };
        int source = 3;
        int destination = 5;
        assertFalse(graphUtil.validPath(n, edges, source, destination));
    }

    @Test
    void testGraphWithCyclePathExists() {
        int n = 4;
        int[][] edges = {
                {0,1}, {1,2}, {2,0}, {2,3}
        };
        int source = 3;
        int destination = 0;
        assertTrue(graphUtil.validPath(n, edges, source, destination));
    }

    @Test
    void testSingleIsland_AllLand() {
        char[][] grid = {
                {'1', '1', '1'},
                {'1', '1', '1'},
                {'1', '1', '1'}
        };
        assertEquals(1, graphUtil.numIslands(grid), "All cells are land, should be one island.");
    }

    @Test
    void testNoIslands_AllWater() {
        char[][] grid = {
                {'0', '0', '0'},
                {'0', '0', '0'},
                {'0', '0', '0'}
        };
        assertEquals(0, graphUtil.numIslands(grid), "All cells are water, should be zero islands.");
    }

    @Test
    void testMultipleIslands_DisconnectedLands() {
        char[][] grid = {
                {'1', '0', '1'},
                {'0', '1', '0'},
                {'1', '0', '1'}
        };
        assertEquals(5, graphUtil.numIslands(grid), "Five separate land cells, each should be its own island.");
    }

    @Test
    void testExampleOne_SingleLargeIsland() {
        char[][] grid = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        assertEquals(1, graphUtil.numIslands(grid), "Example 1: Should have one island.");
    }

    @Test
    void testExampleTwo_MultipleIslands() {
        char[][] grid = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        assertEquals(3, graphUtil.numIslands(grid), "Example 2: Should have three islands.");
    }

    @Test
    void testSingleCell_Land() {
        char[][] grid = {
                {'1'}
        };
        assertEquals(1, graphUtil.numIslands(grid), "Single land cell should count as one island.");
    }

    @Test
    void testSingleCell_Water() {
        char[][] grid = {
                {'0'}
        };
        assertEquals(0, graphUtil.numIslands(grid), "Single water cell should count as zero islands.");
    }

    @Test
    void testNonRectangularGrid() {
        // Although constraints specify a rectangular grid, testing non-rectangular for robustness
        char[][] grid = {
                {'1', '1', '0'},
                {'1', '0'},
                {'1', '1', '1', '0'}
        };
        // This test might throw an exception due to irregular grid, depending on implementation
        // Here, we skip it as per constraints
    }

    @Test
    void testLargeGrid_SparseIslands() {
        char[][] grid = new char[300][300];
        for (int i = 0; i < 300; i++) {
            for (int j = 0; j < 300; j++) {
                grid[i][j] = '0';
            }
        }
        // Place some islands
        grid[0][0] = '1';
        grid[299][299] = '1';
        grid[150][150] = '1';
        grid[150][151] = '1';
        grid[150][152] = '1';
        assertEquals(3, graphUtil.numIslands(grid), "Large grid with four separate islands.");
    }

    @Test
    void testDisconnectedGraph() {
        int n = 5;
        int[][] edges = { {0, 1}, {1, 2}, {3, 4} };
        int expected = 2;
        assertEquals(expected, graphUtil.countComponents(n, edges), "Disconnected graph should have 2 connected components.");
    }

    @Test
    void testFullyConnectedGraph() {
        int n = 5;
        int[][] edges = { {0, 1}, {1, 2}, {2, 3}, {3, 4} };
        int expected = 1;
        assertEquals(expected, graphUtil.countComponents(n, edges), "Fully connected graph should have 1 connected component.");
    }

    @Test
    void testSingleNode() {
        int n = 1;
        int[][] edges = {};
        int expected = 1;
        assertEquals(expected, graphUtil.countComponents(n, edges), "Single node graph should have 1 connected component.");
    }

    @Test
    void testNoEdgesMultipleNodes() {
        int n = 4;
        int[][] edges = {};
        int expected = 4;
        assertEquals(expected, graphUtil.countComponents(n, edges), "Graph with no edges should have n connected components.");
    }

    @Test
    void testMultipleComponents() {
        int n = 10;
        int[][] edges = { {0,1}, {2,3}, {4,5}, {6,7}, {8,9} };
        int expected = 5;
        assertEquals(expected, graphUtil.countComponents(n, edges), "Graph should have 5 connected components.");
    }

    @Test
    void testComplexGraph() {
        int n = 7;
        int[][] edges = { {0,1}, {1,2}, {2,0}, {3,4}, {5,6} };
        int expected = 3;
        assertEquals(expected, graphUtil.countComponents(n, edges), "Complex graph should have 3 connected components.");
    }

    @Test
    void shouldReturnMaximumIslandAreaForMultipleIslands() {
        int[][] grid = {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };
        int expected = 6;
        assertEquals(expected, graphUtil.maxAreaOfIsland(grid));
    }

    @Test
    void shouldReturnZeroWhenNoIslandsExist() {
        int[][] grid = {
                {0,0,0,0,0,0,0,0}
        };
        int expected = 0;
        assertEquals(expected, graphUtil.maxAreaOfIsland(grid));
    }

    @Test
    void shouldHandleSingleCellIsland() {
        int[][] grid = {
                {0, 1, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        int expected = 1;
        assertEquals(expected, graphUtil.maxAreaOfIsland(grid));
    }

    @Test
    void shouldHandleEntireGridAsOneIsland() {
        int[][] grid = {
                {1,1,1},
                {1,1,1},
                {1,1,1}
        };
        int expected = 9;
        assertEquals(expected, graphUtil.maxAreaOfIsland(grid));
    }

    @Test
    void shouldHandleNonRectangularIslands() {
        int[][] grid = {
                {1,0,1,1,0},
                {1,1,0,1,0},
                {0,1,1,0,1},
                {0,0,0,1,1}
        };
        int expected = 5;
        assertEquals(expected, graphUtil.maxAreaOfIsland(grid));
    }

    @Test
    void shouldHandleEmptyGrid() {
        int[][] grid = {};
        int expected = 0;
        assertEquals(expected, graphUtil.maxAreaOfIsland(grid));
    }

    @Test
    void shouldHandleGridWithAllWater() {
        int[][] grid = {
                {0,0,0},
                {0,0,0},
                {0,0,0}
        };
        int expected = 0;
        assertEquals(expected, graphUtil.maxAreaOfIsland(grid));
    }

    @Test
    void shouldHandleLargeGridWithMixedIslands() {
        int[][] grid = {
                {1, 1, 0, 0, 0, 1, 1, 1},
                {1, 1, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 1, 1},
                {1, 0, 1, 0, 1, 1, 1, 0},
                {1, 1, 1, 0, 0, 0, 0, 1}
        };
        int expected = 7;
        assertEquals(expected, graphUtil.maxAreaOfIsland(grid));
    }

    @Test
    public void testBasicExampleOne() {
        int n = 7;
        int[][] edges = {
                {0,1},{1,2},{3,1},{4,0},{0,5},{5,6}
        };
        int[] restricted = {4,5};
        int expected = 4; // Nodes [0,1,2,3]
        assertEquals(expected, graphUtil.reachableNodes(n, edges, restricted));
    }

    @Test
    public void testBasicExampleTwo() {
        int n = 7;
        int[][] edges = {
                {0,1},{0,2},{0,5},{0,4},{3,2},{6,5}
        };
        int[] restricted = {4,2,1};
        int expected = 3; // Nodes [0,5,6]
        assertEquals(expected, graphUtil.reachableNodes(n, edges, restricted));
    }

    @Test
    public void testNoRestrictedNodes() {
        int n = 5;
        int[][] edges = {
                {0,1},{0,2},{1,3},{1,4}
        };
        int[] restricted = {};
        int expected = 5; // All nodes are reachable
        assertEquals(expected, graphUtil.reachableNodes(n, edges, restricted));
    }

    @Test
    public void testAllExceptZeroRestricted() {
        int n = 3;
        int[][] edges = {
                {0,1},{0,2}
        };
        int[] restricted = {1,2};
        int expected = 1; // Only node 0 is reachable
        assertEquals(expected, graphUtil.reachableNodes(n, edges, restricted));
    }

    @Test
    public void shouldHandleLargeTreeWithSparseRestrictions() {
        int n = 100000;
        int[][] edges = new int[n-1][2];
        for(int i = 1; i < n; i++) {
            edges[i-1][0] = i-1;
            edges[i-1][1] = i;
        }
        int[] restricted = {50000, 75000, 99999};
        int expected = 50000; // Nodes 0 to 49999 are reachable
        assertEquals(expected, graphUtil.reachableNodes(n, edges, restricted));
    }

    @Test
    public void testSinglePathWithMultipleRestrictions() {
        int n = 10;
        int[][] edges = {
                {0,1},{1,2},{2,3},{3,4},{4,5},{5,6},{6,7},{7,8},{8,9}
        };
        int[] restricted = {3,6,9};
        int expected = 3; // Nodes [0,1,2]
        assertEquals(expected, graphUtil.reachableNodes(n, edges, restricted));
    }

    @Test
    public void testDisconnectedAfterRestrictions() {
        // Constructing a small tree for clarity
        int n = 7;
        int[][] edges = {
                {0,1}, {1,2}, {1,3}, {1,4}, {4,5}, {5,6}
        };
        int[] restricted = {4, 6}; // Restrict nodes 4 and 6
        int expected = 4; // Nodes [0,1,2,3] are reachable, total 4 nodes
        assertEquals(expected, graphUtil.reachableNodes(n, edges, restricted));
    }

    @Test
    public void shouldHandleLargeGridWithMixedReachableIslands() {
        // Constructing a large tree with mixed restricted nodes
        int n = 100000;
        int[][] edges = new int[n-1][2];
        for(int i = 1; i < n; i++) {
            edges[i-1][0] = i-1;
            edges[i-1][1] = i;
        }
        // Restrict every 10000th node
        List<Integer> restrictedList = new ArrayList<>();
        for(int i = 10000; i < n; i += 10000) {
            restrictedList.add(i);
        }
        int[] restricted = restrictedList.stream().mapToInt(Integer::intValue).toArray();
        // The expected reachable nodes would be up to the first restricted node
        // Since the tree is a straight line, the first restricted node is 10000
        int expected = 10000; // Nodes 0 to 9999 are reachable
        assertEquals(expected, graphUtil.reachableNodes(n, edges, restricted));
    }
}