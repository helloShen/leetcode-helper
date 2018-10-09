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
 * NestedInteger for Leetcode problem: Flatten Nested List Iterator
 * ex:
 *      [[1,1],2,[1,1]]
 *      [1,[4,[6]]]
 * Related Problem:
 *     #341 - Flatten Nested List Iterator
 *     #385 - Mini Parser 
 * 
 * @author Wei SHEN
 */
public class NestedInteger {

    public Integer num = null;
    public List<NestedInteger> nums = null;

    public NestedInteger() {
        nums = new ArrayList<NestedInteger>();
    }
    public NestedInteger(int num) {
        this.num = num;
    }
    public boolean isInteger() {
        return nums == null;
    }
    public Integer getInteger() {
        return num;
    }
    public List<NestedInteger> getList() {
        return nums;
    }
    public boolean add(NestedInteger n) {
        if (isInteger()) {
            return false;
        } else {
            nums.add(n);
            return true;
        }
    }

    /** Serialization */
    public String toString() {
        if (isInteger()) {
            return "" + num;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (NestedInteger ni : nums) {
                sb.append(ni.toString());
                sb.append(",");
            }
            int len = sb.length();
            sb.delete(len-1,len);
            sb.append("]");
            return sb.toString();
        }
    }

}
