package com.leetcode.algorithm;

public class LinkedListUtil {
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head; // Handle cases with 0 or 1 node
        }

        ListNode slow = head;
        ListNode fast = head;

        // Use two pointers: slow moves one step at a time, fast two steps
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow; // slow will point to the middle node
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        for (ListNode currNode = head; currNode != null;) {
            ListNode nextNode = currNode.next;
            if (nextNode != null && currNode.val == nextNode.val) {
                currNode.next = nextNode.next;
            } else {
                currNode = currNode.next;
            }
        }

        return head;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null || left == right) {
            return head;
        }

        ListNode dummy = new ListNode(0); // Dummy node to handle left == 1 case
        dummy.next = head;
        ListNode pre = dummy; // Node before the sublist to be reversed
        for (int i = 1; i < left; i++) {
            pre = pre.next;
        }

        ListNode cur = pre.next; // Start of the sublist to be reversed
        for (int i = 0; i < right - left; i++) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummy.next;
    }
}
