package other;

import java.util.Stack;

/**
 * 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * 进阶：
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 */
public class A0101 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        System.out.println(isSymmetric2(root));

    }
    public static boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> leftStack = new Stack<>();
        Stack<TreeNode> rightStack = new Stack<>();
        TreeNode left = root.left, right = root.right;
        while ((!leftStack.isEmpty() && !rightStack.isEmpty()) || left != null && right != null) {
            while (left == null && right == null && !rightStack.isEmpty() && !leftStack.isEmpty()) {
                left = leftStack.pop();
                right = rightStack.pop();
            }
            if (left == null && right == null) {
                return true;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            leftStack.push(left.left);
            rightStack.push(right.right);
            left = left.right;
            right = right.left;
        }
        return leftStack.size() == rightStack.size();
    }

    public boolean rIsSymmetric(TreeNode root) {
        return rIsMirror(root, root);
    }

    private boolean rIsMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return rIsMirror(left.left, right.right) && rIsMirror(left.right, right.left);
    }

    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }
    public boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return (left.val == right.val) && isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
