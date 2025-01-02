package com.leetcode.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeUtil {
    private int maxDiff =0;
    private int diameter=0;

    /**
     * Computes the diameter of a binary tree.
     *
     * @param root The root of the binary tree.
     * @return The length of the diameter.
     */
    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return diameter;
    }


    /**
     * Helper method to compute the depth of a subtree rooted at the given node.
     * Updates the maxDiameter during the traversal.
     *
     * @param node The current node.
     * @return The depth of the subtree.
     */
    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Recursively compute the depth of left and right subtrees
        int leftHeight =  height(node.left);
        int rightHeight = height(node.right);

        // Update the diameter if the path through this node is larger
        diameter = Math.max(diameter, leftHeight + rightHeight);

        // Return the depth of the subtree rooted at this node
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public int maxAncestorDiff(TreeNode root) {
        findNodeDiff(root, root.val, root.val);
        return maxDiff;
    }

    private void findNodeDiff(TreeNode node, int min, int max) {
        if (node == null) {
            return;
        }

        int diffMin = Math.abs(node.val - min);
        int diffMax = Math.abs(node.val - max);
        maxDiff = Math.max(maxDiff, Math.max(diffMin, diffMax));

        int newMin = Math.min(node.val, min);
        int newMax = Math.max(node.val, max);

        findNodeDiff(node.left, newMin, newMax);
        findNodeDiff(node.right, newMin, newMax);
    }

    public int minDepth(TreeNode root) {
        // Empty tree has depth 0
        if (root == null) {
            return 0;
        }

        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);

        // If one subtree is null, consider the other subtree's depth
        if (leftDepth == 0) {
            return 1 + rightDepth;
        } else if (rightDepth == 0) {
            return 1 + leftDepth;
        }

        // Minimum depth is 1 (current node) + minimum of left and right subtrees
        return 1 + Math.min(leftDepth, rightDepth);
    }

    public int deepestLeavesSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int sum = 0;
        // Perform BFS level by level
        while (!queue.isEmpty()) {
            int nodesInCurrentLevel = queue.size();

            sum = 0; // Reset sum for the current level
            for (int i = 0; i < nodesInCurrentLevel; i++) {
                TreeNode node = queue.remove();

                // Calculate sum
                sum = sum + node.val;

                // Add child nodes for the next level
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        // After the loop, sum contains the sum of the deepest leaves
        return sum;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int count = 0;
        while (!queue.isEmpty()) {
            int nodesInCurrentLevel = queue.size();

            Deque<Integer> levelResults = new ArrayDeque<>();
            for (int i = 0; i < nodesInCurrentLevel; i++) {
                TreeNode node = queue.remove();

                if (count % 2 == 0) {
                    levelResults.addLast(node.val);
                } else {
                    levelResults.addFirst(node.val);
                }

                // Add child nodes for the next level
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            results.add(new ArrayList<>(levelResults));

            count++;
        }

        return results;

    }

    public int closestValue(TreeNode root, double target) {
        boolean done = false;
        int closest = root.val;
        TreeNode current = root;
        while (!done && current != null) {
            if (Math.abs(current.val - target) < Math.abs(closest - target)) {
                closest = current.val;
            } else if (Math.abs(current.val - target) == Math.abs(closest - target) && current.val < closest) {
                closest = current.val;
            }
            if (target < current.val) {
                current = current.left;
            } else if (target > current.val) {
                current = current.right;
            } else {
                done = true;
            }
        }
        return closest;
    }

    TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

}
