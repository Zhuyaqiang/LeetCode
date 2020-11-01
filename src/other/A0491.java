package other;

import java.util.*;

/**
 * 递增子序列
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
 * 示例:
 * 输入: [4, 6, 7, 7]
 * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * 说明:
 * 给定数组的长度不会超过15。
 * 数组中的整数范围是 [-100,100]。
 * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
 */
public class A0491 {
    public static void main(String[] args) {
        System.out.println(findSubsequences(new int[]{1,3,5,7}));
    }

    public static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> findSubsequences(int[] nums) {
        for (int i = 0; i < nums.length; i++)
            backtrack(nums, i, new ArrayList<>());
        return res;
    }
    // 递归, 回溯
    public static Set<String> set = new HashSet<>();
    public static void backtrack(int[] nums, int index, List<Integer> list) {
        if (index >= nums.length)
            return;
        if (list.size() == 0) {
            list.add(nums[index]);
            for (int i = index; i < nums.length; i++) {
                backtrack(nums, i + 1, new ArrayList<>(list));
            }
        } else {
            int val = list.get(list.size() - 1);
            for (int i = index; i < nums.length; i++) {
                if (i > index && nums[i] == nums[i-1])
                    continue;
                if (val <= nums[i]) {
                    list.add(nums[i]);
                    if (list.size() >= 2 && !set.contains(list.toString())) {
                        res.add(new ArrayList<>(list));
                        set.add(list.toString());
                        backtrack(nums, i + 1, new ArrayList<>(list));
                    }
                    list.remove(list.size()-1);
                }
            }
        }
    }
}
