/**
 * Min Heap
 */
package com.ciaoshen.leetcode.util;
import java.util.*;

public class MinHeap {

    public MinHeap(int size) {
        heap = new int[size+1];
        p = 1;
    }
    /**
     * return min value in heap and update the heap
     * @return min value in heap (current root node)
     */
    public int getMin() {
        int min = heap[1];
        heap[1] = heap[--p];
        minHelper(1);
        return min;
    }
    // bubble-down the pseudo-root
    private void minHelper(int root) {
        int left = root * 2, right = left + 1;
        // 当左右子节点中至少有一个大于父节点时
        if ((left < p && heap[left] < heap[root]) || (right < p && heap[right] < heap[root])) {
            // 优先考虑换左节点，只有当右节点确实比左节点小才考虑换右节点
            if (right < p && heap[left] > heap[right]) {
                swap(root,right);
                minHelper(right);
            } else {
                swap(root,left);
                minHelper(left);
            }
        }
    }
    // insert a new number at the end of array and bubble-up it
    public void insert(int val) {
        int curr = p, parent = p / 2;
        heap[p++] = val;
        // 如果新节点值小于其父节点，冒泡
        while (parent > 0 && heap[curr] < heap[parent]) {
            swap(curr,parent);
            curr = parent;
            parent = curr / 2;
        }
    }
    public boolean isEmpty() {
        return p <= 1;
    }
    public String toString() {
        return Arrays.toString(heap);
    }

    private int[] heap;
    private int p;

    private void swap(int a, int b) {
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }
}
