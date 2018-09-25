/**
 * MIT License
 *
 * Copyright (c) 2018 Wei SHEN 

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.ciaoshen.leetcode.util;
import java.util.List;
import java.util.ArrayList;

/**
 * TreeLinkNode is a binary tree with next pointer.
 * For example, a perfect binary tree of 7 TreeLinkedNode looks like,
 * 
 *           1 -> NULL
 *         /  \
 *        2 -> 3 -> NULL
 *       / \  / \
 *      4->5->6->7 -> NULL
 * 
 * Related problem:
 * Problem #116 - Populating Next Right Pointers in Each Node
 * 
 * @author Wei SHEN
 */
public class TreeLinkNode {

    /**============================== 【public members】 ============================== */
    
    public int val;
    public TreeLinkNode left;
    public TreeLinkNode right;
    public TreeLinkNode next;

    /** 
     * Constructor
     * @param x value of this node.
     */
    public TreeLinkNode(int x) {
        val = x;
    }

    /** 
     * Serialization (in BFS order)
     */
    public String toString() {
        return bfs().toString();
    }


    /**============================== 【private members】 ============================== */
    
    /** parse the tree in BFS order */
    private List<List<Integer>> bfs() {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<TreeLinkNode> buffer = new ArrayList<>();
        buffer.add(this);
        while (!buffer.isEmpty()) {
            List<Integer> thisLevel = new ArrayList<Integer>();
            int size = buffer.size();
            for (int i = 0; i < size; i++) {
                TreeLinkNode node = buffer.remove(0);
                if (node != null) {
                    thisLevel.add(node.val);
                    buffer.add(node.left);
                    buffer.add(node.right);
                } else {
                    thisLevel.add(null);
                }
            }
            if (thisLevel.isEmpty()) continue;
            for (Integer n : thisLevel) { // eliminate the bottom row with all null nodes
                if (n != null) {
                    res.add(thisLevel);
                    break;
                }
            }
        }
        return res;
    }

}