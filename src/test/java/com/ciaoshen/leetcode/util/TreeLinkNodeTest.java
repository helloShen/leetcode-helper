package com.ciaoshen.leetcode.util;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TreeLinkNodeTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TreeLinkNodeTest.class);
    private static TreeLinkNode head;

    /**
     * Creat a perfect binary tree of TreeLinkedNode of size 7.
     * 
     *           1 -> NULL
     *         /  \
     *        2 -> 3 -> NULL
     *       / \  / \
     *      4->5->6->7 -> NULL
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        int size = 7;
        TreeLinkNode[] nodeBank = new TreeLinkNode[size];
        for (int i = 0; i < size; i++) {
            nodeBank[i] = new TreeLinkNode(i+1);
        }
        for (int i = 0; (i * 2 + 1) < size; i++) {
            nodeBank[i].left = nodeBank[i * 2 + 1];
            if (i * 2 + 2 < size) {
                nodeBank[i].right = nodeBank[i * 2 + 2];
            }
        }
        nodeBank[1].next = nodeBank[2]; 
        nodeBank[3].next = nodeBank[4];
        nodeBank[4].next = nodeBank[5];
        nodeBank[5].next = nodeBank[6];
        head = nodeBank[0];
    }

    @Test
    public void testToString() {
        String expected = "[[1], [2, 3], [4, 5, 6, 7]]";
        String actual = head.toString();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("TreeLinkNodeTest.toString() return: {}", actual);
            LOGGER.debug("Expected String is: {}", expected);
        }
        assertThat(actual, is(equalTo(expected)));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("TreeLinkNode#toString() method passed junit test!");
        }
    }
}