package other;

/**
 * 二叉树的堂兄弟节点
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 *
 * 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
 *
 * 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
 *
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
 */
public class A0993 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(5);
        System.out.println(isCousins(root, 5, 4));
    }
    public static boolean isCousins(TreeNode root, int x, int y) {
        int[] xRes = {-1, -1}, yRes = {-1, -1};
        get(root, x, xRes, 1, 1);
        get(root, y, yRes, 1, 1);
        if (xRes[0] == yRes[0] && xRes[1] != yRes[1]) {
            return true;
        }
        return false;
    }
    public static void get(TreeNode root, int val, int[] res, int parent, int curr) {
        if (root == null) {
            return;
        }
        if (root.val == val) {
            res[0] = curr;
            res[1] = parent;
            return;
        }
        get(root.left, val, res, root.val, curr + 1);
        if (res[0] == -1) {
            get(root.right, val, res, root.val, curr + 1);
        }
    }
}
