package other;

import java.util.*;

/**
 * 二叉树的层次遍历2
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class A0107 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(levelOrderBottom(root));
    }
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        Stack<List> stack = new Stack<>();
        while (!deque.isEmpty()) {
            int n = deque.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode poll = deque.poll();
                list.add(poll.val);
                if (poll.left != null)
                    deque.offer(poll.left);
                if (poll.right != null)
                    deque.offer(poll.right);
            }
            stack.add(list);
        }
        List<List<Integer>> res = new ArrayList<>();
        int size = stack.size();
        for (int i = 0; i < size; i++)
            res.add(stack.pop());
        return res;
    }
}
