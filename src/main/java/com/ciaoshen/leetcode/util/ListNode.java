/**
 * Leetcode - Data Structure - ListNode
 */
package com.ciaoshen.leetcode.util;
import java.util.*;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }
    public String toString() {
        ListNode cursor = this;
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(cursor.val + "->");
            cursor = cursor.next;
        } while (cursor != null);
        int length = sb.length();
        if (length > 2) { sb = sb.delete(length-2,length); }
        return sb.toString();
    }
    /**
     * Static Factory
     */
    private static final Random R = new Random();
    public static ListNode random(int size, int max) {
        ListNode sentinel = new ListNode(0), cursor = sentinel;
        for (int i = 0; i < size; i++) {
            cursor.next = new ListNode(R.nextInt(max)+1);
            cursor = cursor.next;
        }
        return sentinel.next;
    }
    public static ListNode randomSorted(int size, int max) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(R.nextInt(max)+1);
        }
        Collections.sort(list);
        ListNode sentinel = new ListNode(0), cursor = sentinel;
        for (int num : list) {
            cursor.next = new ListNode(num);
            cursor = cursor.next;
        }
        return sentinel.next;
    }
    private static void testToString() {
        ListNode test = new ListNode(0), cursor = test;
        for (int i = 1; i < 10; i++) {
            cursor.next = new ListNode(i);
            cursor = cursor.next;
        }
        System.out.println(test);
    }
    private static void testRandom() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Random [1~10]: " + random(10,10));
        }
    }
    public static void main(String[] args) {
        //testToString();
        //testRandom();
    }
}
