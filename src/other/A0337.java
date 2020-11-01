package other;

import java.util.HashMap;
import java.util.Map;

/**
 * 打家劫舍
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，
 * 每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 *
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 *
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 */
public class A0337 {
    public Map<TreeNode, Integer> is = new HashMap<>();
    public Map<TreeNode, Integer> not = new HashMap<>();
    public int rRob(TreeNode root) {
        return Math.max(rBacktrack(root, false),rBacktrack(root, true));
    }
    public int rBacktrack(TreeNode root, boolean flag) {
        if (root == null)
            return 0;
        int val;
        if (flag) {
            if (not.containsKey(root))
                return not.get(root);
            val = rBacktrack(root.left, false) + rBacktrack(root.right, false);
            not.put(root, val);
            return val;
        } else {
            if (is.containsKey(root) && not.containsKey(root))
                return Math.max(is.get(root), not.get(root));
            int isVal;
            int notVal;
            if (is.containsKey(root))
                isVal = is.get(root);
            else
                isVal = rBacktrack(root.right, true) + rBacktrack(root.left, true) + root.val;
            if (not.containsKey(root))
                notVal = not.get(root);
            else
                notVal = rBacktrack(root.left, false) + rBacktrack(root.right, false);
            if (isVal >= notVal)
                is.put(root, isVal);
            else
                not.put(root, notVal);
            return Math.max(rBacktrack(root.left, false) + rBacktrack(root.right, false), rBacktrack(root.right, true) + rBacktrack(root.left, true) + root.val);
        }
    }



    public int rob(TreeNode root) {
        return Math.max(backtrack(root, true), backtrack(root, false));
    }
    // 暴力递归
    public int backtrack(TreeNode root, boolean is) {
        if (root == null)
            return 0;
        int right = 0, left = 0;
        if (is) {
            right = backtrack(root.right, false);
            left = backtrack(root.left, false);
            return right + left;
        } else {
            return Math.max(root.val + backtrack(root.right, true) + backtrack(root.left, true), backtrack(root.right, false) + backtrack(root.left, false));
        }
    }
    // 记忆化搜索
    public Map<TreeNode, Integer> o = new HashMap<>(); // 打劫当前节点
    public Map<TreeNode, Integer> f = new HashMap<>(); // 不打劫当前节点
    public int rob2(TreeNode root) {
        backtrack2(root);
        return Math.max(f.getOrDefault(root, 0), o.getOrDefault(root, 0));
    }
    public void backtrack2(TreeNode root) {
        if (root == null) {
            return;
        }
        backtrack2(root.left);
        backtrack2(root.right);
        o.put(root, root.val + f.getOrDefault(root.left, 0) + f.getOrDefault(root.right, 0));
        f.put(root, Math.max(f.getOrDefault(root.left, 0), o.getOrDefault(root.left, 0)) + Math.max(o.getOrDefault(root.right, 0), f.getOrDefault(root.right, 0)));
    }
}
