package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 子集II
 *
 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 说明：解集不能包含重复的子集。
 示例:
 输入: [1,2,2]
 输出:
 [
 [2],
 [1],
 [1,2,2],
 [2,2],
 [1,2],
 []
 ]
 */
public class A0090 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        List<List<Integer>> lists = subsetsWithDup(nums);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
    static List<List<Integer>> res = new ArrayList<>();
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        backtrack(nums, 0, list);
        return res;
    }
    public static void backtrack(int[] nums, int start, List<Integer> temp) {
        res.add(new ArrayList<>(temp));
        if (start == nums.length)
            return;
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i-1])
                continue;
            temp.add(nums[i]);
            backtrack(nums, i + 1, temp);
            temp.remove(temp.size() - 1);
        }
    }
}
