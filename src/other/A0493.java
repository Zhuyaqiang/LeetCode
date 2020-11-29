package other;

import java.util.Arrays;

/**
 * 翻转对
 * <p>
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 * 你需要返回给定数组中的重要翻转对的数量。
 * 示例 1:
 * 输入: [1,3,2,3,1]
 * 输出: 2
 * 示例 2:
 * 输入: [2,4,3,5,1]
 * 输出: 3
 * 注意:
 */
public class A0493 {
    // 暴力, 超时
    public int reversePairs(int[] nums) {
        int res = 0, len = nums.length;
        if (len <= 1)
            return 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (((double) nums[i]) / 2 > nums[j])
                    res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] nums = {1, 3, 2, 3, 1};
//        mergeSort(nums, 0, nums.length - 1);
//        System.out.println(res);
//        System.out.println(Arrays.toString(nums));
//
//        int[] nums2 = {2, 4, 3, 5, 1};
//        mergeSort(nums2, 0, nums2.length - 1);
//        System.out.println(res);
//        System.out.println(Arrays.toString(nums2));

        int[] nums2 = {5,4,3,2,1};
        mergeSort(nums2, 0, nums2.length - 1);
        System.out.println(res);
        System.out.println(Arrays.toString(nums2));
    }
    // 归并排序
    public static int res = 0;
    public static int reversePairs2(int[] nums) {
        int len = nums.length;
        if (len <= 1)
            return 0;
        mergeSort(nums, 0, len-1);
        return res;
    }


    public static void mergeSort(int[] nums, int l, int r) {
        if (l >= r)
            return;
        int mid = l + (r - l) / 2;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        int i = l, j = mid + 1;
        while (i <= mid) {
            while (j <= r && ((double)nums[i]) / 2 > nums[j])
                j++;
            res += j - mid - 1;
            i++;
        }
        merge(nums, l, mid, mid + 1, r);
    }

    private static void merge(int[] nums, int l1, int r1, int l2, int r2) {
        int[] temp = new int[r2 - l1 + 1];
        int tempStart = l1, tempStart2 = l2;
        for (int i = 0; i < temp.length; i++) {
            if (l1 > r1) {
                temp[i] = nums[l2++];
                continue;
            }
            if (l2 > r2) {
                temp[i] = nums[l1++];
                continue;
            }
            // 此时nums[l1] > nums[l2]之前所有的
            if (nums[l1] <= nums[l2]) {
                temp[i] = nums[l1++];
            }
            else {
                temp[i] = nums[l2];
                l2 ++;
            }
        }
        for (int i = tempStart; i < tempStart + temp.length; i++)
            nums[i] = temp[i - tempStart];
    }
}
