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
 * TreeNode is the node of a binary tree.
 * 
 * @author Wei SHEN
 */
public class TreeNode {

    /**
     *     val
     *     / \
     *  left right
     */
    public TreeNode left;
    public TreeNode right;
    public int val;

    // create node with a specific value
    public TreeNode(int x) { val = x; }

    /**
     * Serialization: print the nodes in BFS order
     */
    public String toString() {
        return bfs().toString();
    }

    /**
     * Parse the tree in BFS order
     * @return List of Integer
     * 
     * For example, given the following tree,
     *      a
     *     / \
     *    b  null
     * output = [[a],[b,null]]
     * null node will be listed in the output
     */
    private List<List<Integer>> bfs() {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<TreeNode> buffer = new ArrayList<>();
        buffer.add(this);
        while (!buffer.isEmpty()) {
            List<Integer> line = new ArrayList<>();
            int size = buffer.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = buffer.remove(0);
                if (node == null) {
                    line.add(null);
                } else {
                    line.add(node.val);
                    buffer.add(node.left);
                    buffer.add(node.right);
                }
            }
            if (line.isEmpty()) continue;
            for (Integer n : line) { // ignore the bottom line with all null nodes
                if (n != null) res.add(line);
                break;
            }
        }
        return res;
    }

}