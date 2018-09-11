/**
 * NestedInteger for Leetcode problem: Flatten Nested List Iterator
 */
package com.ciaoshen.leetcode.util;
import java.util.*;

public class NestedInteger {

    /** 构造函数 */
    public NestedInteger() {
        nums = new ArrayList<NestedInteger>();
    }
    public NestedInteger(int num) {
        this.num = num;
    }
    public NestedInteger(int[] in) {
        nums = new ArrayList<NestedInteger>();
        for (int num : in) {
            nums.add(new NestedInteger(num));
        }
    }
    public NestedInteger(String s) {
        NestedInteger created = deserialize(s);
        if (created.isInteger()) {
            this.num = created.getInteger();
        } else {
            this.nums = created.getList();
        }
    }

    /** 公开接口 */
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

    /** 序列化 */
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

    /**
     * 反序列化。要求输入格式如下：
     * 1. 输入String不能为空
     * 2. 不能有空格
     * 3. 只能包含以下字符：
     *      '0~9'
     *      ','
     *      '-'
     *      '['
     *      ']'
     *
     * 下面几个例子是合法的：
     *      "[123,[456,[789]]]"
     *      "123"
     *      "[-123,[],[[]]]"
     *
     *  !注：单个"-"表示0
     */
    private static NestedInteger deserialize(String s) {
        int sign = 1;
        Integer num = null;
        Deque<NestedInteger> stack = new LinkedList<>();
        NestedInteger dummy = new NestedInteger();
        NestedInteger curr = dummy;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                NestedInteger newNI = new NestedInteger();
                curr.add(newNI);
                stack.push(curr);
                curr = newNI;
            } else if (c == ']' || c == ',') {
                if (num != null) {
                    curr.add(new NestedInteger(num * sign));
                    num = null;
                    sign = 1;
                }
                if (c == ']') {
                    curr = stack.pop();
                }
            } else if (c =='-') {
                num = 0;
                sign = -1;
            } else { // is digit
                if (num == null) {
                    num = 0;
                }
                num = num * 10 + (c - '0');
            }
        }
        if (num != null) { // dump
            curr.add(new NestedInteger(num * sign));
        }
        return dummy.getList().get(0);
    }


    /** 私有字段，实际数据 */
    private Integer num = null;
    private List<NestedInteger> nums = null;

}
