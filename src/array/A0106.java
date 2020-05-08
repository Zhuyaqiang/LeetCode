package array;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 从中序与后序遍历序列构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 * 例如，给出
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class A0106 {
    static int[] inorders;
    static int[] postorders;
    static int postIndex;
    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        TreeNode treeNode = buildTree(inorder, postorder);
        System.out.println();
        rBuildTree(inorder, postorder);
        display(treeNode);
    }

    // 与105区别在于递归时先进行右递归
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        inorders = inorder;
        postorders = postorder;
        postIndex = postorder.length - 1;
        for (int i = 0; i < inorders.length; i++)
            map.put(inorders[i], i);
        return backtrack(0, inorders.length - 1);
    }

    public static TreeNode backtrack(int left, int right) {
        if (left > right)
            return null;
        int rootVal = postorders[postIndex];
        TreeNode root = new TreeNode(rootVal);

        int index = map.get(rootVal);

        postIndex--;

        System.out.println(left + " " + right + " " + index);
        root.right = backtrack(index + 1, right);
        root.left = backtrack(left, index - 1);
        return root;
    }

    public static TreeNode rBuildTree(int[] inorder, int[] postorder) {
        inorders = inorder;
        postorders = postorder;
        postIndex = postorder.length-1;
        for (int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);
        return rBacktrack(0, postorder.length-1);
    }
    public static TreeNode rBacktrack(int left, int right) {
        if (left > right)
            return null;
        int val = postorders[postIndex];
        int inIndex = map.get(val);
        postIndex--;
        TreeNode root = new TreeNode(val);
        System.out.println(left + " " + right + " " + inIndex);
        root.right = rBacktrack(inIndex + 1, right);
        root.left = rBacktrack(left, inIndex - 1);
        return root;
    }

    public static void display(TreeNode tree) {
        if (tree == null)
            return;
        display(tree.left);
        display(tree.right);
        System.out.println(tree.val);
    }
}
