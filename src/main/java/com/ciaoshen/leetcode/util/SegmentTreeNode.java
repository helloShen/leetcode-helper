/**
 * 线段树
 * 用于Range Sum Query - Mutable这道题
 * 线段树专门用来计算区域内的累计值
 */
package com.ciaoshen.leetcode.util;
import java.util.*;

public class SegmentTreeNode {
    public int start,end;
    public SegmentTreeNode left,right;
    public int val;

    public SegmentTreeNode(int val) {
        this.val = val;
    }

    public String toString() {
        List<SegmentTreeNode> nodesInThisLevel = new ArrayList<>();
        nodesInThisLevel.add(this);
        int size = nodesInThisLevel.size();
        StringBuilder sb = new StringBuilder();
        while (size > 0) {
            for (int i = 0; i < size; i++) {
                SegmentTreeNode node = nodesInThisLevel.remove(0);
                sb.append(nodeToString(node) + "\t");
                if (node != null) {
                    nodesInThisLevel.add(node.left);
                    nodesInThisLevel.add(node.right);
                }
            }
            sb.append("\n");
            sb.append("====================================\n");
            size = nodesInThisLevel.size();
        }
        return new String(sb);
    }
    private String nodeToString(SegmentTreeNode node) {
        return (node == null)? "{NULL}" : "{" + node.start + "<--[" + node.val + "]-->" + node.end +"}";
    }

}
