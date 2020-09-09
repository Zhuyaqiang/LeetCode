package other;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2：
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 * 提示：
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 */
public class A0039 {
    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2, 3, 6, 7}, 7));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, 0, target, res, new ArrayList<>());
        return res;
    }

    private static void backtrack(int[] candidates, int index, int target, List<List<Integer>> res, ArrayList<Integer> curr) {
        if (target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (target - candidates[i] < 0)
                continue;
            else {
                curr.add(candidates[i]);
                backtrack(candidates, i, target - candidates[i], res, curr);
                curr.remove(curr.size() - 1);
            }
        }
    }
}
