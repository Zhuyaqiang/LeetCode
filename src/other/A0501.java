package other;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 二叉搜索树中的众数
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 * 假定 BST 有如下定义：
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 *    1
 *     \
 *      2
 *     /
 *    2
 * 返回[2].
 * 提示：如果众数超过1个，不需考虑输出顺序
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 */
public class A0501 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        int[] mode = rFindMode(root);
        System.out.println(Arrays.toString(mode));
    }

    public static int[] rFindMode(TreeNode root) {
        flag = false;
        rBacktrack(root);
        return set.stream().mapToInt(Integer::intValue).toArray();
    }
    public static void rBacktrack(TreeNode root) {
        if (root == null)
            return;
        rBacktrack(root.left);
        if (!flag) {
            flag = true;
            curr = root.val;
            count = 1;
            set.add(curr);
            max = 1;
        } else {
            if (curr == root.val) {
                count ++;
                if (count == max)
                    set.add(root.val);
                else if (count > max) {
                    set.clear();
                    set.add(root.val);
                    max = count;
                }
            } else {
                count = 1;
                curr = root.val;
                if (max == 1)
                    set.add(curr);
            }
        }
        rBacktrack(root.right);
    }
    public static Set<Integer> set = new HashSet<>();
    public static int count = 0;
    public static int curr = 0;
    public static int max = 0;
    public static List<Integer> res = new ArrayList<>();
    public static boolean flag = false;
    public static int[] findMode(TreeNode root) {
        if (root == null)
            return new int[0];
        backtrack(root);
        flag = false;
//        result(root);
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
    public static void backtrack(TreeNode root) {
        if (root == null)
            return;
        backtrack(root.left);
        if (!flag) {
            flag = true;
            curr = root.val;
            count = 1;
            max =  1;
            res.add(root.val);
        } else {
            if (root.val == curr) {
                count ++;
                if (count == max) {
                    res.add(root.val);
                } else if (count > max) {
                    res.clear();
                    res.add(root.val);
                }
                max = Math.max(max, count);
            } else {
                if (max == 1)
                    res.add(root.val);
                count = 1;
                curr = root.val;
            }
        }
        backtrack(root.right);
    }
}
