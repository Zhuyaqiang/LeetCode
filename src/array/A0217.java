package array;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 存在重复元素
 给定一个整数数组，判断是否存在重复元素。
 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 示例 1:
 输入: [1,2,3,1]
 输出: true
 示例 2:
 输入: [1,2,3,4]
 输出: false
 示例 3:
 输入: [1,1,1,3,3,4,3,2,4,2]
 输出: true
 */
public class A0217 {
    public static void main(String[] args) {
        int[] nums = {1,4,3,2,1};
        System.out.println(containsDuplicate(nums));
    }
    // 排序再对比, 超时
    public static boolean containsDuplicate(int[] nums) {
        int len = nums.length;
        quickSort(nums, 0, len - 1);
        for (int i = 1; i < len; i++) {
            if (nums[i] == nums[i-1])
                return true;
        }
        return false;
    }
    public static void quickSort(int nums[], int start, int end) {
        if (start < end) {
            int l = start, r = end, pivot = nums[l];
            while (l < r) {
                while (l < r && nums[r] >= pivot) r--;
                if (l < r) {
                    nums[l] = nums[r];
                    l ++;
                }
                while (l < r && nums[l] < pivot) l++;
                if (l < r) {
                    nums[r] = nums[l];
                    r--;
                }
            }
            nums[l] = pivot;
            quickSort(nums, start, r - 1);
            quickSort(nums, r + 1, end);
        }
    }
    public boolean containsDuplicate2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i]))
                return true;
            else
                set.add(nums[i]);
        }
        return false;
    }
}
