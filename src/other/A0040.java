package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合总和2
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */
public class A0040 {
    public static void main(String[] args) {
        System.out.println(combinationSum22(new int[]{2, 5, 2, 1, 2}, 5));
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < candidates.length; i++) {
            if (i > 0 && candidates[i] == candidates[i - 1])
                continue;
            backtrack(candidates, res, new ArrayList<>(), i, target);
        }
        return res;
    }

    public static void backtrack(int[] candidates, List<List<Integer>> res, List<Integer> curr, int index, int target) {
        target -= candidates[index];
        curr.add(candidates[index]);
        if (target == 0) {
            res.add(curr);
            return;
        }
        if (target < 0)
            return;
        for (int i = index + 1; i < candidates.length; i++) {
            if (i > index + 1 && candidates[i] == candidates[i - 1])
                continue;
            backtrack(candidates, res, new ArrayList<>(curr), i, target);
        }
        curr.remove(curr.size() - 1);
    }

    public static List<List<Integer>> combinationSum22(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        backtrack2(candidates, res, new ArrayList<>(), 0, target);
        return res;
    }

    public static void backtrack2(int[] candidates, List<List<Integer>> res, List<Integer> curr, int index, int target) {
        if (target == 0) {
            res.add(curr);
            return;
        }
        if (target < 0)
            return;
        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i - 1])
                continue;
            curr.add(candidates[i]);
            backtrack2(candidates, res, new ArrayList<>(curr), i + 1, target - candidates[i]);
            curr.remove(curr.size() - 1);
        }
    }
}
