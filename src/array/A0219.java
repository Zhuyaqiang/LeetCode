package array;

import java.util.HashSet;
import java.util.Set;

/**
 * 存在重复元素2
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
 * 示例 1:
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 * 示例 2:
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 * 示例 3:
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 */
public class A0219 {
    public static void main(String[] args) {
        int[] nums = {1,2,1};
        System.out.println(containsNearbyDuplicate2(nums, 0));
    }
    // 暴力法
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i > k)
                    break;
                if (nums[j] == nums[i]) {
                    return true;
                }
            }
        }
        return false;
    }
    // 哈希表
    public static boolean containsNearbyDuplicate2(int[] nums, int k) {
        int len = nums.length;
        if (k == 0)
            return false;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            if (set.contains(nums[i]))
                return true;
            else {
                if (set.size() == k) {
                    set.remove(nums[i-k]);
                }
                set.add(nums[i]);
            }
        }
        return false;
    }
}
