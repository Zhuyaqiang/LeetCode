package other;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
 * 你可以假设所有输入数组都可以得到满足题目要求的结果。
 * 示例 1：
 * 输入：nums = [1,5,1,1,6,4]
 * 输出：[1,6,1,5,1,4]
 * 解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
 * 示例 2：
 * 输入：nums = [1,3,2,2,3,1]
 * 输出：[2,3,1,3,1,2]
 *  
 * 提示：
 * 1 <= nums.length <= 5 * 104
 * 0 <= nums[i] <= 5000
 * 题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果
 * 进阶：你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 */
public class A0324 {
    public static void main(String[] args) {
//        int[] nums = {2,1,1,2,1,3,3,3,1,3,1,3,2};
//        int[] nums = {3,3,3,2,2,2,3,2,1,1,2,1,2,3,3,3,1,2};
        int[] nums = {1,1,2,2,2,1};
//        int[] nums = {4, 5, 5, 6};
        wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void wiggleSort(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return;
        }
        int k = (len + 1) / 2;
        int mid = findMid(nums, 0, len - 1, k);
        int[] A = new int[k];
        int[] B = new int[len - k];
        for (int i = 0; i < k; i++) {
            A[i] = nums[i];
        }
        for (int i = k; i < len; i++) {
            B[i - k] = nums[i];
        }
        if (A[k - 1] == B[len - k - 1] || A[0] == B[0]) {
            for (int i = 0; i < k / 2; i++) {
                int temp = A[i];
                A[i] = A[k - i - 1];
                A[k - i - 1] = temp;
            }
        }
        for (int i = 0; i < k; i++) {
            nums[2 * i] = A[k - i - 1];
        }
        for (int i = 0; i < len - k; i++) {
            nums[2 * i + 1] = B[len - k - i - 1];
        }
    }

    public static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
    public static int findMid(int[] nums, int l, int r, int k) {
        while (l < r) {
            int left = l, right = r, pivot = nums[l];
            while (left < right) {
                while (left < right && nums[right] >= pivot) {
                    right--;
                }
                if (left < right) {
                    nums[left++] = nums[right];
                }
                while (left < right && nums[left] <= pivot) {
                    left++;
                }
                if (left < right) {
                    nums[right--] = nums[left];
                }
            }
            nums[right] = pivot;
            int start = l, end = right - 1;
            while (start < end) {
                if (nums[start] == nums[right]) {
                    int temp = nums[start];
                    nums[start] = nums[end];
                    nums[end] = temp;
                    end--;
                } else {
                    start++;
                }
            }
            start = right + 1;
            end = r;
            while (start < end) {
                if (nums[end] == nums[right]) {
                    int temp = nums[end];
                    nums[end] = nums[start];
                    nums[start] = temp;
                    start++;
                } else {
                    end--;
                }
            }
            if (right - l + 1 == k) {
                return right;
            } else if (right + 1 - l > k) {
                r = right - 1;
            } else {
                k = k - (right - l + 1);
                l = right + 1;
            }
        }
        return l;
    }
}
