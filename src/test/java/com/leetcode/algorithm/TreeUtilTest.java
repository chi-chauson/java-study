package com.leetcode.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeUtilTest {
    private final TreeUtil treeUtil = new TreeUtil();

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

}