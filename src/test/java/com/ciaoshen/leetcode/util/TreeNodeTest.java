package com.ciaoshen.leetcode.util;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TreeNodeTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TreeNodeTest.class);
    private static TreeNode root;

    /**
     * Creat a perfect binary tree of 3 levels.
     *           1 
     *         /  \
     *        2    3 
     *       / \  / \
     *      4  5  6  7 
     * 
     * @author Wei SHEN
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        int size = 7;
        TreeNode[] nodes = new TreeNode[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new TreeNode(i+1);
        }
        for (int i = 0; (i * 2 + 1) < size; i++) {
            nodes[i].left = nodes[i * 2 + 1];
            if (i * 2 + 2 < size) {
                nodes[i].right = nodes[i * 2 + 2];
            }
        }
        root = nodes[0];
    }

    @Test
    public void test() {
        String expected = "[[1], [2, 3], [4, 5, 6, 7]]";
        String actual = root.toString();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("value of root is = {}", root.val);
            LOGGER.debug("Actual = {}", actual);
            LOGGER.debug("Expected = {}", expected);
        }
        assertThat(actual, is(equalTo(expected)));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Class TreeNode passed junit test!");
        }
    }
}