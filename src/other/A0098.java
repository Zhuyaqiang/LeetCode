package other;

/**
 * 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A0098 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println(isValidBST(root));  // true
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.right.left = new TreeNode(3);
        root1.right.right = new TreeNode(6);
        System.out.println(isValidBST(root1));  // false
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(5);
        root2.left.left = new TreeNode(0);
        root2.left.right = new TreeNode(2);
        root2.right.right = new TreeNode(4);
        root2.right.right = new TreeNode(6);
        System.out.println(isValidBST(root2));   //true
        TreeNode root3 = new TreeNode(32);
        root3.left = new TreeNode(26);
        root3.left.left = new TreeNode(19);
        root3.left.left.right = new TreeNode(27);
        root3.right = new TreeNode(47);
        root3.right.right = new TreeNode(56);
        System.out.println(isValidBST(root3));   // false
        TreeNode root4 = new TreeNode(Integer.MIN_VALUE);
        root.right = new TreeNode(Integer.MAX_VALUE);
        System.out.println(isValidBST(root4));  // true

        /**
         *                 32
         *          26             47
         *    19                      56
         *       27
         */
    }
    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return backtrack(root.left, Long.MIN_VALUE, root.val) && backtrack(root.right, root.val, Long.MAX_VALUE);
    }
    public static boolean backtrack(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val >= max || root.val <= min) {
            return false;
        }
        boolean res = true;
        if (root.left != null) {
            if (root.left.val >= root.val) {
                return false;
            }
            res = res && backtrack(root.left, min, Math.min(max, root.val));
        }
        if (root.right != null) {
            if (root.right.val <= root.val) {
                return false;
            }
            res = res && backtrack(root.right, Math.max(min, root.val), max);
        }
        return res;
    }
}
