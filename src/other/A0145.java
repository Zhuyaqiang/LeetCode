package other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树给定一个二叉树，返回它的 后序 遍历。
 * 示例:
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 */
public class A0145 {

    // 递归
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        backtrack(ans, root);
        return ans;
    }
    public void backtrack(List<Integer> list, TreeNode root) {
        if (root == null)
            return;
        backtrack(list, root.left);
        backtrack(list, root.right);
        list.add(root.val);
    }

    // 迭代, 先序遍历翻转
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (curr != null) {
                ans.add(curr.val);
                stack.push(curr.left);
                stack.push(curr.right);
            }
        }
        Collections.reverse(ans);
        return ans;
    }

    // 迭代
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果右子树为空或者右子树刚被访问过, 则应该访问该结点
            // 否则将该节点树加入到栈中, 继续访问右子树
            if (root.right == null || pre == root.right) {
                ans.add(root.val);
                pre = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return ans;
    }
}
