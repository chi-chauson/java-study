package com.leetcode.algorithm;

public class TreeUtil {
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
