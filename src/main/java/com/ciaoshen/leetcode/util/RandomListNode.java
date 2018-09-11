/**
 * Leetcode - Basic Data Stucture - Random List Node
 *
 * A linked list is given such that each node contains
 * an additional random pointer which could point to any
 * node in the list or null.
 */
package com.ciaoshen.leetcode.util;
import java.util.*;

public class RandomListNode {
    public int label;
    public RandomListNode next, random;
    public RandomListNode(int x) { label = x; }

    public String toString() {
        RandomListNode cur = this;
        String str = "Next Chain = ";
        while (cur != null) {
            str += cur.label;
            if (cur.next != null) {
                str += "->";
            }
            cur = cur.next;
        }
        return str;
    }
    public String showRandomChain() {
        RandomListNode cur = this;
        String str = "Random Chain = [ ";
        while (cur != null) {
            str = str + "[" + cur.label + "->";
            if (cur.random != null) {
                str = str + cur.random.label + "]";
            }
            if (cur.next != null) {
                str += ", ";
            }
            cur = cur.next;
        }
        str += " ]";
        return str;
    }
    public static RandomListNode random(int size, int max) {
        Random r = new Random();
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode cur = dummy;
        RandomListNode[] members = new RandomListNode[size];
        for (int i = 0; i < size; i++) {
            RandomListNode node = new RandomListNode(r.nextInt(max)+1);
            members[i] = node;
            cur.next = node;
            cur = cur.next;
        }
        cur = dummy.next;
        for (int i = 0; i < size; i++) {
            cur.random = members[r.nextInt(size)];
            cur = cur.next;
        }
        return dummy.next;
    }
    /**
     * 测试区
     */
    private static void testToString() {
        RandomListNode one = new RandomListNode(1);
        RandomListNode two = new RandomListNode(2);
        RandomListNode three = new RandomListNode(3);
        RandomListNode four = new RandomListNode(4);
        one.next = two; one.random = three;
        two.next = three; two.random = four;
        three.next = four; three.random = one;
        four.random = one;
        System.out.println(one);
        System.out.println(one.showRandomChain());
    }
    private static void testRandom() {
        RandomListNode r = random(10,10);
        System.out.println(r);
        System.out.println(r.showRandomChain());
    }
    public static void main(String[] args) {
        //testToString();
        testRandom();
    }
}
