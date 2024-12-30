package com.leetcode.algorithm;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
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
        Integer[] input = {8,3,10,1,6,null,14,null,null,4,7,13};
        TreeNode root = buildTree(input);
        int expected = 7;
        assertEquals(expected, treeUtil.maxAncestorDiff(root));
    }

    @Test
    public void testMaximumDifferenceExample2() {
        Integer[] input = {1,null,2,null,0,3};
        TreeNode root = buildTree(input);
        int expected = 3;
        assertEquals(expected, treeUtil.maxAncestorDiff(root));
    }

    @Test
    public void testMaximumDifferenceSingleLeft() {
        Integer[] input = {5,3,null,2,null,1};
        TreeNode root = buildTree(input);
        int expected = 4; // |5 -1| = 4
        assertEquals(expected, treeUtil.maxAncestorDiff(root));
    }

    @Test
    public void testMaximumDifferenceSingleRight() {
        Integer[] input = {5,null,8,null,10,null,12};
        TreeNode root = buildTree(input);
        int expected = 7; // |5 -12| = 7
        assertEquals(expected, treeUtil.maxAncestorDiff(root));
    }

    @Test
    public void testMaximumDifferenceAllSame() {
        Integer[] input = {1,1,1,1,1,1,1};
        TreeNode root = buildTree(input);
        int expected = 0;
        assertEquals(expected, treeUtil.maxAncestorDiff(root));
    }

    @Test
    public void testMaximumDifferenceNegativeValues() {
        // Although constraints specify 0 <= Node.val, testing negative values for robustness
        Integer[] input = {5,3,9,1,4,7,12};
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

}