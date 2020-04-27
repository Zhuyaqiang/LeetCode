package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合总数3
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
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
        List<List<Integer>> lists = combinationSum3(3, 9);
        System.out.println(lists);
    }
    public static List<List<Integer>> res = new ArrayList<>();
    public static int nn;
    public static List<List<Integer>> combinationSum3(int k, int n) {
        nn = k;
        backtrack(1, n, 0, new ArrayList<>());
        return res;
    }
    public static void backtrack(int start, int target, int count, List<Integer> list) {
        if (count == nn &&  target == 0) {
            res.add(new ArrayList<>(list));
        }
        if (count == nn)
            return;
        for (int i = start; i < 10; i++) {
            list.add(i);
            backtrack(i+1, target-i, count+1, list);
            list.remove(list.size()-1);
        }
    }
}
