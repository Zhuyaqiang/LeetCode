package other;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 示例:
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class A0077 {
    public static void main(String[] args) {
        System.out.println(combine(4,2));
    }
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
//        --------------------法一-------------------------
//        for (int i = 1; i <= n; i++)
//            backtrack(i, res, new ArrayList<>(), k, n);
//        return res;
//        -------------------------------------------------

//        --------------------法二-------------------------
        backtrack(1, res, new ArrayList<>(), k, n);
        return res;
//        -------------------------------------------------
    }
    public static void backtrack(int index, List<List<Integer>> res, List<Integer> curr, int k, int n) {
        // 合理剪枝
        if (curr.size() + (n - index + 1) < k)
            return;

//        -----------法一----------------
//        curr.add(index);
//        if (curr.size() == k) {
//            res.add(curr);
//            return;
//        }
//        for (int i = index + 1; i <= n; i++) {
//            backtrack(i, res, new ArrayList<>(curr), k, n);
//        }
//        ------------------------------
//        -----------法二----------------
        if (curr.size() == k) {
            res.add(new ArrayList<>(curr));
            return;
        }
        curr.add(index);
        backtrack(index+1, res, curr, k, n);
        curr.remove(curr.size() - 1);
        backtrack(index+1, res, curr, k, n);
        //        ------------------------------
    }
}
