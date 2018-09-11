/**
 * Leetcode - Data Structure - Tree Link Node
 */
package com.ciaoshen.leetcode.util;
import java.util.*;

public class TreeLinkNode {
    public int val;
    public TreeLinkNode left;
    public TreeLinkNode right;
    public TreeLinkNode next;
    public TreeLinkNode(int num) { val = num; }
    public String toString() {
        return bfs().toString();
    }
    public List<List<Integer>> bfs() {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<TreeLinkNode> buffer = new ArrayList<>();
        buffer.add(this);
        while (!buffer.isEmpty()) {
            List<Integer> nums = new ArrayList<Integer>();
            int size = buffer.size();
            for (int i = 0; i < size; i++) {
                TreeLinkNode node = buffer.remove(0);
                if (node != null) {
                    nums.add(node.val);
                    buffer.add(node.left);
                    buffer.add(node.right);
                } else {
                    nums.add(null);
                }
            }
            if (!nums.isEmpty()) { res.add(nums); }
        }
        return res;
    }
    public int[] preOrderToArray() {
        TreeLinkNode cur = this;
        List<Integer> res = new ArrayList<>();
        preorder(cur,res);
        return listToArray(res);
    }
    private static void preorder(TreeLinkNode root, List<Integer> res) {
        if (root == null) { return; }
        res.add(root.val);
        preorder(root.left,res);
        preorder(root.right,res);
    }
    public int[] inOrderToArray() {
        TreeLinkNode cur = this;
        List<Integer> res = new ArrayList<>();
        inorder(cur,res);
        return listToArray(res);
    }
    private static void inorder(TreeLinkNode root, List<Integer> res) {
        if (root == null) { return; }
        inorder(root.left,res);
        res.add(root.val);
        inorder(root.right,res);
    }
    public int[] postOrderToArray() {
        TreeLinkNode cur = this;
        List<Integer> res = new ArrayList<>();
        postorder(cur,res);
        return listToArray(res);
    }
    private static void postorder(TreeLinkNode root, List<Integer> res) {
        if (root == null) { return; }
        postorder(root.left,res);
        postorder(root.right,res);
        res.add(root.val);
    }
    private static int[] listToArray(List<Integer> list) {
        int[] nums = new int[list.size()];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = list.get(i);
        }
        return nums;
    }
    private static final Random R = new Random();
    /**
     * 返回一个最大深度不超过5的BST
     * @param  max [元素可能的最大值范围]
     */
    private static final int BST_DEPTH = 5;
    public static TreeLinkNode randomBST(int max) {
        return randomBSTRecur(1,max,BST_DEPTH);
    }
    private static TreeLinkNode randomBSTRecur(int min, int max, int depth) {
        if (depth == 0 || min > max) { return null; }
        int num = min + R.nextInt(max-min+1);
        TreeLinkNode root = new TreeLinkNode(num);
        root.left = randomBSTRecur(min,num-1,depth-1);
        root.right = randomBSTRecur(num+1,max,depth-1);
        return root;
    }
    /**
     * 返回一个完美二叉树，深度由参数level控制。但深度最大不可超过8。
     * @param  level [控制二叉树的深度。不可超过5。]
     */
    private static final int MAX_LEVEL = 5;
    private static final int MAX_NUM = 50;
    public static TreeLinkNode randomStd(int level) {
        if (level < 0) { level = 1; }
        if (level > MAX_LEVEL) { level = MAX_LEVEL; }
        int max = MAX_NUM;
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= max; i++) {
            nums.add(i);
        }
        return randomStdRecur(level,nums,max);
    }
    private static TreeLinkNode randomStdRecur(int levRemain, List<Integer> nums, int max) {
        if (levRemain == 0) { return null; }
        int num = 0;
        do {
            num = R.nextInt(max) + 1;
        } while (!nums.contains(num));
        nums.remove(nums.indexOf(new Integer(num)));
        TreeLinkNode root = new TreeLinkNode(num);
        root.left = randomStdRecur(levRemain-1,nums,max);
        root.right = randomStdRecur(levRemain-1,nums,max);
        return root;
    }
    private static void testRandomBST() {
        TreeLinkNode tree = randomBST(20);
        System.out.println(tree.bfs());
    }
    private static void testRandomStd() {
        TreeLinkNode tree = randomStd(5);
        System.out.println(tree.bfs());
    }
    private static void testToArray() {
        TreeLinkNode tree = randomStd(4);
        System.out.println(tree.bfs());
        System.out.println("preorder: " + Arrays.toString(tree.preOrderToArray()));
        System.out.println("inorder: " + Arrays.toString(tree.inOrderToArray()));
        System.out.println("postorder: " + Arrays.toString(tree.postOrderToArray()));
    }
    public static void main(String[] args) {
        //testRandomBST();
        //testRandomStd();
        //testToArray();
    }
}
