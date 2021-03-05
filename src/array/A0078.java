package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 子集
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class A0078 {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        rSubsets(nums);
        for (List<Integer> re : res) {
            System.out.println(re);
        }
    }

    public static List<List<Integer>> rSubsets(int[] nums) {
        rBacktrack(nums, new ArrayList<>(), 0);
        return res;
    }
    public static void rBacktrack(int[] nums, List<Integer> list,  int index) {
        res.add(new ArrayList<>(list));
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            rBacktrack(nums, list, i + 1);
            list.remove(list.size() - 1);
        }
    }

    // 回溯
    public static List<List<Integer>> res = new ArrayList<>();
    public static List<List<Integer>> subsets(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        backtrack(0, nums, res, list);
        return res;
    }
    public static void backtrack(int start, int[] nums, List<List<Integer>> res, List<Integer> temp) {
        res.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            backtrack(i + 1, nums, res, temp);
            temp.remove(temp.size()-1);
        }
    }
}
