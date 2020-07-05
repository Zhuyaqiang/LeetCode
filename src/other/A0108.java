package other;

/**
 * 将有序数组转换为二叉搜索树
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * 示例:
 * 给定有序数组: [-10,-3,0,5,9],
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class A0108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0)
            return null;
        TreeNode root = build(nums, 0, nums.length-1);
        return root;
    }
    public TreeNode build(int[] nums, int l, int r) {
        if (l == r)
            return new TreeNode(nums[l]);
        int mid = l + (r - l) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        if (mid != l) {
            root.left = build(nums, l, mid - 1);
        }
        root.right = build(nums, mid + 1, r);
        return root;
    }
}
