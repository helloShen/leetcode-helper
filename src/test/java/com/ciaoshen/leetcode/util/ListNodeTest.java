package com.ciaoshen.leetcode.util;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListNodeTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListNodeTest.class);
    private static ListNode head1;
    private static ListNode head2;

    /**
     * Creat 2 lists:
     * list1 = "2 -> 4 -> 6 -> 8 -> 10"
     * list2 = "0"
     * The second list is empty
     * @author Wei SHEN
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        // list 1
        int size = 5;
        ListNode[] nodes = new ListNode[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new ListNode((i + 1) * 2);
        }
        for (int i = 0; i < size - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }
        head1 = nodes[0];
        // list 2
        ListNode zero = new ListNode(0);
        head2 = zero;
    }

    @Test
    public void test() {
        String expected = "2->4->6->8->10";
        String actual = head1.toString();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Actual = {}", actual);
            LOGGER.debug("Expected = {}", expected);
        }
        assertThat(actual, is(equalTo(expected)));
        expected = "0";
        actual = head2.toString();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Actual = {}", actual);
            LOGGER.debug("Expected = {}", expected);
        }
        assertThat(actual, is(equalTo(expected)));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Class ListNode passed junit test!");
        }
    }
}