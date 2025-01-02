package com.leetcode.algorithm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GraphUtilTest {
    private final GraphUtil util = new GraphUtil();

    @Test
    void testTriangleGraphPathExists() {
        int n = 3;
        int[][] edges = {{0,1}, {1,2}, {2,0}};
        int source = 0;
        int destination = 2;
        assertTrue(util.validPath(n, edges, source, destination));
    }

    @Test
    void testDisconnectedGraphPathDoesNotExist() {
        int n = 6;
        int[][] edges = {{0,1}, {0,2}, {3,5}, {5,4}, {4,3}};
        int source = 0;
        int destination = 5;
        assertFalse(util.validPath(n, edges, source, destination));
    }

    @Test
    void testSingleVertexSameSourceDestination() {
        int n = 1;
        int[][] edges = {};
        int source = 0;
        int destination = 0;
        assertTrue(util.validPath(n, edges, source, destination));
    }

    @Test
    void testSingleVertexDifferentSourceDestination() {
        int n = 2;
        int[][] edges = {};
        int source = 0;
        int destination = 1;
        assertFalse(util.validPath(n, edges, source, destination));
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
        assertTrue(util.validPath(n, edges, source, destination));
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
        assertFalse(util.validPath(n, edges, source, destination));
    }

    @Test
    void testGraphWithNoEdgesSourceEqualsDestination() {
        int n = 5;
        int[][] edges = {};
        int source = 3;
        int destination = 3;
        assertTrue(util.validPath(n, edges, source, destination));
    }

    @Test
    void testGraphWithNoEdgesSourceNotEqualsDestination() {
        int n = 5;
        int[][] edges = {};
        int source = 1;
        int destination = 4;
        assertFalse(util.validPath(n, edges, source, destination));
    }

    @Test
    void testPathExistsDirectEdge() {
        int n = 4;
        int[][] edges = {{0,1}, {1,2}, {2,3}};
        int source = 1;
        int destination = 2;
        assertTrue(util.validPath(n, edges, source, destination));
    }

    @Test
    void testPathExistsIndirectEdge() {
        int n = 5;
        int[][] edges = {{0,1}, {1,2}, {2,3}, {3,4}};
        int source = 0;
        int destination = 4;
        assertTrue(util.validPath(n, edges, source, destination));
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
        assertFalse(util.validPath(n, edges, source, destination));
    }

    @Test
    void testGraphWithCyclePathExists() {
        int n = 4;
        int[][] edges = {
                {0,1}, {1,2}, {2,0}, {2,3}
        };
        int source = 3;
        int destination = 0;
        assertTrue(util.validPath(n, edges, source, destination));
    }
}