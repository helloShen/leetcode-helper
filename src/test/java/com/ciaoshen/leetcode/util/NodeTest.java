package com.ciaoshen.leetcode.util;

import java.util.Random;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NodeTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NodeTest.class);
    private static Node root;

    /**
     * 
     * Create the following Binary Search Tree:
     *           4
     *          / \
     *         2   5
     *        / \
     *       1   3
     * @author Wei SHEN
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        Node one = new Node(1, null, null);
        Node three = new Node(3, null, null);
        Node five = new Node(5, null, null);
        Node two = new Node(2, one, three);
        Node four = new Node(4, two, five);
        root = four;
    }

    @Test
    public void test() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Tree = {}", root.toString());
        }
        assertThat(root.left.left.val, is(equalTo(1)));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Smallest node = {}", root.left.left.val);
            LOGGER.debug("Class Node passed junit test!");
        }
    }
}