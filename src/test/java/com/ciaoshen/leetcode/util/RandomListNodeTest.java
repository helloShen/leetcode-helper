package com.ciaoshen.leetcode.util;

import java.util.Random;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomListNodeTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomListNodeTest.class);
    private static RandomListNode list;

    /**
     * Creat 1 RandomListNode
     * 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
     * Note: the random pointer is randomly generated
     * 
     * @author Wei SHEN
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        int size = 7;
        Random r = new Random();
        RandomListNode[] nodes = new RandomListNode[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new RandomListNode(i);
        }
        for (int i = 0; i < size - 1; i++) {
            nodes[i].next = nodes[i + 1];
            nodes[i].random = nodes[r.nextInt(size)];
        }
        list = nodes[0];
    }

    @Test
    public void test() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("list = {}", list);
        }
        assertThat(list.next.next.next.label, is(equalTo(3)));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Class RandomListNode passed junit test!");
        }
    }
}