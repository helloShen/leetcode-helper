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

/**
 * The random linked list is a singly linked list given 
 * such that each node contains an additional random pointer 
 * which could point to any node in the list or null.
 * 
 * @author Wei SHEN
 */
public class RandomListNode {

    public int label;
    public RandomListNode next, random;

    /**
     * Constructor
     * @param x input label value
     */
    public RandomListNode(int x) { 
        label = x; 
    }

    /**
     * Serialization
     */
    public String toString() {
        RandomListNode cur = this;
        String str = "";
        while (cur != null) {
            str += cur.label;
            if (cur.next != null) {
                str += ("[" + cur.random.label + "]");
                str += "->";
            } else {
                str += "[null]";
            }
            cur = cur.next;
        }
        return str;
    }

}