package com.leetcode.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImplicitGraphTest {

    @Test
    void testOpenLockTrivialCase() {
        ImplicitGraph graph = new ImplicitGraph();
        String[] deadends = {};
        String target = "0000";

        int result = graph.openLock(deadends, target);

        // Since we start at "0000" and that's also the target, steps should be 0.
        assertEquals(0, result, "If the target is the initial state, steps should be 0.");
    }

    @Test
    void testOpenLockImmediateDeadend() {
        ImplicitGraph graph = new ImplicitGraph();
        // "0000" is a deadend, so we cannot even start; result should be -1.
        String[] deadends = {"0000"};
        String target = "8888";

        int result = graph.openLock(deadends, target);

        // Expect -1 because we're blocked from the start.
        assertEquals(-1, result, "Should be -1 if '0000' is in deadends.");
    }

    @Test
    void testOpenLockOneMoveAway() {
        ImplicitGraph graph = new ImplicitGraph();
        // No deadends given.
        String[] deadends = {};
        // Target is "0001" which is one move from "0000".
        String target = "0001";

        int result = graph.openLock(deadends, target);

        // We expect 1 step to move from "0000" -> "0001".
        assertEquals(1, result, "Target '0001' should be 1 step away from '0000'.");
    }

    @Test
    void testOpenLockLargerExample() {
        ImplicitGraph graph = new ImplicitGraph();
        // Suppose these are some deadends we want to avoid.
        // This example is often given in the "Open the Lock" type problem,
        // though the exact answer depends on BFS exploration.
        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";

        int result = graph.openLock(deadends, target);

        // For the classic "Open the Lock" problem, the known answer in many solutions for this
        // particular configuration is 6. (Feel free to adapt if your BFS logic differs.)
        assertEquals(6, result, "Expected 6 steps to reach '0202' given these deadends.");
    }

    @Test
    void testNeighborsMethod() {
        ImplicitGraph graph = new ImplicitGraph();
        // This method should return all 8 possible neighbors of "0000".
        // For "0000", neighbors are: 9000, 1000, 0900, 0100, 0090, 0010, 0009, 0001
        var neighbors = graph.neighbors("0000");

        // We can check that it has 8 unique neighbors.
        assertEquals(8, neighbors.size(), "There should be 8 neighbors for any 4-digit wheel combination.");

        // Check that all expected neighbors are present.
        assertTrue(neighbors.containsAll(Arrays.asList("9000", "1000", "0900", "0100", "0090", "0010", "0009", "0001")),
                "Neighbors of '0000' did not match the expected set.");
    }

    @Test
    void testCalcEquationSingleEquation() {
        // Equation: a / b = 2.0
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a", "b"));

        double[] values = {2.0};

        // Query: a / b
        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a", "b"));

        ImplicitGraph graph = new ImplicitGraph();
        double[] results = graph.calcEquation(equations, values, queries);

        // We expect the result to be [2.0]
        assertArrayEquals(new double[]{2.0}, results, 1e-9,
                "a/b should be 2.0 based on the single input equation.");
    }

    @Test
    void testCalcEquationBasic() {
        // Equations:
        //   a / b = 2.0
        //   b / c = 3.0
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a", "b"));
        equations.add(Arrays.asList("b", "c"));

        double[] values = {2.0, 3.0};

        // Queries:
        //   a / c  -> expected = 6.0
        //   b / a  -> expected = 0.5
        //   a / e  -> expected = -1 (unknown variable)
        //   a / a  -> expected = 1.0 (any var divided by itself is 1 if it exists in the graph)
        //   x / x  -> expected = -1 (x is not in the graph at all)
        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a", "c"));
        queries.add(Arrays.asList("b", "a"));
        queries.add(Arrays.asList("a", "e"));
        queries.add(Arrays.asList("a", "a"));
        queries.add(Arrays.asList("x", "x"));

        ImplicitGraph graph = new ImplicitGraph();
        double[] results = graph.calcEquation(equations, values, queries);

        // Expected results
        double[] expected = {6.0, 0.5, -1.0, 1.0, -1.0};

        assertArrayEquals(expected, results, 1e-9,
                "Check that the evaluated ratios match the expected results.");
    }

    @Test
    void testCalcEquationMultipleQueries() {
        // Equations:
        //   x / y = 4.0
        //   y / z = 2.0
        //   z / w = 0.5
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("x", "y"));
        equations.add(Arrays.asList("y", "z"));
        equations.add(Arrays.asList("z", "w"));

        double[] values = {4.0, 2.0, 0.5};

        // Queries:
        //   x / w -> (x / y) * (y / z) * (z / w) = 4.0 * 2.0 * 0.5 = 4.0
        //   w / x -> inverse of x/w = 1/4.0 = 0.25
        //   x / x -> 1.0 (exists in graph)
        //   x / u -> -1 (u does not exist at all)
        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("x", "w"));
        queries.add(Arrays.asList("w", "x"));
        queries.add(Arrays.asList("x", "x"));
        queries.add(Arrays.asList("x", "u"));

        ImplicitGraph graph = new ImplicitGraph();
        double[] results = graph.calcEquation(equations, values, queries);

        double[] expected = {4.0, 0.25, 1.0, -1.0};

        assertArrayEquals(expected, results, 1e-9,
                "Check multi-step evaluations and missing variables.");
    }

    @Test
    void testAnswerQueryDirectCall() {
        // If you want to test the helper method directly:
        // Build a small graph manually.
        ImplicitGraph graph = new ImplicitGraph();

        // Let's manually populate the graph as if we had:
        // a / b = 2.0  ==> b / a = 0.5
        graph.graph.put("a", new HashMap<>());
        graph.graph.put("b", new HashMap<>());
        graph.graph.get("a").put("b", 2.0);
        graph.graph.get("b").put("a", 0.5);

        // Test direct calls:
        double result1 = graph.answerQuery("a", "b");  // Should be 2.0
        double result2 = graph.answerQuery("b", "a");  // Should be 0.5
        double result3 = graph.answerQuery("a", "a");  // Should be 1.0 (traversal from a to a)
        double result4 = graph.answerQuery("c", "a");  // -1.0 (no c in graph)

        assertEquals(2.0, result1, 1e-9);
        assertEquals(0.5, result2, 1e-9);
        assertEquals(1.0, result3, 1e-9);
        assertEquals(-1.0, result4, 1e-9);
    }
}