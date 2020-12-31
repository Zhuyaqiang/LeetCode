package other;

/**
 * 二叉搜索树中的插入操作
 * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据保证，新值和原始二叉搜索树中的任意节点值都不同。
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。
 * 例如,
 * 给定二叉搜索树:
 *         4
 *        / \
 *       2   7
 *      / \
 *     1   3
 * 和 插入的值: 5
 * 你可以返回这个二叉搜索树:
 *          4
 *        /   \
 *       2     7
 *      / \   /
 *     1   3 5
 * 或者这个树也是有效的:
 *          5
 *        /   \
 *       2     7
 *      / \
 *     1   3
 *          \
 *           4
 * 提示：
 * 给定的树上的节点数介于 0 和 10^4 之间
 * 每个节点都有一个唯一整数值，取值范围从 0 到 10^8
 * -10^8 <= val <= 10^8
 * 新值和原始二叉搜索树中的任意节点值都不同
 */
public class A0701 {
    public TreeNode rInsertIntoBST(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        if (root == null)
            return node;
        TreeNode temp = root;
        TreeNode pre = null;
        while (temp != null) {
            pre = temp;
            if (val > temp.val) {
                temp = temp.right;
            } else {
                temp = temp.left;
            }
        }
        if (val > pre.val)
            pre.right = node;
        else
            pre.left = node;
        return root;
    }






    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        boolean flag = false;
        TreeNode res, temp = root;
        while (temp != null) {
            if (temp.val < val) {
                if (temp.right != null) {
                    temp = temp.right;
                } else {
                    // temp为小于val的最大的点
                    // 之后往temp右子树加
                    flag = false;
                    break;
                }
            } else {
                if (temp.left != null) {
                    temp = temp.left;
                } else {
                    // temp为大于val的最小的点
                    // 之后往temp的左子树加
                    flag = true;
                    break;
                }
            }
        }
        if (flag)
            temp.left = new TreeNode(val);
        else
            temp.right = new TreeNode(val);
        return root;
    }
}
