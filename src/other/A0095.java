package other;

import java.util.ArrayList;
import java.util.List;

/**
 * 不同的二叉搜索树2
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 * 示例：
 * 输入：3
 * 输出：
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *  
 * 提示：
 * 0 <= n <= 8
 */
public class A0095 {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0)
            return new ArrayList<>();
        return backtrack(1, n);
    }
    public List<TreeNode> backtrack(int start, int end) {
        List<TreeNode> ans = new ArrayList<>();
        if (start > end) {
            ans.add(null);
            return ans;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> lefts = backtrack(start, i - 1);
            List<TreeNode> rights = backtrack(i + 1, end);
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode res = new TreeNode(i);
                    res.left = left;
                    res.right = right;
                    ans.add(res);
                }
            }
        }
        return ans;
    }
}
