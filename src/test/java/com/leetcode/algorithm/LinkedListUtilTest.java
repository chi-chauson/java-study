package com.leetcode.algorithm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListUtilTest {

    private LinkedListUtil util = new LinkedListUtil();

    //Helper method to create linked list
    private ListNode createLinkedList(int... values) {
        if (values == null || values.length == 0) {
            return null;
        }

        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        return head;
    }

    private List<Integer> linkedListToList(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return list;
    }

    @Test
    void testMiddleNode_oddLength() {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode middle = util.middleNode(head);
        assertEquals(3, middle.val);
    }

    @Test
    void testMiddleNode_evenLength() {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6))))));
        ListNode middle = util.middleNode(head);
        assertEquals(4, middle.val);
    }

    @Test
    void testMiddleNode_singleNode() {
        ListNode head = new ListNode(1);
        ListNode middle = util.middleNode(head);
        assertEquals(1, middle.val);
    }

    @Test
    void testMiddleNode_emptyList() {
        ListNode head = null;
        ListNode middle = util.middleNode(head);
        assertNull(middle);
    }

    @Test
    void testMiddleNode_twoNodes() {
        ListNode head = new ListNode(1, new ListNode(2));
        ListNode middle = util.middleNode(head);
        assertEquals(2, middle.val);
    }

    @Test
    void testMiddleNode_longList() {
        ListNode head = createLinkedList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        ListNode middle = util.middleNode(head);
        assertEquals(6, middle.val);
    }

    @Test
    void testDeleteDuplicates_emptyList() {
        ListNode head = null;
        ListNode result = util.deleteDuplicates(head);
        assertNull(result);
    }

    @Test
    void testDeleteDuplicates_singleNode() {
        ListNode head = new ListNode(1);
        ListNode result = util.deleteDuplicates(head);
        List<Integer> expected = Arrays.asList(1);
        assertEquals(expected, linkedListToList(result));
    }

    @Test
    void testDeleteDuplicates_noDuplicates() {
        ListNode head = createLinkedList(1, 2, 3, 4, 5);
        ListNode result = util.deleteDuplicates(head);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(expected, linkedListToList(result));
    }

    @Test
    void testDeleteDuplicates_allDuplicates() {
        ListNode head = createLinkedList(1, 1, 1, 1, 1);
        ListNode result = util.deleteDuplicates(head);
        List<Integer> expected = Arrays.asList(1);
        assertEquals(expected, linkedListToList(result));
    }

    @Test
    void testDeleteDuplicates_mixedDuplicates() {
        ListNode head = createLinkedList(1, 1, 2, 3, 3);
        ListNode result = util.deleteDuplicates(head);
        List<Integer> expected = Arrays.asList(1, 2, 3);
        assertEquals(expected, linkedListToList(result));
    }

    @Test
    void testDeleteDuplicates_consecutiveDuplicates() {
        ListNode head = createLinkedList(1, 2, 2, 3, 4, 4, 4, 5);
        ListNode result = util.deleteDuplicates(head);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(expected, linkedListToList(result));
    }

    @Test
    void testDeleteDuplicates_negativeValues() {
        ListNode head = createLinkedList(-3, -3, -2, -1, -1, 0, 1, 1, 2);
        ListNode result = util.deleteDuplicates(head);
        List<Integer> expected = Arrays.asList(-3, -2, -1, 0, 1, 2);
        assertEquals(expected, linkedListToList(result));
    }

    @Test
    void testReverseBetween_example1() {
        ListNode head = createLinkedList(1, 2, 3, 4, 5);
        ListNode result = util.reverseBetween(head, 2, 4);
        List<Integer> expected = Arrays.asList(1, 4, 3, 2, 5);
        assertEquals(expected, linkedListToList(result));
    }

    @Test
    void testReverseBetween_example2() {
        ListNode head = createLinkedList(5);
        ListNode result = util.reverseBetween(head, 1, 1);
        List<Integer> expected = Arrays.asList(5);
        assertEquals(expected, linkedListToList(result));
    }

    @Test
    void testReverseBetween_reverseFirstTwo() {
        ListNode head = createLinkedList(1, 2, 3, 4, 5);
        ListNode result = util.reverseBetween(head, 1, 2);
        List<Integer> expected = Arrays.asList(2, 1, 3, 4, 5);
        assertEquals(expected, linkedListToList(result));
    }

    @Test
    void testReverseBetween_reverseLastTwo() {
        ListNode head = createLinkedList(1, 2, 3, 4, 5);
        ListNode result = util.reverseBetween(head, 4, 5);
        List<Integer> expected = Arrays.asList(1, 2, 3, 5, 4);
        assertEquals(expected, linkedListToList(result));
    }

    @Test
    void testReverseBetween_reverseEntireList() {
        ListNode head = createLinkedList(1, 2, 3, 4, 5);
        ListNode result = util.reverseBetween(head, 1, 5);
        List<Integer> expected = Arrays.asList(5, 4, 3, 2, 1);
        assertEquals(expected, linkedListToList(result));
    }

    @Test
    void testReverseBetween_singleNodeReverse() {
        ListNode head = createLinkedList(1);
        ListNode result = util.reverseBetween(head, 1, 1);
        List<Integer> expected = Arrays.asList(1);
        assertEquals(expected, linkedListToList(result));
    }

    @Test
    void testReverseBetween_twoNodesReverse() {
        ListNode head = createLinkedList(1, 2);
        ListNode result = util.reverseBetween(head, 1, 2);
        List<Integer> expected = Arrays.asList(2, 1);
        assertEquals(expected, linkedListToList(result));
    }

    @Test
    void testReverseBetween_threeNodesReverseMiddle() {
        ListNode head = createLinkedList(1, 2, 3);
        ListNode result = util.reverseBetween(head, 2, 3);
        List<Integer> expected = Arrays.asList(1, 3, 2);
        assertEquals(expected, linkedListToList(result));
    }

}