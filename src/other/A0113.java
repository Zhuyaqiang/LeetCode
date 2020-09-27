package other;

import java.util.ArrayList;
import java.util.List;

/**
 * 路径总和
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */
public class A0113 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null)
            return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        backtrack(root, new ArrayList<>(), res, 0, sum);
        return res;
    }
    public void backtrack(TreeNode root, List<Integer> curr, List<List<Integer>> res, int currSum, int sum) {
        currSum += root.val;
        if (currSum > sum)
            return;
        curr.add(root.val);
        if (root.left == null && root.right == null) {
            if (currSum == sum) {
                res.add(new ArrayList<>(curr));
                return;
            }
        }
        if (root.left == null)
            backtrack(root.right, curr, res, currSum, sum);
        if (root.right == null)
            backtrack(root.left, curr, res, currSum, sum);
        curr.remove(curr.size() - 1);
    }
}
