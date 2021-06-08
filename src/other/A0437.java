package other;

import java.util.*;

/**
 * 路径总和3
 * <p>
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 * <p>
 * 找出路径和等于给定数值的路径总数。
 * <p>
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 * <p>
 * 示例：
 * <p>
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * <p>
 * 10
 * /  \
 * 5   -3
 * / \    \
 * 3   2   11
 * / \   \
 * 3  -2   1
 * <p>
 * 返回 3。和等于 8 的路径有:
 * <p>
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
 */
public class A0437 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        System.out.println(pathSum(root, 0));
    }

    public static int res = 0;

    public static int rPathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        rBacktrack(root, sum, 0, map);
        return res;
    }
    public static void rBacktrack(TreeNode root, int sum, int curr, Map<Integer, Integer> map) {
        if (root == null) {
            return;
        }
        curr += root.val;
        int diff = curr - sum;
        if (map.containsKey(diff)) {
            res += map.get(diff);
        }
        map.put(curr, map.getOrDefault(curr, 0) + 1);

        rBacktrack(root.left, sum, curr, map);
        rBacktrack(root.right, sum, curr, map);
        map.put(curr, map.get(curr) - 1);
    }

    public static int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        backtrack(root, sum, map, 0);
        return res;
    }

    public static void backtrack(TreeNode root, int target, Map<Integer, Integer> map, int currSum) {
        if (root == null) {
            return;
        }
        currSum += root.val;
        int val = currSum - target;

        res += map.getOrDefault(val, 0);
        map.put(currSum, map.getOrDefault(currSum, 0) + 1);

        backtrack(root.left, target, map, currSum);
        backtrack(root.right, target, map, currSum);

        map.put(currSum, map.get(currSum) - 1);
    }
}
