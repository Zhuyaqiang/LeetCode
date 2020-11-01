package other;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合总和3
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * 说明：
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class A0216 {
    public static void main(String[] args) {
        System.out.println(combinationSum3(3, 15));
    }
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), k, n, 1);
        return res;
    }

    private static void backtrack(List<List<Integer>> res, ArrayList<Integer> curr, int k, int n, int index) {
        if (n == 0 && k == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }
        if (n < 0 || k <= 0)
            return;
        for (int i = index; i <= 9; i++) {
            curr.add(i);
            backtrack(res, curr, k - 1, n-i, i + 1);
            curr.remove(curr.size() - 1);
        }
    }
}
