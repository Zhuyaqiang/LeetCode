package array;

import java.util.HashMap;
import java.util.Map;

/**
 * 从前序与中序遍历构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class A0105 {
    static int preIndex = 0;
    static int[] preorders;
    static int[] inorders;
    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};

        TreeNode treeNode = buildTree(preorder, inorder);
        play(treeNode);
    }
    public static void play(TreeNode tree) {
        if (tree == null)
            return;
        play(tree.left);
        System.out.println(tree.val);
        play(tree.right);
    }
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        preorders = preorder;
        inorders = inorder;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return backtrack(0, preorder.length);
    }
    static TreeNode backtrack(int start, int end) {
        if (start == end)
            return null;
        int currVal = preorders[preIndex];
        TreeNode root = new TreeNode(currVal);

        int inIndex = map.get(currVal);
        preIndex++;
        root.left = backtrack(start, inIndex);
        root.right = backtrack(inIndex + 1, end);
        return root;
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
