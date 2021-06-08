package other;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 *
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 *
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 示例 4：
 *
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 示例 5：
 *
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 *  
 *
 * 提示：
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 */
public class A0004 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int totalLen = len1 + len2;
        if (totalLen % 2 == 0) {
            return (double) (getKth(nums1, nums2, totalLen / 2) + getKth(nums1, nums2, totalLen / 2 - 1)) / 2;
        } else {
            return (double) getKth(nums1, nums2, totalLen / 2);
        }
    }
    public int getKth(int[] nums1, int[] nums2, int k) {
        int index1 = 0, index2 = 0;
        int len1 = nums1.length, len2 = nums2.length;
        while (true) {
            if (index1 == len1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == len2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            int half = k / 2;
            int newIndex1 = Math.min(len1, index1 + half) - 1;
            int newIndex2 = Math.min(len2, index2 + half) - 1;
            if (nums1[newIndex1] >= nums2[newIndex2]) {
                index2 = newIndex2 + 1;
                k -= (newIndex1 - index1 + 1);
            } else {
                index1 = newIndex1 + 1;
                k -= (newIndex1 - index1 + 1);
            }
        }
    }
}
