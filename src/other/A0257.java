package other;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例:
 * 输入:
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 * 输出: ["1->2->5", "1->3"]
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 */
public class A0257 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        backtrack(res, root, "");
        return res;
    }
    public void backtrack(List<String> res, TreeNode root, String str) {
        if (root == null)  {
            return;
        }
        str = str + root.val;
        if (root.right == null && root.left == null) {
            res.add(str);
            return;
        }
        str = str + "->";
        backtrack(res, root.left, str);
        backtrack(res, root.right, str);
    }


    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> res = new ArrayList<>();
        backtrack2(res, root, new StringBuilder());
        return res;
    }
    public void backtrack2(List<String> res, TreeNode root, StringBuilder str) {
        if (root == null)  {
            return;
        }
        if (root.right == null && root.left == null) {
            res.add(str.toString() + root.val);
            return;
        }
        int len = str.length();
        str.append(root.val).append("->");
        backtrack2(res, root.left, str);
        backtrack2(res, root.right, str);
        str.delete(len, str.length());
    }
}
