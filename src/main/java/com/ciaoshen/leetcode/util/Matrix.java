/**
 * Toolkits for Matrix
 */
package com.ciaoshen.leetcode.util;

public class Matrix {
    public static void print(int[][] m) {
        if (m.length == 0) { System.out.println("This Matrix is Empty!"); return; }
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.print("\n\n");
        }
    }
    public static void print(char[][] m) {
        if (m.length == 0) { System.out.println("This Matrix is Empty!"); return; }
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.print("\n\n");
        }
    }
    public static void print(boolean[][] m) {
        if (m.length == 0) { System.out.println("This Matrix is Empty!"); return; }
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.print("\n\n");
        }
    }
    public static void print(String[][] m) {
        if (m.length == 0) { System.out.println("This Matrix is Empty!"); return; }
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.print("\n\n");
        }
    }
}
