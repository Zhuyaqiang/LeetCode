package DP;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 不同的二叉搜索树2
 *给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 * 示例：
 * 输入：3
 * 输出：
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 */
public class A0095 {
    public List<TreeNode> rGenerateTrees(int n) {
        if (n == 0)
            return new ArrayList<>();
        return rBacktrack(1, n);
    }
    public List<TreeNode> rBacktrack(int start, int end) {
        List<TreeNode> ans = new ArrayList<>();
        if (start > end) {
            ans.add(null);
            return ans;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> lefts = rBacktrack(start, i-1);
            List<TreeNode> rights = rBacktrack(i+1, end);
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    ans.add(root);
                }
            }
        }
        return ans;
    }
    // 递归
    // 对于1,2,....,n
    // 每个节点都可做根节点, 1做根节点, 左子树null, 右子树是以(2,3,...,n)任意节点做根节点的结果
    // 2做根节点, 左子数1, 右子树(2, 3, 4, ..., n)任意节点做根节点的结果
    // 即对每个节点, 都分别递归求其左右子树的结果列表
    public List<TreeNode> generateTrees(int n) {
        if (n == 0)
            return new LinkedList<>();
        return generate(1, n);
    }
    public List<TreeNode> generate(int start, int end) {
        LinkedList<TreeNode> ans = new LinkedList<>();
        if (start > end) {
            ans.add(null);
            return ans;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> lefts = generate(start, i - 1);
            List<TreeNode> rights = generate(i + 1, end);
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    ans.add(root);
                }
            }
        }
        return ans;
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}