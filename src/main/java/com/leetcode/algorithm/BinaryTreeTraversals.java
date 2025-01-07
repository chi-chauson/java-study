package com.leetcode.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreeTraversals {
    /**
     * Iterative Pre-order Traversal (Node -> Left -> Right)
     *
     * @param root the root of the binary tree
     * @return list of node values in Pre-order
     */
    public List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            result.add(current.val); // Process the node

            // Push right first so that left is processed first
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }

        return result;
    }

    /**
     * Iterative In-order Traversal (Left -> Node -> Right)
     *
     * @param root the root of the binary tree
     * @return list of node values in In-order
     */
    public List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // Reach the leftmost node of the current node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // Current must be null at this point
            current = stack.pop();
            result.add(current.val); // Process the node

            // Visit the right subtree
            current = current.right;
        }

        return result;
    }

    /**
     * Iterative Post-order Traversal (Left -> Right -> Node) using two stacks
     *
     * @param root the root of the binary tree
     * @return list of node values in Post-order
     */
    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> stack1 = new ArrayDeque<>();
        Deque<TreeNode> stack2 = new ArrayDeque<>();

        stack1.push(root);

        while (!stack1.isEmpty()) {
            TreeNode current = stack1.pop();
            stack2.push(current);

            // Push left and right children to stack1
            if (current.left != null) {
                stack1.push(current.left);
            }
            if (current.right != null) {
                stack1.push(current.right);
            }
        }

        // Pop all items from stack2 and add to result
        while (!stack2.isEmpty()) {
            result.add(stack2.pop().val);
        }

        return result;
    }

    /**
     * Iterative Post-order Traversal (Left -> Right -> Node) using one stack
     *
     * @param root the root of the binary tree
     * @return list of node values in Post-order
     */
    public List<Integer> postOrderTraversalOneStack(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode lastVisited = null;
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // Go to the leftmost node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            TreeNode peekNode = stack.peek();
            // If right child exists and traversing node from left child, move to right child
            if (peekNode.right != null && lastVisited != peekNode.right) {
                current = peekNode.right;
            } else {
                // Process the node
                result.add(peekNode.val);
                lastVisited = stack.pop();
            }
        }

        return result;
    }

}
