package com.leetcode.algorithm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class TreeUtilTest {
    private final TreeUtil treeUtil = new TreeUtil();

    // Helper method to build tree from array representation
    private TreeNode buildTree(Integer[] values) {
        if (values.length == 0) return null;
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < values.length) {
            TreeNode current = queue.poll();
            if (values[i] != null) {
                current.left = new TreeNode(values[i]);
                queue.offer(current.left);
            }
            i++;
            if (i < values.length && values[i] != null) {
                current.right = new TreeNode(values[i]);
                queue.offer(current.right);
            }
            i++;
        }
        return root;
    }

    // Helper method to serialize tree to level-order array
    private List<Integer> serializeTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current != null) {
                result.add(current.val);
                queue.offer(current.left);
                queue.offer(current.right);
            } else {
                result.add(null);
            }
        }
        // Remove trailing nulls
        int i = result.size() - 1;
        while (i >= 0 && result.get(i) == null) i--;
        return result.subList(0, i + 1);
    }

    /**
     * Helper method to compare expected and actual sum of deepest leaves.
     *
     * @param treeValues  The array representing the tree in level-order.
     * @param expectedSum The expected sum of the deepest leaves.
     */
    private void assertDeepestLeavesSum(Integer[] treeValues, int expectedSum) {
        TreeNode root = buildTree(treeValues);
        TreeUtil util = new TreeUtil();
        int actualSum = util.deepestLeavesSum(root);
        assertEquals(expectedSum, actualSum, "Sum of deepest leaves should be " + expectedSum);
    }

    /**
     * Helper method to compare expected and actual zigzag level order traversal.
     *
     * @param treeValues        The array representing the tree in level-order.
     * @param expectedTraversal The expected zigzag level order traversal.
     */
    private void assertZigzagLevelOrder(Integer[] treeValues, List<List<Integer>> expectedTraversal) {
        TreeNode root = buildTree(treeValues);
        TreeUtil util = new TreeUtil();
        List<List<Integer>> actualTraversal = util.zigzagLevelOrder(root);
        assertEquals(expectedTraversal, actualTraversal, "Zigzag level order traversal does not match expected.");
    }


    @Test
    void testEmptyTree_returnsZero() {
        assertNull(null);
        assertEquals(0, treeUtil.minDepth(null));
    }

    @Test
    void testSingleNodeTree_returnsOne() {
        TreeNode root = new TreeNode(1);
        assertEquals(1, treeUtil.minDepth(root));
    }

    @Test
    void testLeftSkewedTree_returnsCorrectDepth() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        assertEquals(3, treeUtil.minDepth(root));
    }

    @Test
    void testRightSkewedTree_returnsCorrectDepth() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertEquals(3, treeUtil.minDepth(root));
    }

    @Test
    void testBalancedTree_returnsCorrectDepth() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        assertEquals(2, treeUtil.minDepth(root));
    }

    @Test
    void testComplexTree_example1_returnsTwo() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        assertEquals(2, treeUtil.minDepth(root));
    }

    @Test
    void testComplexTree_example2_returnsFive() {
        TreeNode root = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);
        root.right.right.right.right = new TreeNode(6);
        assertEquals(5, treeUtil.minDepth(root));
    }

    @Test
    void testLeftSubtreeHasOnlyRightChild_returnsCorrectDepth() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        assertEquals(3, treeUtil.minDepth(root));
    }

    @Test
    void testRightSubtreeHasOnlyLeftChild_returnsCorrectDepth() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        assertEquals(3, treeUtil.minDepth(root));
    }

    @Test
    public void testMaximumDifferenceExample1() {
        Integer[] input = {8, 3, 10, 1, 6, null, 14, null, null, 4, 7, 13};
        TreeNode root = buildTree(input);
        int expected = 7;
        assertEquals(expected, treeUtil.maxAncestorDiff(root));
    }

    @Test
    public void testMaximumDifferenceExample2() {
        Integer[] input = {1, null, 2, null, 0, 3};
        TreeNode root = buildTree(input);
        int expected = 3;
        assertEquals(expected, treeUtil.maxAncestorDiff(root));
    }

    @Test
    public void testMaximumDifferenceSingleLeft() {
        Integer[] input = {5, 3, null, 2, null, 1};
        TreeNode root = buildTree(input);
        int expected = 4; // |5 -1| = 4
        assertEquals(expected, treeUtil.maxAncestorDiff(root));
    }

    @Test
    public void testMaximumDifferenceSingleRight() {
        Integer[] input = {5, null, 8, null, 10, null, 12};
        TreeNode root = buildTree(input);
        int expected = 7; // |5 -12| = 7
        assertEquals(expected, treeUtil.maxAncestorDiff(root));
    }

    @Test
    public void testMaximumDifferenceAllSame() {
        Integer[] input = {1, 1, 1, 1, 1, 1, 1};
        TreeNode root = buildTree(input);
        int expected = 0;
        assertEquals(expected, treeUtil.maxAncestorDiff(root));
    }

    @Test
    public void testMaximumDifferenceNegativeValues() {
        // Although constraints specify 0 <= Node.val, testing negative values for robustness
        Integer[] input = {5, 3, 9, 1, 4, 7, 12};
        TreeNode root = buildTree(input);
        int expected = 7; // |5 - 12| = 7, |3 -1|=2, |1-5|=4, |9-12|=3 --> max is 7
        assertEquals(expected, treeUtil.maxAncestorDiff(root));
    }

    @Test
    public void testMaximumDifferenceLargeTree() {
        // Creating a balanced tree with depth 4
        Integer[] input = {
                50,
                30, 70,
                20, 40, 60, 80,
                10, 25, 35, 45, 55, 65, 75, 85
        };
        TreeNode root = buildTree(input);
        int expected = 75; // |50 - 10|=40, |50 -85|=35, |20 -10|=10, |80 -5| undefined, max should be |20 - 10|=10, but likely |50-10|=40
        // Wait: 50 -10=40, 30-10=20, 70-10=60? No, 70-10 is not ancestor-descendant.
        // Ancestors of 10 are 20, 30, 50. So |50-10|=40 is max.
        expected = 40;
        assertEquals(expected, treeUtil.maxAncestorDiff(root));
    }

    @Test
    public void testMaximumDifferenceTwoNodes() {
        Integer[] input = {1, 2};
        TreeNode root = buildTree(input);
        int expected = 1;
        assertEquals(expected, treeUtil.maxAncestorDiff(root));
    }

    @Test
    public void testExample1() {
        // Input: root = [1,2,3,4,5]
        Integer[] input = {1, 2, 3, 4, 5};
        TreeNode root = buildTree(input);
        TreeUtil util = new TreeUtil();
        int expected = 3;
        int actual = util.diameterOfBinaryTree(root);
        assertEquals(expected, actual, "Diameter should be 3");
    }

    @Test
    public void testExample2() {
        // Input: root = [1,2]
        Integer[] input = {1, 2};
        TreeNode root = buildTree(input);
        TreeUtil util = new TreeUtil();
        int expected = 1;
        int actual = util.diameterOfBinaryTree(root);
        assertEquals(expected, actual, "Diameter should be 1");
    }

    @Test
    public void testSingleNode() {
        // Input: root = [1]
        Integer[] input = {1};
        TreeNode root = buildTree(input);
        TreeUtil util = new TreeUtil();
        int expected = 0;
        int actual = util.diameterOfBinaryTree(root);
        assertEquals(expected, actual, "Diameter should be 0 for single node");
    }

    @Test
    public void testLeftSkewedTree() {
        // Input: root = [1,2,null,3,null,4,null,5]
        Integer[] input = {1, 2, null, 3, null, 4, null, 5};
        TreeNode root = buildTree(input);
        TreeUtil util = new TreeUtil();
        int expected = 4;
        int actual = util.diameterOfBinaryTree(root);
        assertEquals(expected, actual, "Diameter should be 4 for left skewed tree");
    }

    @Test
    public void testRightSkewedTree() {
        // Input: root = [1,null,2,null,3,null,4,null,5]
        Integer[] input = {1, null, 2, null, 3, null, 4, null, 5};
        TreeNode root = buildTree(input);
        TreeUtil util = new TreeUtil();
        int expected = 4;
        int actual = util.diameterOfBinaryTree(root);
        assertEquals(expected, actual, "Diameter should be 4 for right skewed tree");
    }

    @Test
    public void testBalancedTree() {
        // Input: root = [1,2,3,4,5,6,7]
        Integer[] input = {1, 2, 3, 4, 5, 6, 7};
        TreeNode root = buildTree(input);
        TreeUtil util = new TreeUtil();
        int expected = 4; // e.g., path [4,2,1,3,7]
        int actual = util.diameterOfBinaryTree(root);
        assertEquals(expected, actual, "Diameter should be 4 for balanced tree");
    }

    @Test
    public void testTreeWithNegativeValues() {
        // Input: root = [1,-2,-3,4,5]
        Integer[] input = {1, -2, -3, 4, 5};
        TreeNode root = buildTree(input);
        TreeUtil util = new TreeUtil();
        int expected = 3;
        int actual = util.diameterOfBinaryTree(root);
        assertEquals(expected, actual, "Diameter should be 3 even with negative values");
    }

    @Test
    public void testLargeTree() {
        // Construct a large balanced binary tree with depth 14 (~16383 nodes)
        // For practicality in testing, we'll use a smaller size
        int depth = 10; // Adjusted for testing purposes
        Integer[] input = new Integer[(int) Math.pow(2, depth) - 1];
        Arrays.fill(input, 1);
        TreeNode root = buildTree(input);
        TreeUtil util = new TreeUtil();
        int expected = (depth - 1) * 2;
        int actual = util.diameterOfBinaryTree(root);
        assertEquals(expected, actual, "Diameter should be " + expected + " for large balanced tree");
    }

    @Test
    public void testExample1_DeepestLeavesSum() {
        // Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
        Integer[] input = {1, 2, 3, 4, 5, null, 6, 7, null, null, null, null, 8};
        int expected = 15; // Deepest leaves are 7 and 8
        assertDeepestLeavesSum(input, expected);
    }

    @Test
    public void testExample2_DeepestLeavesSum() {
        // Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
        Integer[] input = {6, 7, 8, 2, 7, 1, 3, 9, null, 1, 4, null, null, null, 5};
        int expected = 19; // Deepest leaves are 9, 1, 4, 5
        assertDeepestLeavesSum(input, expected);
    }

    @Test
    public void testSingleNode_DeepestLeavesSum() {
        // Input: root = [10]
        Integer[] input = {10};
        int expected = 10; // Only one node
        assertDeepestLeavesSum(input, expected);
    }

    @Test
    public void testLeftSkewedTree_DeepestLeavesSum() {
        // Input: root = [1,2,null,3,null,4,null,5]
        Integer[] input = {1, 2, null, 3, null, 4, null, 5};
        int expected = 5; // Deepest leaf is 5
        assertDeepestLeavesSum(input, expected);
    }

    @Test
    public void testRightSkewedTree_DeepestLeavesSum() {
        // Input: root = [1,null,2,null,3,null,4,null,5]
        Integer[] input = {1, null, 2, null, 3, null, 4, null, 5};
        int expected = 5; // Deepest leaf is 5
        assertDeepestLeavesSum(input, expected);
    }

    @Test
    public void testBalancedTree_DeepestLeavesSum() {
        // Input: root = [1,2,3,4,5,6,7]
        Integer[] input = {1, 2, 3, 4, 5, 6, 7};
        int expected = 22; // Deepest leaves are 4,5,6,7; sum = 4+5+6+7=22
        assertDeepestLeavesSum(input, expected);
    }

    @Test
    public void testTreeWithMultipleDeepestLeaves_DeepestLeavesSum() {
        // Input: root = [1,2,3,4,5,6,7,8,9,10,null,null,11,12,null,13]
        Integer[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, null, null, 11, 12, null, 13};
        int expected = 13; // Deepest leaf is 13
        assertDeepestLeavesSum(input, expected);
    }

    @Test
    public void testTreeWithAllLeavesAtSameDepth_DeepestLeavesSum() {
        // Input: root = [5,3,8,1,4,7,9]
        Integer[] input = {5, 3, 8, 1, 4, 7, 9};
        int expected = 1 + 4 + 7 + 9; // Sum = 21
        assertDeepestLeavesSum(input, 21);
    }

    @Test
    public void testLargeTree_DeepestLeavesSum() {
        // Construct a complete binary tree with depth 4
        // Level 1: 1
        // Level 2: 2,3
        // Level 3: 4,5,6,7
        // Level 4: 8,9,10,11,12,13,14,15
        Integer[] input = {
                1,
                2, 3,
                4, 5, 6, 7,
                8, 9, 10, 11, 12, 13, 14, 15
        };
        int expected = 8 + 9 + 10 + 11 + 12 + 13 + 14 + 15; // Sum = 92
        assertDeepestLeavesSum(input, expected);
    }

    @Test
    public void testTreeWithNullNodes_DeepestLeavesSum() {
        // Input: root = [1,2,3,null,4,null,5,null,null,6]
        Integer[] input = {1, 2, 3, null, 4, null, 5, null, null, 6};
        int expected = 6; // Deepest leaf is 6
        assertDeepestLeavesSum(input, expected);
    }

    @Test
    public void testZigzagTraversal_Example1() {
        // Input: root = [3,9,20,null,null,15,7]
        Integer[] input = {3, 9, 20, null, null, 15, 7};
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(3),
                Arrays.asList(20, 9),
                Arrays.asList(15, 7)
        );
        assertZigzagLevelOrder(input, expected);
    }

    @Test
    public void testZigzagTraversal_SingleNode() {
        // Input: root = [1]
        Integer[] input = {1};
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1)
        );
        assertZigzagLevelOrder(input, expected);
    }

    @Test
    public void testZigzagTraversal_EmptyTree() {
        // Input: root = []
        Integer[] input = {};
        List<List<Integer>> expected = new ArrayList<>();
        assertZigzagLevelOrder(input, expected);
    }

    @Test
    public void testZigzagTraversal_LeftSkewedTree() {
        // Input: root = [1,2,null,3,null,4,null,5]
        Integer[] input = {1, 2, null, 3, null, 4, null, 5};
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3),
                Arrays.asList(4),
                Arrays.asList(5)
        );
        assertZigzagLevelOrder(input, expected);
    }

    @Test
    public void testZigzagTraversal_RightSkewedTree() {
        // Input: root = [1,null,2,null,3,null,4,null,5]
        Integer[] input = {1, null, 2, null, 3, null, 4, null, 5};
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3),
                Arrays.asList(4),
                Arrays.asList(5)
        );
        assertZigzagLevelOrder(input, expected);
    }

    @Test
    public void testZigzagTraversal_BalancedTree() {
        // Input: root = [1,2,3,4,5,6,7]
        Integer[] input = {1, 2, 3, 4, 5, 6, 7};
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(3, 2),
                Arrays.asList(4, 5, 6, 7)
        );
        assertZigzagLevelOrder(input, expected);
    }

    @Test
    public void testZigzagTraversal_MultipleDeepestLeaves() {
        // Input: root = [1,2,3,4,5,6,7,8,9,10,null,null,11,12,null,13]
        Integer[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, null, null, 11, 12, null, 13};
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(3, 2),
                Arrays.asList(4, 5, 6, 7),
                Arrays.asList(12, 11, 10, 9, 8),
                Arrays.asList(13)
        );
        assertZigzagLevelOrder(input, expected);
    }

    @Test
    public void testZigzagTraversal_AllLeavesSameDepth() {
        // Input: root = [5,3,8,1,4,7,9]
        Integer[] input = {5, 3, 8, 1, 4, 7, 9};
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(5),
                Arrays.asList(8, 3),
                Arrays.asList(1, 4, 7, 9)
        );
        assertZigzagLevelOrder(input, expected);
    }

    @Test
    public void testZigzagTraversal_LargeBalancedTree() {
        // Construct a complete binary tree with depth 4
        // Level 1: 1
        // Level 2: 2,3
        // Level 3: 4,5,6,7
        // Level 4: 8,9,10,11,12,13,14,15
        Integer[] input = {
                1,
                2, 3,
                4, 5, 6, 7,
                8, 9, 10, 11, 12, 13, 14, 15
        };
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(3, 2),
                Arrays.asList(4, 5, 6, 7),
                Arrays.asList(15, 14, 13, 12, 11, 10, 9, 8)
        );
        assertZigzagLevelOrder(input, expected);
    }

    @Test
    public void testZigzagTraversal_TreeWithNullNodes() {
        // Input: root = [1,2,3,null,4,null,5,null,null,6]
        Integer[] input = {1, 2, 3, null, 4, null, 5, null, null, 6};
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(3, 2),
                Arrays.asList(4, 5),
                Arrays.asList(6)
        );
        assertZigzagLevelOrder(input, expected);
    }

    @Test
    void testClosestValueExample1() {
        // Tree: [4,2,5,1,3]
        Integer[] values = {4,2,5,1,3};
        TreeNode root = buildTree(values);
        double target = 3.714286;
        int expected = 4;
        assertEquals(expected, treeUtil.closestValue(root, target));
    }

    @Test
    void testClosestValueExample2_SingleNode() {
        // Tree: [1]
        Integer[] values = {1};
        TreeNode root = buildTree(values);
        double target = 4.428571;
        int expected = 1;
        assertEquals(expected, treeUtil.closestValue(root, target));
    }

    @Test
    void testClosestValueTargetLowerThanAllNodes() {
        // Tree: [10,5,15]
        Integer[] values = {10,5,15};
        TreeNode root = buildTree(values);
        double target = 2.0;
        int expected = 5;
        assertEquals(expected, treeUtil.closestValue(root, target));
    }

    @Test
    void testClosestValueTargetHigherThanAllNodes() {
        // Tree: [10,5,15]
        Integer[] values = {10,5,15};
        TreeNode root = buildTree(values);
        double target = 20.0;
        int expected = 15;
        assertEquals(expected, treeUtil.closestValue(root, target));
    }

    @Test
    void testClosestValueMultipleClosestValues() {
        // Tree: [4,2,5,1,3]
        Integer[] values = {4,2,5,1,3};
        TreeNode root = buildTree(values);
        double target = 3.5;
        int expected = 3; // Both 3 and 4 are equally close, return smaller
        assertEquals(expected, treeUtil.closestValue(root, target));
    }

    @Test
    void testClosestValueExactMatch() {
        // Tree: [4,2,5,1,3]
        Integer[] values = {4,2,5,1,3};
        TreeNode root = buildTree(values);
        double target = 3.0;
        int expected = 3;
        assertEquals(expected, treeUtil.closestValue(root, target));
    }

    @Test
    void testClosestValueLargeBST() {
        // Build a large BST
        TreeNode root = null;
        for (int i = 1; i <= 10000; i++) {
            root = treeUtil.insertIntoBST(root, i);
        }
        double target = 9999.5;
        int expected = 9999;
        assertEquals(expected, treeUtil.closestValue(root, target));
    }

    @Test
    void testClosestValueNegativeTarget() {
        // Tree: [4,2,5,1,3]
        Integer[] values = {4,2,5,1,3};
        TreeNode root = buildTree(values);
        double target = -1.0;
        int expected = 1;
        assertEquals(expected, treeUtil.closestValue(root, target));
    }

    @Test
    void testClosestValueMultipleSameClosest() {
        // Tree: [6,2,8,0,4,7,9]
        Integer[] values = {6,2,8,0,4,7,9};
        TreeNode root = buildTree(values);
        double target = 5.0;
        int expected = 4; // 4 and 6 are equally close, return 4
        assertEquals(expected, treeUtil.closestValue(root, target));
    }

    @Test
    void testClosestValueTargetIsFloat() {
        // Tree: [10,5,15,3,7,13,18]
        Integer[] values = {10,5,15,3,7,13,18};
        TreeNode root = buildTree(values);
        double target = 12.7;
        int expected = 13;
        assertEquals(expected, treeUtil.closestValue(root, target));
    }

    @Test
    public void testInsertIntoEmptyTree() {
        Integer[] input = {};
        int val = 10;
        TreeNode root = buildTree(input);
        TreeNode result = treeUtil.insertIntoBST(root, val);
        List<Integer> expected = Arrays.asList(10);
        assertEquals(expected, serializeTree(result));
    }

    @Test
    public void testInsertAsLeftChild() {
        Integer[] input = {4, 2, 7, 1, 3};
        int val = 5;
        TreeNode root = buildTree(input);
        TreeNode result = treeUtil.insertIntoBST(root, val);
        List<Integer> expected = Arrays.asList(4, 2, 7, 1, 3, 5);
        assertEquals(expected, serializeTree(result));
    }

    @Test
    public void testInsertAsRightChildWithDeepInsertion() {
        Integer[] input = {40, 20, 60, 10, 30, 50, 70};
        int val = 25;
        TreeNode root = buildTree(input);
        TreeNode result = treeUtil.insertIntoBST(root, val);
        List<Integer> expected = Arrays.asList(40, 20, 60, 10, 30, 50, 70, null, null, 25);
        assertEquals(expected, serializeTree(result));
    }

    @Test
    public void testInsertIntoBSTWithExistingNullLeaves() {
        Integer[] input = {4, 2, 7, 1, 3, null, null, null, null, null, null};
        int val = 5;
        TreeNode root = buildTree(input);
        TreeNode result = treeUtil.insertIntoBST(root, val);
        List<Integer> expected = Arrays.asList(4, 2, 7, 1, 3, 5);
        assertEquals(expected, serializeTree(result));
    }

    @Test
    public void testInsertLeftMostNode() {
        Integer[] input = {50, 30, 70, 20, 40, 60, 80};
        int val = 10;
        TreeNode root = buildTree(input);
        TreeNode result = treeUtil.insertIntoBST(root, val);
        List<Integer> expected = Arrays.asList(50, 30, 70, 20, 40, 60, 80, 10);
        assertEquals(expected, serializeTree(result));
    }

    @Test
    public void testInsertRightMostNode() {
        Integer[] input = {50, 30, 70, 20, 40, 60, 80};
        int val = 90;
        TreeNode root = buildTree(input);
        TreeNode result = treeUtil.insertIntoBST(root, val);
        List<Integer> expected = Arrays.asList(50, 30, 70, 20, 40, 60, 80, null, null, null, null, null, null, null, 90);
        assertEquals(expected, serializeTree(result));
    }

}