package other;

/**
 * 监控二叉树
 * 给定一个二叉树，我们在树的节点上安装摄像头。
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
 * 计算监控树的所有节点所需的最小摄像头数量。
 */
public class A0968 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(0);
        System.out.println(minCameraCover(root));
    }
    public static int minCameraCover(TreeNode root) {
        if (backtrack(root) == 0)
            res ++;
        return res;
    }
    public static int res = 0;
    // root.val=0, 节点待覆盖
    // root.val=1, 节点已覆盖
    // root.val=2, 节点已装相机
    public static int backtrack(TreeNode root) {
        if (root == null)
            return 1;
        int left = backtrack(root.left);
        int right = backtrack(root.right);
        // 只要有一个子节点未覆盖, 则root节点需安装相机
        if (left == 0 || right == 0) {
            res++;
            return 2;
        }
        // 若两个子节点都被覆盖, 则root节点可以不装
        if (left == 1 && right == 1)
            return 0;
        // 如果一个子节点已覆盖一个子节点已装相机或两个子节点都覆盖或两个子节点都装相机, 则root已覆盖
        if (left + right >= 3)
            return 1;
        return -1;
    }
}
