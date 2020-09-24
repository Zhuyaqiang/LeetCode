package other;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 合并二叉树
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
 * 示例 1:
 * 输入:
 * 	Tree 1                     Tree 2
 *           1                         2
 *          / \                       / \
 *         3   2                     1   3
 *        /                           \   \
 *       5                             4   7
 * 输出:
 * 合并后的树:
 * 	     3
 * 	    / \
 * 	   4   5
 * 	  / \   \
 * 	 5   4   7
 * 注意: 合并必须从两个树的根节点开始。
 */
public class A0617 {
    // 深度优先搜索, 递归
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        return backtrack(t1, t2);
    }
    public TreeNode backtrack(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null)
            return null;
        if (t1 == null) {
            return t2;
        } else if (t2 == null)
            return t1;
        t1.val += t2.val;
        t1.left = backtrack(t1.left, t2.left);
        t1.right = backtrack(t1.right, t2.right);
        return t1;
    }
    // 广度优先搜索
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if (t1 == null)
            return t2;
        if (t2 == null)
            return t1;
        TreeNode merge = new TreeNode(t1.val + t2.val);
        Deque<TreeNode> deque = new LinkedList<>();
        Deque<TreeNode> deque1 = new LinkedList<>();
        Deque<TreeNode> deque2 = new LinkedList<>();
        deque.offer(merge);
        deque1.offer(t1);
        deque2.offer(t2);
        while (!deque1.isEmpty() && !deque2.isEmpty()) {
            TreeNode node = deque.poll(), node1 = deque1.poll(), node2 = deque2.poll();
            TreeNode left1 = node1.left, left2 = node2.left, right1 = node1.right, right2 = node2.right;
            if (left1 != null && left2 != null) {
                TreeNode left = new TreeNode(left1.val + left2.val);
                node.left = left;
                deque.offer(left);
                deque1.offer(left1);
                deque2.offer(left2);
            } else if (left1 != null)
                node.left = left1;
            else if (left2 != null)
                node.left = left2;

            if (right1 != null && right2 != null) {
                TreeNode right = new TreeNode(right1.val + right2.val);
                node.right = right;
                deque.offer(right);
                deque1.offer(right1);
                deque2.offer(right2);
            } else if (right1 != null)
                node.right = right1;
            else if (right2 != null)
                node.right = right2;
        }
        return merge;
    }

}
