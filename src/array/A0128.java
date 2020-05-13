package array;

import java.util.HashSet;
import java.util.Set;

/**
 * 最长连续序列
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * 要求算法的时间复杂度为 O(n)。
 * 示例:
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */
public class A0128 {
    public static void main(String[] args) {
        int[] nums = {1,2,0,1};
        System.out.println(longestConsecutive2(nums));
    }

    // 先排序, 复杂度nlogn
    public static int longestConsecutive(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return 0;
        quickSort(nums, 0, len - 1);
        int currMax = 1;
        int maxRes = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] == nums[i-1] + 1) {
                currMax = currMax + 1;
                maxRes = Math.max(maxRes, currMax);
            }
            else if (nums[i] == nums[i-1])
                continue;
            else
                currMax = 1;
        }
        return maxRes;
    }

    public static void quickSort(int[] nums, int start, int end) {
        if (start < end) {
            int l = start, r = end, pivot = nums[start];
            while (l < r) {
                while (l < r && nums[r] >= pivot) r--;
                if (l < r) {
                    nums[l] = nums[r];
                    l++;
                }
                while (l < r && nums[l] < pivot) l++;
                if (l < r) {
                    nums[r] = nums[l];
                    r--;
                }
            }
            nums[l] = pivot;
            quickSort(nums, start, l - 1);
            quickSort(nums, l + 1, end);
        }
    }

    // 哈希集合, 如果当前数字-1在哈希表内, 则说明肯定会被查找到, 跳过
    //        如果当前数字-1不在哈希表内, 则当前数字作为起点往后循环查找最长序列
    public static int longestConsecutive2(int[] nums) {
        if (nums.length == 0)
            return 0;
        Set<Integer> num_set = new HashSet<>();
        for (int num : nums) {
            num_set.add(num);
        }
        int maxResult = 1;
        for (int num : nums) {
            if (num_set.contains(num-1))
                continue;
            int currMax = 1;
            int currNum = num;
            while (num_set.contains(currNum+1)) {
                currNum++;
                currMax++;
            }
            maxResult = Math.max(currMax, maxResult);
        }
        return maxResult;
    }
}
