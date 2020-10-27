package array;

/**
 * 寻找两个有序数组的中位数
 */
public class A0004 {
    // 暴力
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int N = m + n;
        boolean flag = false;
        if (N % 2 == 0) {
            flag = true;
            N = N / 2 - 1;
        } else {
            flag = false;
            N = N / 2;
        }
        int oneIndex = 0, twoIndex = 0;
        while (oneIndex < m && twoIndex < n && oneIndex + twoIndex < N) {
            if (nums1[oneIndex] >= nums2[twoIndex]) {
                twoIndex ++;
            } else {
                oneIndex ++;
            }
        }
        while (oneIndex + twoIndex < N && oneIndex < m)
            oneIndex ++;
        while (twoIndex + oneIndex < N && twoIndex < n)
            twoIndex ++;
        if (oneIndex == m) {
            if (!flag)
                return nums2[twoIndex];
            else {
                return (double)(nums2[twoIndex] + nums2[twoIndex + 1]) / 2;
            }
        }
        if (twoIndex == n) {
            if (!flag)
                return nums1[oneIndex];
            else
                return (double)(nums1[oneIndex] + nums1[oneIndex + 1]) / 2;
        }
        int res = 0;
        if (nums2[twoIndex] >= nums1[oneIndex]) {
            res += nums1[oneIndex ++];
        } else {
            res += nums2[twoIndex ++];
        }
        if (!flag)
            return res;
        if (oneIndex == m) {
            res += nums2[twoIndex];
        } else if (twoIndex == n) {
            res += nums1[oneIndex];
        } else {
            res += Math.min(nums1[oneIndex], nums2[twoIndex]);
        }
        return (double)res / 2;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2};
        int[] nums2 = {3,4};
        System.out.println(findMedianSortedArrays2(nums1, nums2));
    }
    // 二分查找
    // https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int left = (len1 + len2 + 1) / 2;
        int right = (len1 + len2 + 2) / 2;
        // 合并处理奇数和偶数的情况
        return (getKth(nums1, 0, len1 - 1, nums2, 0, len2 - 1, left) + getKth(nums1, 0, len1 - 1, nums2, 0, len2 - 1, right)) * 0.5;
    }

    private static double getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1, len2 = end2 - start2 + 1;
        // 保证nums1的长度永远小于nums2
        if (len1 > len2)
            return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0)
            return nums2[start2 + k - 1];
        if (k == 1)
            return Math.min(nums1[start1], nums2[start2]);
        // 确保不会越界
        int i = start1 + Math.min(k / 2, len1) - 1;
        int j = start2 + Math.min(k / 2, len2) - 1;
        if (nums1[i] > nums2[j])
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        else
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
    }
}
