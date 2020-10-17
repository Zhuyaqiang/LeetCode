package other;

/**
 * 二叉搜索树的最小绝对差
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 * 示例：
 * 输入：
 *
 *    1
 *     \
 *      3
 *     /
 *    2
 *
 * 输出：
 * 1
 * 解释：
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 *
 */
public class A0530 {
    public int res = Integer.MAX_VALUE, pre = -1;
    public int getMinimumDifference(TreeNode root) {
        backtrack(root);
        return res;
    }
    public void backtrack(TreeNode root) {
        if (root == null)
            return;
        backtrack(root.left);
        if (pre == -1) {
            pre = root.val;
        } else {
            int val = Math.abs(pre - root.val);
            res = Math.min(res, val);
            pre = root.val;
            if (res == 0)
                return;
        }
        backtrack(root.right);
    }
}
