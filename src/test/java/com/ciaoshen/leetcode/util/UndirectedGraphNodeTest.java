package com.ciaoshen.leetcode.util;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UndirectedGraphNodeTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UndirectedGraphNodeTest.class);
    private static UndirectedGraphNode graph;

    /**
     * The constructor create a graph with three nodes, 
     * {0,1,2#1,2#2,2}
     * which looks like the following:
     *     1
     *    / \
     *   /   \
     *  0 --- 2
     *       / \
     *       \_/
     * 
     * @author Wei SHEN
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        UndirectedGraphNode[] nodes = new UndirectedGraphNode[3];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new UndirectedGraphNode(i);
        }
        nodes[0].neighbors.add(nodes[1]);
        nodes[0].neighbors.add(nodes[2]);
        nodes[1].neighbors.add(nodes[2]);
        nodes[2].neighbors.add(nodes[2]);
        graph = nodes[0];
    }

    @Test
    public void test() {
        UndirectedGraphNode neighborOne = graph.neighbors.get(0);
        UndirectedGraphNode neighborTwo = graph.neighbors.get(1);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Node [{}] has 2 neighbors: [{}] and [{}]", graph.label, neighborOne.label, neighborTwo.label);
        }
        int expectedLabel = 0;
        int expectedNeighborOne = 1;
        int expectedNeighborTwo = 2;
        assertThat(graph.label, is(equalTo(expectedLabel)));
        assertThat(neighborOne.label, is(equalTo(expectedNeighborOne)));
        assertThat(neighborTwo.label, is(equalTo(expectedNeighborTwo)));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Class UndirectedGraphNode passed junit test!");
        }
    }
}