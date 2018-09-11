/**
 * Structure Interval: period of time, start at "start", end at "end"
 */
package com.ciaoshen.leetcode.util;

public class Interval {
    public int start;
    public int end;
    public Interval() { start = 0; end = 0; }
    public Interval(int s, int e) { start = s; end = e; }
    @Override
    public String toString() {
        return "[" + start + "," + end + "]";
    }
    public static Interval[] intervals(int[][] matrix) {
        Interval[] result = new Interval[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            result[i] = new Interval(matrix[i][0],matrix[i][1]);
        }
        return result;
    }
}
