package other;

import java.util.Arrays;

/**
 * 数组中第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 6 5 4 3 2 1
 * 输出: 5
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 6 5 5 4 3 3 2 2 1
 * 输出: 4
 * 说明:
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 */
public class A0215 {
    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println(rFindKthLargest(nums, 4));
        int[] nums1 = {3,2,1,5,6,4};
        System.out.println(rFindKthLargest(nums1, 2));
        int[] nums2 = {2, 1};
        System.out.println(rFindKthLargest(nums2, 2));
    }

    public static int rFindKthLargest(int[] nums, int k) {
        int len = nums.length;
        int l = 0, r = len - 1;
        int start = l, end = r;
        while (start < end) {
            int pivot = nums[start], left = start, right = end;
            while (left < right) {
                while (left < right && nums[right] <= pivot) {
                    right--;
                }
                if (left < right) {
                    nums[left] = nums[right];
                    left++;
                }
                while (left < right && nums[left] >= pivot) {
                    left++;
                }
                if (left < right) {
                    nums[right] = nums[left];
                    right--;
                }
            }
            nums[right] = pivot;
            if (right - start + 1 == k) {
                return nums[right];
            } else if (right - start + 1 > k) {
                end = right - 1;
            } else {
                k = k - (right - start + 1);
                start = right + 1;
            }
        }
        return nums[start];
    }

    // 排序
    public static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    // 基于快排
    public static int findKthLargest2(int[] nums, int k) {
        k = nums.length - k;
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int pivot = l;
            // 把数组以nums[l]的大小分为两部分，
            // 一部分大于nums[l], 一部分小于nums[l]
            // [l, pivot] < nums[l], [i+1, j) >= nums[l]
            for (int j = l + 1; j <= r; j++) {
                if (nums[j] < nums[l]) {
                    pivot++;
                    swap(nums, pivot, j);
                }
            }
            swap(nums, l, pivot);
            if (k == pivot)
                return nums[pivot];
            else if (k < pivot)
                r = pivot - 1;
            else
                l = pivot + 1;
        }
        return -1;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
