package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2:
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */
public class A0039 {
    public static void main(String[] args) {
        int[] candidates = {2,3,5};
        List<List<Integer>> lists = combinationSum(candidates, 8);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
    // 回溯
    public static List<List<Integer>> res= new ArrayList<>();
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0)
            return res;
        backtrack(0, candidates, target, new ArrayList<>());
        return res;
    }
    public static void backtrack(int start, int[] candidates, int target, List<Integer> list) {
        if (target == 0)
            res.add(new ArrayList<>(list));
        else if (target < 0)
            return;
        else {
            for (int i = start; i < candidates.length; i++) {
                list.add(candidates[i]);
                backtrack(i, candidates, target - candidates[i], list);
                list.remove(list.size()-1);
            }
        }
    }
}
