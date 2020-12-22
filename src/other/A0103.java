package other;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的锯齿形层序遍历
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层序遍历如下：
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 *              1
 *          2       3
 *       4    n   n     5
 */
public class A0103 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        List<List<Integer>> lists = zigzagLevelOrder(root);
        System.out.println(lists);
    }
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        // false 从左往右, true从右往左
        boolean flag = true;
        while (!deque.isEmpty()) {
            int size = deque.size();
            Deque<TreeNode> temp = new LinkedList<>();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode;
                if (!flag) {
                    treeNode = deque.pollFirst();
                } else {
                    treeNode = deque.pollLast();
                }
                list.add(treeNode.val);
                if (!flag) {
                    if (treeNode.right != null) {
                        temp.addLast(treeNode.right);
                    }
                    if (treeNode.left != null) {
                        temp.addLast(treeNode.left);
                    }
                } else {
                    if (treeNode.left != null) {
                        temp.addFirst(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        temp.addFirst(treeNode.right);
                    }
                }
            }
            deque.addAll(temp);
            res.add(list);
            flag = !flag;
        }
        return res;
    }
}
