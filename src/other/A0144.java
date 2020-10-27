package other;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的前序遍历
 * 给定一个二叉树，返回它的 前序 遍历。
 *  示例:
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 * 输出: [1,2,3]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class A0144 {
    // 递归
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        backtrack(root, list);
        return list;
    }

    public void backtrack(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        list.add(root.val);
        backtrack(root.left, list);
        backtrack(root.right, list);
    }
    // 迭代
    public List<Integer> preorderTraversal2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (pop == null)
                continue;
            res.add(pop.val);
            stack.push(pop.right);
            stack.push(pop.left);
        }
        return res;
    }
}
