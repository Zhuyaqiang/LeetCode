package other;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的层平均值
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
 * 示例 1：
 * 输入：
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 输出：[3, 14.5, 11]
 * 解释：
 * 第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
 */
public class A0637 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(averageOfLevels(root));
    }

    public static List<Double> averageOfLevels(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        List<Double> res = new ArrayList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            int n = deque.size();
            double sum = 0;
            int count = 0;
            for (int i = 0; i < n; i++) {
                TreeNode temp = deque.poll();
                if (temp == null)
                    continue;
                sum += temp.val;
                count++;
                deque.offer(temp.left);
                deque.offer(temp.right);
            }
            if (count != 0)
                res.add(sum / count);
        }
        return res;
    }
}
