package other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 路径总和3
 *
 给定一个二叉树，它的每个结点都存放着一个整数值。

 找出路径和等于给定数值的路径总数。

 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。

 示例：

 root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

 10
 /  \
 5   -3
 / \    \
 3   2   11
 / \   \
 3  -2   1

 返回 3。和等于 8 的路径有:

 1.  5 -> 3
 2.  5 -> 2 -> 1
 3.  -3 -> 11

 */
public class A0437 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-1);
        root.left.right.right = new TreeNode(1);
        System.out.println(pathSum(root, 8));
    }
    public static int pathSum(TreeNode root, int sum) {
        int res = 0;
        List<List<Integer>> pathSum = new ArrayList<>();
        return res;
    }
    static class Node {
        public int sum;
        public TreeNode root;
        public Node(TreeNode root, int sum) {
            this.sum = sum;
            this.root = root;
        }
    }
}
