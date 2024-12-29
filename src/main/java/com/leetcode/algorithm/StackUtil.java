package com.leetcode.algorithm;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;


public class StackUtil {
    public String simplifyPath(String path) {
        if (path == null || path.isEmpty()) {
            return "/";
        }

        Deque<String> stack = new ArrayDeque<>();
        List<String> paths = Arrays.asList(path.split("/"));

        for (String part : paths) {
            if (part.equals(".") || part.isEmpty()) {
                continue;
            } else if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(part);
            }
        }

        StringBuilder simplifiedPath = new StringBuilder();
        if (stack.isEmpty()) {
            return "/";
        }

        while (!stack.isEmpty()) {
            simplifiedPath.insert(0, "/" + stack.pop());
        }

        return simplifiedPath.toString();
    }

    public static String makeGood(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        Deque<Character> deque = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (!deque.isEmpty() && Math.abs(deque.peekLast() - c) == 32) {
                deque.removeLast(); // Use removeLast instead of pop
            } else {
                deque.addLast(c);   // Use addLast instead of push
            }
        }

        StringBuilder result = new StringBuilder();
        while (!deque.isEmpty()) {
            result.append(deque.removeFirst()); // Use removeFirst and append for efficiency
        }

        return result.toString();
    }
}
