package other;

/**
 * 给定一个非空二叉树，返回其最大路径和。
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 * 示例 1:
 * 输入: [1,2,3]
 * <p>
 * 1
 * / \
 * 2   3
 * <p>
 * 输出: 6
 * 示例 2:
 * 输入: [-10,9,20,null,null,15,7]
 * <p>
 * -10
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 输出: 42
 */
public class A0124 {
    public int max = Integer.MIN_VALUE;

    public int rMaxPathSum(TreeNode root) {
        rRecursion(root);
        return max;
    }
    public int rRecursion(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = rRecursion(root.left);
        int right = rRecursion(root.right);
        max = Math.max(max, root.val + left + right);
        return Math.max(left, right) + root.val;
    }

    public int maxPathSum(TreeNode root) {
        backtrack(root);
        return max;
    }

    // backtrack计算参数root节点的最大贡献值（要么是root节点的值+其左子树的最大贡献值， 要么是root节点的值+其右子树的最大贡献值）
    // 同时更新max结果，为root节点的值加上其左右节点的最大贡献值
    public int backtrack(TreeNode root) {
        if (root == null)
            return 0;

        int left = Math.max(0, backtrack(root.left)); // 左子节点的最大贡献值
        int right = Math.max(0, backtrack(root.right));

        max = Math.max(max, left + right + root.val);

        return root.val + Math.max(left, right);
    }
}
