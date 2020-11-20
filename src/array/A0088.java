package array;

/**
 * 合并两个有序数组
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * 说明:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * 输出: [1,2,2,3,5,6]
 */
public class A0088 {
    public static void main(String[] args) {
        int[] nums1 = {0};
        int[] nums2 = {1};
        rMerge(nums1,0,nums2,1);
        for (int i : nums1) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int start;
        int x = m - 1, y = n - 1;
        for (start = m + n - 1; start >= 0; start--) {
            if (y < 0 || x >= 0 && nums1[x] < nums2[y]) {
                nums1[start] = nums1[x];
                x--;
            }
            else if (x < 0 || y >=0 && nums1[x] >= nums2[y]) {
                nums1[start] = nums2[y];
                y--;
            }
        }
    }
    public static void rMerge(int[] nums1, int m, int[] nums2, int n) {
        int oneIndex = m - 1, twoIndex = n - 1;
        if (m == 0) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }
        if (nums1[m - 1] < nums1[0]) {
            for (int start = m + n - 1; start >= 0; start--) {
                if (twoIndex < 0 || oneIndex >= 0 && nums1[oneIndex] < nums2[twoIndex]) {
                    nums1[start] = nums1[oneIndex];
                    oneIndex--;
                } else if (oneIndex < 0 || twoIndex >= 0 && nums1[oneIndex] >= nums1[twoIndex]) {
                    nums1[start] = nums2[twoIndex];
                    twoIndex--;
                }
            }
        } else {
            for (int start = m + n - 1; start >= 0; start--) {
                if (twoIndex < 0 || oneIndex >= 0 && nums1[oneIndex] >= nums2[twoIndex]) {
                    nums1[start] = nums1[oneIndex];
                    oneIndex--;
                } else if (oneIndex < 0 || twoIndex >= 0 && nums1[oneIndex] < nums2[twoIndex]) {
                    nums1[start] = nums2[twoIndex];
                    twoIndex--;
                }
            }
        }
    }
}
