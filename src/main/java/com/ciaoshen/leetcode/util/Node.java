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

import java.util.*;

/**
 * 426. Convert Binary Search Tree to Sorted Doubly Linked List
 * Binary Search Tree looks like:
 *           4
 *          / \
 *         2   5
 *        / \
 *       1   3
 * 
 * @author Wei SHEN
 */
public class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}
    public Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        List<Node> list = new ArrayList<>();
        list.add(this);
        while (!list.isEmpty()) {
            List<Integer> nums = new ArrayList<>();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Node node = list.remove(0);
                nums.add((node == null)? null : node.val);
                if (node != null) {
                    list.add(node.left);
                    list.add(node.right);
                }
            }
            if (nums.isEmpty()) continue;
            for (int i = 0; i < nums.size(); i++) {
                if (nums.get(i) != null) {
                    sb.append(nums.toString() + ", ");
                    break;
                }
            }
        }
        if (sb.length() > 2) sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }

}
