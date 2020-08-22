package other;

/**
 * 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 */
public class A0110 {
    public boolean isBalanced(TreeNode root) {
        return backtrack(root, 0, 0) >= 0;
    }
    public int backtrack(TreeNode root, int currHeight, int stopHeight) {
        if (root == null)
            return currHeight;
        int rightRes = backtrack(root.right, currHeight + 1, stopHeight);
        int leftRes = backtrack(root.left, currHeight + 1, stopHeight);
        if (leftRes < 0 || rightRes < 0)
            return -1;
        int val = Math.abs(leftRes - rightRes);
        if (val <= 1)
            return Math.max(leftRes, rightRes);
        else
            return -1;
    }
}
