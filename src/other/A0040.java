package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合总和2
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 * [1,2,2],
 * [5]
 * ]
 */
public class A0040 {
    public static void main(String[] args) {
//        System.out.println(rCombinationSum2(new int[]{2, 5, 2, 1, 2}, 5));
        System.out.println(rCombinationSum2(new int[]{14, 6, 25, 9, 30, 20, 33, 34, 28, 30, 16, 12, 31, 9, 9, 12, 34, 16, 25, 32, 8, 7, 30, 12, 33, 20, 21, 29, 24, 17, 27, 34, 11, 17, 30, 6, 32, 21, 27, 17, 16, 8, 24, 12, 12, 28, 11, 33, 10, 32, 22, 13, 34, 18, 12}, 27));
    }


    public static List<List<Integer>> rCombinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        rBacktrack(res, new ArrayList<>(), 0, candidates, target);
        return res;
    }

    public static void rBacktrack(List<List<Integer>> res, List<Integer> curr, int index, int[] candidates, int target) {
        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i - 1])
                continue;
            target -= candidates[i];
            curr.add(candidates[i]);
            if (target == 0)
                res.add(new ArrayList<>(curr));
            else if (target > 0)
                rBacktrack(res, curr, i + 1, candidates, target);
            curr.remove(curr.size() - 1);
            target += candidates[i];
        }
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
