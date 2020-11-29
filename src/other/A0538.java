package other;

/**
 * 把二叉搜索树转换为累加树
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它
 * 的节点值之和。
 * 例如：
 * 输入: 原始二叉搜索树:
 *               5
 *             /   \
 *            2     13
 *
 * 输出: 转换为累加树:
 *              18
 *             /   \
 *           20     13
 */
public class A0538 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(0);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(-4);
        root.left.right = new TreeNode(1);
        convertBST(root);
    }
    public static TreeNode convertBST(TreeNode root) {
        backtrack(root);
        return root;
    }
    public static int curr = 0;
    public static void backtrack(TreeNode root) {
        if (root == null)
            return;
        backtrack(root.right);
        root.val += curr;
        curr = root.val;
        backtrack(root.left);
    }
}
