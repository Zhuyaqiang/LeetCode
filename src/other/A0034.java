package other;

import java.util.Arrays;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 进阶：
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *  
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 */
public class A0034 {
    public static void main(String[] args) {
        int[] nums = {1};
        System.out.println(Arrays.toString(rSearchRange(nums, 1)));
    }
    public static int[] rSearchRange(int[] nums, int target) {
        int len = nums.length;
        if (len == 0 || target > nums[len - 1] || target < nums[0]) {
            return new int[]{-1, -1};
        }
        int one = binary(nums, target, false);
        int two = binary(nums, target, true);
        System.out.println(one + " " + two);
        if (one == -1 || two - 1 < one) {
            return new int[] {-1, -1};
        }
        return new int[] {one, two - 1};
    }
    public static int binary(int[] nums, int target, boolean flag) {
        int len = nums.length;
        int l = 0, r = len - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            // 查找第一个大于的
            if (flag) {
                if (nums[mid] < target) {
                    l = mid + 1;
                } else if (nums[mid] > target) {
                    if (mid > 0 && nums[mid - 1] == target) {
                        return mid;
                    } else {
                        r = mid - 1;
                    }
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target) {
                    l = mid + 1;
                } else if (nums[mid] > target) {
                    r = mid - 1;
                } else {
                    if (mid > 0 && nums[mid - 1] != target) {
                        return mid;
                    } else {
                        r = mid - 1;
                    }
                }
            }
        }
        return l;
    }
        // 二分查找1
    public static int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        if (len == 0)
            return new int[]{-1, -1};
        int l = 0, r = len - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                l = mid;
                r = mid;
                break;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        if (l != r)
            return new int[] {-1, -1};
        while (l > 0 && nums[l] == nums[l-1])
            l --;
        while (r < len-1 && nums[r] == nums[r+1])
            r ++;
        return new int[]{l, r};
    }
    // 二分查找2, 两次查找, 第一次查找[第一个等于target的位置], 第二次查找[第一个大于target的位置-1]
    public static int[] searchRange2(int[] nums, int target) {
        int lIndex = binarySearch(nums, target, true);
        int rIndex = binarySearch(nums, target, false) - 1;
        if (lIndex <= rIndex && lIndex >= 0 && rIndex < nums.length && nums[lIndex] == target && nums[rIndex] == target)
            return new int[]{lIndex, rIndex};
        return new int[]{-1, -1};
    }
    // lower == true: 寻找第一个大于等于target的值
    // lower == false: 寻找第一个大于target的值
    public static int binarySearch(int[] nums, int target, boolean lower) {
        int l = 0, r = nums.length - 1, ans = nums.length;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                ans = mid;
                r = mid - 1;
            } else
                l = mid + 1;
        }
        return ans;
    }
}
