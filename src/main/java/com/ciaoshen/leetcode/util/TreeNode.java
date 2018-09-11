/**
 * Binary Tree Node
 */
package com.ciaoshen.leetcode.util;
import java.util.*;

public class TreeNode {

    /**
     *     val
     *     / \
     *  left right
     */
    public TreeNode left;
    public TreeNode right;
    public int val;


    //默认节点值为0
    public TreeNode() { val = 0; }
    //根据给定值创造节点对象
    public TreeNode(int x) { val = x; }


    /**
     * 【公开服务接口】
     * 默认以BFS按行打印节点，取消注释可随时修改策略，
     * 备选策略包括，
     *      1.bfs():        [[level-one][level-two]]
     *      2.preOrder():   [root,left,right]
     *      3.inOrder():    [left,root,right]
     *      4.postOrder():  [left,right,root]
     */
    public String toString() {
        return bfs().toString();
        // return preOrder().toString();
        // return inOrder().toString();
        // return postOrder().toString();
    }


    /**
     * 【公开服务接口】
     * pre-order
     * 按[root-left-right]顺序排列二叉树中节点
     *      a
     *     / \
     *  null c
     * 输出：[a,null,c]
     * 显示null
     */
    public List<Integer> preOrder() {
        List<Integer> res = new ArrayList<>();
        preOrderRecur(res,this);
        return res;
    }


    /**
     * 【公开服务接口】
     * in-order
     * 按[left-root-right]顺序排列二叉树中节点
     *      a
     *     / \
     *  null c
     * 输出：[null,a,c]
     * 显示null
     */
    public List<Integer> inOrder() {
        List<Integer> res = new ArrayList<>();
        inOrderRecur(res,this);
        return res;
    }


    /**
     * 【公开服务接口】
     * post-order
     * 按[left-right-root]顺序排列二叉树中节点
     *      a
     *     / \
     *  null c
     * 输出：[null,c,a]
     * 显示null
     */
    public List<Integer> postOrder() {
        List<Integer> res = new ArrayList<>();
        postOrderRecur(res,this);
        return res;
    }


    /**
     * 【公开服务接口】
     * 获取按行排列（BFS）的树的结构
     * @return 返回一个List
     *      a
     *     / \
     *    b  null
     * 输出：[[a],[b,null]]
     * 显示null节点
     */
    public List<List<Integer>> bfs() {
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
            res.add(line);
        }
        return res;
    }


    /**
     * 【公开服务接口】
     * 根据客户提供的字符串生成一棵二叉树。
     * @return 返回这棵树的根节点
     *
     * 目前支持的二叉树字符串表达模式：
     *      [1,2,3,null,5,6]
     *  1. 用逗号","分割数字，数字前后可以有空格" "
     *  2. 外面可用方括号"[]"括起来
     *  3. "null"代表空节点
     *
     * 如需修改字符串表达模式，可以配置后面的静态私有常量
     *      SPLITER, USEBRACKETS, BRACKETS, NULLNODE
     */
     public static TreeNode stringToTreeNode(String input) {
         //清理空格和外层括号（有弹性，没有就不处理）
         if (input == null) { return null; }
         input = input.trim();
         if (USEBRACKETS && input.length() >=2) {
             if (input.charAt(0) == BRACKETS[0] && input.charAt(input.length()-1) == BRACKETS[1]) {
                 input = input.substring(1,input.length()-1);
             }
         }
         //切割
         String[] items = input.split(SPLITER);
         if (items.length == 0) { return null; }
         //插入首元素作为启动
         List<TreeNode> list = new ArrayList<>();
         String first = items[0].trim();
         if (first.equals(NULLNODE) || first.equals("")) { return null; }
         TreeNode root = new TreeNode(Integer.parseInt(first));
         list.add(root);
         int itemsP = 1;
         //BFS
         while (!list.isEmpty() && itemsP < items.length) {
             TreeNode node = list.remove(0);
             String left = items[itemsP++].trim();
             if (!left.equals(NULLNODE) && !left.equals("")) {
                 node.left = new TreeNode(Integer.parseInt(left));
                 list.add(node.left);
             }
             if (itemsP >= items.length) { break; }
             String right = items[itemsP++].trim();
             if (!right.equals(NULLNODE) && !right.equals("")) {
                 node.right = new TreeNode(Integer.parseInt(right));
                 list.add(node.right);
             }
         }
         return root;
     }
     //字符串格式配置字段
     private static final String SPLITER = ",";                     //避免硬编码
     private static final boolean USEBRACKETS = true;               //标准格式是否用括号包裹
     private static final char[] BRACKETS = new char[]{'[',']'};    //括号
     private static final String NULLNODE = "null";                   //避免硬编码



     /**
      * 【公开服务接口】
      * 傻瓜版随机生成一颗二叉查找树（Binary Search Tree），并返回其根节点
      *      1. 默认节点值域为闭区间的[1,100]
      *      2. 默认深度为5
      */
     public static TreeNode randomBST() {
         return recurBST(1,100,5);
     }


     /**
      *
      * 【公开服务接口】
      * 深度定制版的随机生成一颗二叉查找树（Binary Search Tree），并返回其根节点
      * 可以调节：
      *      1.节点值域为闭区间[min,max]
      *      2.树的深度depth
      */
     public static TreeNode randomBST(int min, int max, int depth) {
         return recurBST(min,max,depth);
     }




     //实际执行递归遍历二叉树
     private static void preOrderRecur(List<Integer> list, TreeNode root) {
         if (root == null) { list.add(null); return; }
         list.add(root.val);
         preOrderRecur(list,root.left);
         preOrderRecur(list,root.right);
     }
     private static void inOrderRecur(List<Integer> list, TreeNode root) {
         if (root == null) { list.add(null); return; }
         inOrderRecur(list,root.left);
         list.add(root.val);
         inOrderRecur(list,root.right);
     }
     private static void postOrderRecur(List<Integer> list, TreeNode root) {
         if (root == null) { list.add(null); return; }
         postOrderRecur(list,root.left);
         postOrderRecur(list,root.right);
         list.add(root.val);
     }


    //实际递归生成二叉搜索树（Binary Search Tree）
    private static final Random R = new Random();
    private static TreeNode recurBST(int min, int max, int depth) {
        if (depth <= 0 || min > max) { return null; }
        int num = R.nextInt(max-min+1)+min; //保证闭区间[min,max]
        TreeNode root = new TreeNode(num);
        root.left = recurBST(min,num-1,depth-1);
        root.right = recurBST(num+1,max,depth-1);
        return root;
    }



    /**
     * 单元测试
     */
    private static class Test {
        String line1 = "";
        String line2 = "[1,2,3,4,5,6,7,8,9,10]";
        String line3 = "[3,9,8,4,0,1,7,null,null,null,2,5]";
        String line4 = "[-64,12,18,-4,-53,null,76,null,-51,null,null,-93,3,null,-31,47,null,3,53,-81,33,4,null,-51,-44,-60,11,null,null,null,null,78,null,-35,-64,26,-81,-31,27,60,74,null,null,8,-38,47,12,-24,null,-59,-49,-11,-51,67,null,null,null,null,null,null,null,-67,null,-37,-19,10,-55,72,null,null,null,-70,17,-4,null,null,null,null,null,null,null,3,80,44,-88,-91,null,48,-90,-30,null,null,90,-34,37,null,null,73,-38,-31,-85,-31,-96,null,null,-18,67,34,72,null,-17,-77,null,56,-65,-88,-53,null,null,null,-33,86,null,81,-42,null,null,98,-40,70,-26,24,null,null,null,null,92,72,-27,null,null,null,null,null,null,-67,null,null,null,null,null,null,null,-54,-66,-36,null,-72,null,null,43,null,null,null,-92,-1,-98,null,null,null,null,null,null,null,39,-84,null,null,null,null,null,null,null,null,null,null,null,null,null,-93,null,null,null,98]";
        String[] lines = new String[]{line1,line2,line3,line4};

        private void test() {
            TreeNode bst1 = randomBST();
            TreeNode bst2 = randomBST(1,1000,3);
            TreeNode fromLine1 = stringToTreeNode(line1);
            TreeNode fromLine2 = stringToTreeNode(line2);

            fourWayPrint(bst1);
            fourWayPrint(bst2);
            fourWayPrint(fromLine1);
            fourWayPrint(fromLine2);
        }
        private void fourWayPrint(TreeNode root) {
            if (root == null) { return; }
            System.out.println("BFS: \t" + root.bfs());
            System.out.println("PRE-ORDER: \t" + root.preOrder());
            System.out.println("IN-ORDER: \t" + root.inOrder());
            System.out.println("POST-ORDER: \t" + root.postOrder());
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test();
    }
}
