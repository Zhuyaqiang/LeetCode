package other;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 根到叶子节点数字之和
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 * 计算从根到叶子节点生成的所有数字之和。
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例 1:
 * 输入: [1,2,3]
 *     1
 *    / \
 *   2   3
 * 输出: 25
 * 解释:
 * 从根到叶子节点路径 1->2 代表数字 12.
 * 从根到叶子节点路径 1->3 代表数字 13.
 * 因此，数字总和 = 12 + 13 = 25.
 * 示例 2:
 *
 * 输入: [4,9,0,5,1]
 *     4
 *    / \
 *   9   0
 *  / \
 * 5   1
 * 输出: 1026
 * 解释:
 * 从根到叶子节点路径 4->9->5 代表数字 495.
 * 从根到叶子节点路径 4->9->1 代表数字 491.
 * 从根到叶子节点路径 4->0 代表数字 40.
 * 因此，数字总和 = 495 + 491 + 40 = 1026.
 */
public class A0129 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(sumNumbers2(root));
    }
    // 深度优先搜索
    public static int sumNumbers(TreeNode root) {
        backtrack(root, 0);
        return res;
    }  public static int res = 0;
    public static void backtrack(TreeNode root, int dis) {
        if (root == null) {
            res += dis;
            return;
        }
        dis = dis * 10 + root.val;
        if (root.left == null && root.right == null) {
            res += dis;
            return;
        }
        if (root.left != null)
            backtrack(root.left, dis);
        if (root.right != null)
            backtrack(root.right, dis);
    }
    // 广度优先搜索
    public static int sumNumbers2(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        if (root == null)
            return 0;
        int res = 0;
        deque.offer(root);
        while (!deque.isEmpty()) {
            int n = deque.size();
            for (int i = 0; i < n; i++) {
                TreeNode poll = deque.poll();
                if (poll.left == null && poll.right == null)
                    res += poll.val;
                else {
                    if (poll.left != null) {
                        poll.left.val = poll.val * 10 + poll.left.val;
                        deque.offer(poll.left);
                    }
                    if (poll.right != null) {
                        poll.right.val = poll.val * 10 + poll.right.val;
                        deque.offer(poll.right);
                    }
                }
            }
        }
        return res;
    }
}
