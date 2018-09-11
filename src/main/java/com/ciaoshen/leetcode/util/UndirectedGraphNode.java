/**
 * Leetcode - Basic Data Structure - Undirected Graph Node
 */
package com.ciaoshen.leetcode.util;
import java.util.*;

public class UndirectedGraphNode {
    public int label;
    public List<UndirectedGraphNode> neighbors;
    public UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
    // 相等的条件是：两个节点的label相等，并且有完全相同的一组neighbors(==)。
    public boolean equals(Object o) {
        if (o == null || !(o instanceof UndirectedGraphNode)) { return false; }
        UndirectedGraphNode another = (UndirectedGraphNode)o;
        if (label != another.label) { return false; }
        if (neighbors.size() != another.neighbors.size()) { return false; }
        outFor:
        for (int i = 0; i < neighbors.size(); i++) {
            UndirectedGraphNode node = neighbors.get(i);
            inFor:
            for (int j = 0; j < another.neighbors.size(); j++) {
                if (another.neighbors.get(j) == node) { continue outFor; }
            }
            return false; // 有一个neighbor匹配不上，就不相等。
        }
        return true;
    }
    // 用自身的label和所有neighbors的label计算散列值。
    public int hashCode() {
        int hash = 17 * 31 + label;
        for (UndirectedGraphNode node : neighbors) {
            hash = hash * 31 + node.label;
        }
        return hash;
    }
    public String toString() {
        return dfs(this,new ArrayList<UndirectedGraphNode>());
    }
    private String dfs(UndirectedGraphNode node, List<UndirectedGraphNode> memo) {
        String res = "";
        if (memo.contains(node)) { return res; }
        res = res + "#" + node.label;
        memo.add(node);
        for (UndirectedGraphNode nbs : node.neighbors) { res = res + "," + nbs.label; }
        for (UndirectedGraphNode nbs : node.neighbors) { res = res + dfs(nbs,memo); }
        return res;
    }

    /**
     * 测试区
     */
    private static void testEquals() {
        UndirectedGraphNode firstOne = new UndirectedGraphNode(1);
        UndirectedGraphNode secondOne = new UndirectedGraphNode(1);
        UndirectedGraphNode firstTwo = new UndirectedGraphNode(2);
        UndirectedGraphNode secondTwo = new UndirectedGraphNode(2);
        UndirectedGraphNode origin = new UndirectedGraphNode(3);
        UndirectedGraphNode right = new UndirectedGraphNode(3);
        UndirectedGraphNode wrong = new UndirectedGraphNode(3);
        origin.neighbors.add(firstOne);
        origin.neighbors.add(firstTwo);
        right.neighbors.add(firstTwo);
        right.neighbors.add(firstOne);
        wrong.neighbors.add(secondOne);
        wrong.neighbors.add(secondTwo);
        System.out.println("origin equals right?" + origin.equals(right));
        System.out.println("origin equals wrong?" + origin.equals(wrong));
    }
    private static void testToString() {
        UndirectedGraphNode one = new UndirectedGraphNode(1);
        UndirectedGraphNode two = new UndirectedGraphNode(2);
        UndirectedGraphNode three = new UndirectedGraphNode(3);
        UndirectedGraphNode four = new UndirectedGraphNode(4);
        one.neighbors.add(two);
        one.neighbors.add(three);
        two.neighbors.add(four);
        four.neighbors.add(two);
        System.out.println(one);
    }
    public static void main(String[] args) {
        //testEquals();
        testToString();
    }
};
