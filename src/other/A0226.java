package other;

/**
 * 翻转二叉树
 * 翻转一棵二叉树。
 * 示例：
 * 输入：
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 */
public class A0226 {
    public TreeNode invertTree(TreeNode root) {
        return backtrack(root);
    }
    public TreeNode backtrack(TreeNode root) {
        if (root == null)
            return null;
        TreeNode newTree = new TreeNode(root.val);
        newTree.left = backtrack(root.right);
        newTree.right = backtrack(root.left);
        return newTree;
    }
}
