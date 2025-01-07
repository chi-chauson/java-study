package com.leetcode.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.leetcode.algorithm.BinaryTreeTraversals;
import com.leetcode.algorithm.TreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BinaryTreeTraversalsTest {

    private BinaryTreeTraversals traversals;

    @BeforeEach
    void setUp() {
        traversals = new BinaryTreeTraversals();
    }

    /**
     * Helper method to build a binary tree from a level-order array representation.
     *
     * @param values an array of Integer where null represents a missing node
     * @return the root of the constructed binary tree
     */
    private TreeNode buildTree(Integer[] values) {
        if (values.length == 0) return null;
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < values.length) {
            TreeNode current = queue.poll();
            if (current != null) {
                // Assign left child
                if (i < values.length && values[i] != null) {
                    current.left = new TreeNode(values[i]);
                    queue.offer(current.left);
                } else {
                    current.left = null;
                    queue.offer(null);
                }
                i++;
                // Assign right child
                if (i < values.length && values[i] != null) {
                    current.right = new TreeNode(values[i]);
                    queue.offer(current.right);
                } else {
                    current.right = null;
                    queue.offer(null);
                }
                i++;
            }
        }
        return root;
    }

    // ------------------ Pre-order Traversal Tests ------------------

    @Test
    @DisplayName("Pre-order Traversal: Empty Tree")
    void testPreOrderTraversal_EmptyTree() {
        TreeNode root = buildTree(new Integer[]{});
        List<Integer> expected = Collections.emptyList();
        List<Integer> actual = traversals.preOrderTraversal(root);
        assertEquals(expected, actual, "Pre-order traversal of empty tree should be empty.");
    }

    @Test
    @DisplayName("Pre-order Traversal: Single Node")
    void testPreOrderTraversal_SingleNode() {
        TreeNode root = buildTree(new Integer[]{10});
        List<Integer> expected = Arrays.asList(10);
        List<Integer> actual = traversals.preOrderTraversal(root);
        assertEquals(expected, actual, "Pre-order traversal of single-node tree should contain only the root.");
    }

    @Test
    @DisplayName("Pre-order Traversal: Sample Tree")
    void testPreOrderTraversal_SampleTree() {
        // Tree Representation:
        //       1
        //     /   \
        //    2     3
        //   / \     \
        //  4   5     6
        TreeNode root = buildTree(new Integer[]{1, 2, 3, 4, 5, null, 6});
        List<Integer> expected = Arrays.asList(1, 2, 4, 5, 3, 6);
        List<Integer> actual = traversals.preOrderTraversal(root);
        assertEquals(expected, actual, "Pre-order traversal of sample tree does not match expected output.");
    }

    @Test
    @DisplayName("Pre-order Traversal: Left-Skewed Tree")
    void testPreOrderTraversal_LeftSkewedTree() {
        // Tree Representation:
        //     1
        //    /
        //   2
        //  /
        // 3
        TreeNode root = buildTree(new Integer[]{1, 2, null, 3});
        List<Integer> expected = Arrays.asList(1, 2, 3);
        List<Integer> actual = traversals.preOrderTraversal(root);
        assertEquals(expected, actual, "Pre-order traversal of left-skewed tree does not match expected output.");
    }

    @Test
    @DisplayName("Pre-order Traversal: Right-Skewed Tree")
    void testPreOrderTraversal_RightSkewedTree() {
        // Tree Representation:
        // 1
        //  \
        //   2
        //    \
        //     3
        TreeNode root = buildTree(new Integer[]{1, null, 2, null, 3});
        List<Integer> expected = Arrays.asList(1, 2, 3);
        List<Integer> actual = traversals.preOrderTraversal(root);
        assertEquals(expected, actual, "Pre-order traversal of right-skewed tree does not match expected output.");
    }

    @Test
    @DisplayName("Pre-order Traversal: Complete Binary Tree")
    void testPreOrderTraversal_CompleteBinaryTree() {
        // Tree Representation:
        //        1
        //      /   \
        //     2     3
        //    / \   / \
        //   4   5 6   7
        TreeNode root = buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        List<Integer> expected = Arrays.asList(1, 2, 4, 5, 3, 6, 7);
        List<Integer> actual = traversals.preOrderTraversal(root);
        assertEquals(expected, actual, "Pre-order traversal of complete binary tree does not match expected output.");
    }

    // ------------------ In-order Traversal Tests ------------------

    @Test
    @DisplayName("In-order Traversal: Empty Tree")
    void testInOrderTraversal_EmptyTree() {
        TreeNode root = buildTree(new Integer[]{});
        List<Integer> expected = Collections.emptyList();
        List<Integer> actual = traversals.inOrderTraversal(root);
        assertEquals(expected, actual, "In-order traversal of empty tree should be empty.");
    }

    @Test
    @DisplayName("In-order Traversal: Single Node")
    void testInOrderTraversal_SingleNode() {
        TreeNode root = buildTree(new Integer[]{20});
        List<Integer> expected = Arrays.asList(20);
        List<Integer> actual = traversals.inOrderTraversal(root);
        assertEquals(expected, actual, "In-order traversal of single-node tree should contain only the root.");
    }

    @Test
    @DisplayName("In-order Traversal: Sample Tree")
    void testInOrderTraversal_SampleTree() {
        // Tree Representation:
        //       1
        //     /   \
        //    2     3
        //   / \     \
        //  4   5     6
        TreeNode root = buildTree(new Integer[]{1, 2, 3, 4, 5, null, 6});
        List<Integer> expected = Arrays.asList(4, 2, 5, 1, 3, 6);
        List<Integer> actual = traversals.inOrderTraversal(root);
        assertEquals(expected, actual, "In-order traversal of sample tree does not match expected output.");
    }

    @Test
    @DisplayName("In-order Traversal: Left-Skewed Tree")
    void testInOrderTraversal_LeftSkewedTree() {
        // Tree Representation:
        //     1
        //    /
        //   2
        //  /
        // 3
        TreeNode root = buildTree(new Integer[]{1, 2, null, 3});
        List<Integer> expected = Arrays.asList(3, 2, 1);
        List<Integer> actual = traversals.inOrderTraversal(root);
        assertEquals(expected, actual, "In-order traversal of left-skewed tree does not match expected output.");
    }

    @Test
    @DisplayName("In-order Traversal: Right-Skewed Tree")
    void testInOrderTraversal_RightSkewedTree() {
        // Tree Representation:
        // 1
        //  \
        //   2
        //    \
        //     3
        TreeNode root = buildTree(new Integer[]{1, null, 2, null, 3});
        List<Integer> expected = Arrays.asList(1, 2, 3);
        List<Integer> actual = traversals.inOrderTraversal(root);
        assertEquals(expected, actual, "In-order traversal of right-skewed tree does not match expected output.");
    }

    @Test
    @DisplayName("In-order Traversal: Complete Binary Tree")
    void testInOrderTraversal_CompleteBinaryTree() {
        // Tree Representation:
        //        1
        //      /   \
        //     2     3
        //    / \   / \
        //   4   5 6   7
        TreeNode root = buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        List<Integer> expected = Arrays.asList(4, 2, 5, 1, 6, 3, 7);
        List<Integer> actual = traversals.inOrderTraversal(root);
        assertEquals(expected, actual, "In-order traversal of complete binary tree does not match expected output.");
    }

    // ------------------ Post-order Traversal (Two Stacks) Tests ------------------

    @Test
    @DisplayName("Post-order Traversal (Two Stacks): Empty Tree")
    void testPostOrderTraversal_TwoStacks_EmptyTree() {
        TreeNode root = buildTree(new Integer[]{});
        List<Integer> expected = Collections.emptyList();
        List<Integer> actual = traversals.postOrderTraversal(root);
        assertEquals(expected, actual, "Post-order traversal (two stacks) of empty tree should be empty.");
    }

    @Test
    @DisplayName("Post-order Traversal (Two Stacks): Single Node")
    void testPostOrderTraversal_TwoStacks_SingleNode() {
        TreeNode root = buildTree(new Integer[]{30});
        List<Integer> expected = Arrays.asList(30);
        List<Integer> actual = traversals.postOrderTraversal(root);
        assertEquals(expected, actual, "Post-order traversal (two stacks) of single-node tree should contain only the root.");
    }

    @Test
    @DisplayName("Post-order Traversal (Two Stacks): Sample Tree")
    void testPostOrderTraversal_TwoStacks_SampleTree() {
        // Tree Representation:
        //       1
        //     /   \
        //    2     3
        //   / \     \
        //  4   5     6
        TreeNode root = buildTree(new Integer[]{1, 2, 3, 4, 5, null, 6});
        List<Integer> expected = Arrays.asList(4, 5, 2, 6, 3, 1);
        List<Integer> actual = traversals.postOrderTraversal(root);
        assertEquals(expected, actual, "Post-order traversal (two stacks) of sample tree does not match expected output.");
    }

    @Test
    @DisplayName("Post-order Traversal (Two Stacks): Left-Skewed Tree")
    void testPostOrderTraversal_TwoStacks_LeftSkewedTree() {
        // Tree Representation:
        //     1
        //    /
        //   2
        //  /
        // 3
        TreeNode root = buildTree(new Integer[]{1, 2, null, 3});
        List<Integer> expected = Arrays.asList(3, 2, 1);
        List<Integer> actual = traversals.postOrderTraversal(root);
        assertEquals(expected, actual, "Post-order traversal (two stacks) of left-skewed tree does not match expected output.");
    }

    @Test
    @DisplayName("Post-order Traversal (Two Stacks): Right-Skewed Tree")
    void testPostOrderTraversal_TwoStacks_RightSkewedTree() {
        // Tree Representation:
        // 1
        //  \
        //   2
        //    \
        //     3
        TreeNode root = buildTree(new Integer[]{1, null, 2, null, 3});
        List<Integer> expected = Arrays.asList(3, 2, 1);
        List<Integer> actual = traversals.postOrderTraversal(root);
        assertEquals(expected, actual, "Post-order traversal (two stacks) of right-skewed tree does not match expected output.");
    }

    @Test
    @DisplayName("Post-order Traversal (Two Stacks): Complete Binary Tree")
    void testPostOrderTraversal_TwoStacks_CompleteBinaryTree() {
        // Tree Representation:
        //        1
        //      /   \
        //     2     3
        //    / \   / \
        //   4   5 6   7
        TreeNode root = buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        List<Integer> expected = Arrays.asList(4, 5, 2, 6, 7, 3, 1);
        List<Integer> actual = traversals.postOrderTraversal(root);
        assertEquals(expected, actual, "Post-order traversal (two stacks) of complete binary tree does not match expected output.");
    }

    // ------------------ Post-order Traversal (One Stack) Tests ------------------

    @Test
    @DisplayName("Post-order Traversal (One Stack): Empty Tree")
    void testPostOrderTraversal_OneStack_EmptyTree() {
        TreeNode root = buildTree(new Integer[]{});
        List<Integer> expected = Collections.emptyList();
        List<Integer> actual = traversals.postOrderTraversalOneStack(root);
        assertEquals(expected, actual, "Post-order traversal (one stack) of empty tree should be empty.");
    }

    @Test
    @DisplayName("Post-order Traversal (One Stack): Single Node")
    void testPostOrderTraversal_OneStack_SingleNode() {
        TreeNode root = buildTree(new Integer[]{40});
        List<Integer> expected = Arrays.asList(40);
        List<Integer> actual = traversals.postOrderTraversalOneStack(root);
        assertEquals(expected, actual, "Post-order traversal (one stack) of single-node tree should contain only the root.");
    }

    @Test
    @DisplayName("Post-order Traversal (One Stack): Sample Tree")
    void testPostOrderTraversal_OneStack_SampleTree() {
        // Tree Representation:
        //       1
        //     /   \
        //    2     3
        //   / \     \
        //  4   5     6
        TreeNode root = buildTree(new Integer[]{1, 2, 3, 4, 5, null, 6});
        List<Integer> expected = Arrays.asList(4, 5, 2, 6, 3, 1);
        List<Integer> actual = traversals.postOrderTraversalOneStack(root);
        assertEquals(expected, actual, "Post-order traversal (one stack) of sample tree does not match expected output.");
    }

    @Test
    @DisplayName("Post-order Traversal (One Stack): Left-Skewed Tree")
    void testPostOrderTraversal_OneStack_LeftSkewedTree() {
        // Tree Representation:
        //     1
        //    /
        //   2
        //  /
        // 3
        TreeNode root = buildTree(new Integer[]{1, 2, null, 3});
        List<Integer> expected = Arrays.asList(3, 2, 1);
        List<Integer> actual = traversals.postOrderTraversalOneStack(root);
        assertEquals(expected, actual, "Post-order traversal (one stack) of left-skewed tree does not match expected output.");
    }

    @Test
    @DisplayName("Post-order Traversal (One Stack): Right-Skewed Tree")
    void testPostOrderTraversal_OneStack_RightSkewedTree() {
        // Tree Representation:
        // 1
        //  \
        //   2
        //    \
        //     3
        TreeNode root = buildTree(new Integer[]{1, null, 2, null, 3});
        List<Integer> expected = Arrays.asList(3, 2, 1);
        List<Integer> actual = traversals.postOrderTraversalOneStack(root);
        assertEquals(expected, actual, "Post-order traversal (one stack) of right-skewed tree does not match expected output.");
    }

    @Test
    @DisplayName("Post-order Traversal (One Stack): Complete Binary Tree")
    void testPostOrderTraversal_OneStack_CompleteBinaryTree() {
        // Tree Representation:
        //        1
        //      /   \
        //     2     3
        //    / \   / \
        //   4   5 6   7
        TreeNode root = buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        List<Integer> expected = Arrays.asList(4, 5, 2, 6, 7, 3, 1);
        List<Integer> actual = traversals.postOrderTraversalOneStack(root);
        assertEquals(expected, actual, "Post-order traversal (one stack) of complete binary tree does not match expected output.");
    }

    // ------------------ Additional Edge Case Tests ------------------

    @Test
    @DisplayName("Pre-order Traversal: Deep Left Tree")
    void testPreOrderTraversal_DeepLeftTree() {
        // Tree Representation:
        //         1
        //        /
        //       2
        //      /
        //     3
        //    /
        //   4
        TreeNode root = buildTree(new Integer[]{1, 2, null, 3, null, 4});
        List<Integer> expected = Arrays.asList(1, 2, 3, 4);
        List<Integer> actual = traversals.preOrderTraversal(root);
        assertEquals(expected, actual, "Pre-order traversal of deep left tree does not match expected output.");
    }

    @Test
    @DisplayName("In-order Traversal: Deep Left Tree")
    void testInOrderTraversal_DeepLeftTree() {
        // Tree Representation:
        //         1
        //        /
        //       2
        //      /
        //     3
        //    /
        //   4
        TreeNode root = buildTree(new Integer[]{1, 2, null, 3, null, 4});
        List<Integer> expected = Arrays.asList(4, 3, 2, 1);
        List<Integer> actual = traversals.inOrderTraversal(root);
        assertEquals(expected, actual, "In-order traversal of deep left tree does not match expected output.");
    }

    @Test
    @DisplayName("Post-order Traversal (Two Stacks): Deep Left Tree")
    void testPostOrderTraversal_TwoStacks_DeepLeftTree() {
        // Tree Representation:
        //         1
        //        /
        //       2
        //      /
        //     3
        //    /
        //   4
        TreeNode root = buildTree(new Integer[]{1, 2, null, 3, null, 4});
        List<Integer> expected = Arrays.asList(4, 3, 2, 1);
        List<Integer> actual = traversals.postOrderTraversal(root);
        assertEquals(expected, actual, "Post-order traversal (two stacks) of deep left tree does not match expected output.");
    }

    @Test
    @DisplayName("Post-order Traversal (One Stack): Deep Left Tree")
    void testPostOrderTraversal_OneStack_DeepLeftTree() {
        // Tree Representation:
        //         1
        //        /
        //       2
        //      /
        //     3
        //    /
        //   4
        TreeNode root = buildTree(new Integer[]{1, 2, null, 3, null, 4});
        List<Integer> expected = Arrays.asList(4, 3, 2, 1);
        List<Integer> actual = traversals.postOrderTraversalOneStack(root);
        assertEquals(expected, actual, "Post-order traversal (one stack) of deep left tree does not match expected output.");
    }

    @Test
    @DisplayName("All Traversals: Null Tree")
    void testAllTraversals_NullTree() {
        TreeNode root = buildTree(new Integer[]{});
        List<Integer> expected = Collections.emptyList();

        assertEquals(expected, traversals.preOrderTraversal(root), "Pre-order traversal of null tree should be empty.");
        assertEquals(expected, traversals.inOrderTraversal(root), "In-order traversal of null tree should be empty.");
        assertEquals(expected, traversals.postOrderTraversal(root), "Post-order traversal (two stacks) of null tree should be empty.");
        assertEquals(expected, traversals.postOrderTraversalOneStack(root), "Post-order traversal (one stack) of null tree should be empty.");
    }

    @Test
    @DisplayName("All Traversals: Complete Binary Tree")
    void testAllTraversals_CompleteBinaryTree() {
        // Tree Representation:
        //        1
        //      /   \
        //     2     3
        //    / \   / \
        //   4   5 6   7
        TreeNode root = buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        List<Integer> preOrderExpected = Arrays.asList(1, 2, 4, 5, 3, 6, 7);
        List<Integer> inOrderExpected = Arrays.asList(4, 2, 5, 1, 6, 3, 7);
        List<Integer> postOrderExpected = Arrays.asList(4, 5, 2, 6, 7, 3, 1);

        assertEquals(preOrderExpected, traversals.preOrderTraversal(root), "Pre-order traversal of complete binary tree does not match expected output.");
        assertEquals(inOrderExpected, traversals.inOrderTraversal(root), "In-order traversal of complete binary tree does not match expected output.");
        assertEquals(postOrderExpected, traversals.postOrderTraversal(root), "Post-order traversal (two stacks) of complete binary tree does not match expected output.");
        assertEquals(postOrderExpected, traversals.postOrderTraversalOneStack(root), "Post-order traversal (one stack) of complete binary tree does not match expected output.");
    }
}