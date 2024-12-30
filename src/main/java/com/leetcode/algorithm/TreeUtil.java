package com.leetcode.algorithm;

public class TreeUtil {
    private int maxDiff =0;

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
}
