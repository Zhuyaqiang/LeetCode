package other;

import java.util.Arrays;

/**
 * 拼接最大数
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 * 说明: 请尽可能地优化你算法的时间和空间复杂度。
 * 示例 1:
 * 输入:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * 输出:
 * [9, 8, 6, 5, 3]
 * 示例 2:
 * 输入:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * 输出:
 * [6, 7, 6, 0, 4]
 * 示例 3:
 * 输入:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * 输出:
 * [9, 8, 9]
 */
public class A0321 {
    public static void main(String[] args) {
        int[] nums1 = {3,4,6,5};
        int[] nums2 = {9, 1, 2, 5, 8, 3};
        System.out.println(Arrays.toString(maxNumber(nums1, nums2, 5)));
//        int[] nums3 = {6,7};
//        int[] nums4 = {6,0,4};
//        System.out.println(Arrays.toString(maxNumber(nums3, nums4, 5)));
//        int[] nums7 = {6,3,9,0,5,6};
//        int[] nums8 = {2,2,5,2,1,4,4,5,7,8,9,3,1,6,9,7,0};
//        System.out.println(Arrays.toString(maxNumber(nums7, nums8, 23)));
//        int[] nums5 = {3,9};
//        int[] nums6 = {8,9};
//        System.out.println(Arrays.toString(maxNumber(nums5, nums6, 3)));
    }
    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] res = new int[k];
        int len1 = nums1.length, len2 = nums2.length;
        // 从 nums1 中选出长 i 的子序列
        for (int i = 0; i <= k && i <= len1; i++) {
            // 从nums2中选出长为k-i的子序列
            int j = k - i;
            if (j >= 0 && j <= len2) {
                int[] sub1 = getSub(nums1, i);
                int[] sub2 = getSub(nums2, j);
                int[] curr = merge(sub1, sub2);
                if (compare(curr, 0, res, 0)) {
                    res = Arrays.copyOf(curr, k);
                }
            }
        }
        return res;
    }

    // 找到curr和res中不相等的第一个数字进行比较
    private static boolean compare(int[] curr, int cIndex, int[] res,  int rIndex) {
        int cLen = curr.length, rLen = res.length;
        while (cIndex < cLen && rIndex < rLen) {
            int diff = curr[cIndex] - res[rIndex];
            if (diff > 0)
                return true;
            else if (diff < 0)
                return false;
            cIndex ++;
            rIndex ++;
        }
        // 如果长度不同且比较完了短的
        if (cIndex < cLen)
            return true;
        else
            return false;
    }

    private static int[] merge(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length, index1 = 0, index2 = 0;
        int[] res = new int[len1 + len2];
        for (int i = 0; i < len1 + len2; i++) {
            if (compare(nums1, index1, nums2, index2))
                res[i] = nums1[index1++];
            else
                res[i] = nums2[index2++];
        }
        return res;
    }

    private static int[] getSub(int[] nums, int k) {
        int[] stack = new int[k];
        int top = -1, len = nums.length;
        // 需要删掉的个数
        int remain = len - k;
        for (int i = 0; i < len; i++) {
            int val = nums[i];
            while (top >= 0 && stack[top] < val && remain > 0) {
                top --;
                remain --;
            }
            if (top < k - 1) {
                stack[++top] = val;
            } else {
                remain --;
            }
        }
        return stack;
    }
}
