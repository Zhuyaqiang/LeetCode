package other;

/**
 * 区间和的个数
 * 给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 * 说明:
 * 最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。
 * 示例:
 * 输入: nums = [-2,5,-1], lower = -2, upper = 2,
 * 输出: 3
 * 解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。
 */
public class A0327 {
    public static void main(String[] args) {
        int[] nums = {-2,5,-1};
        System.out.println(countRangeSum2(nums, -2, 2));
    }
    // 暴力, 前缀和
    public static int countRangeSum(int[] nums, int lower, int upper) {
        int len = nums.length;
        long[] sum = new long[len + 1];
        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i-1] + nums[i-1];
        }
        int res = 0;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                long val = sum[i] - sum[j];
                if (val >= lower && val <= upper)
                    res ++;
            }
        }
        return res;
    }
    // 归并排序, 前缀和
    public static int countRangeSum2(int[] nums, int lower, int upper) {
        int len = nums.length;
        long[] sum = new long[len+1];
        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i-1] + nums[i-1];
        }
        return merge(sum, lower, upper, 0, len);
    }

    private static int merge(long[] sum, int lower, int upper, int l, int r) {
        if (l == r)
            return 0;
        int mid = l + (r - l) / 2;
        int lRes = merge(sum, lower, upper, l, mid);
        int rRes = merge(sum, lower, upper, mid + 1, r);

        int res = rRes + lRes;
        // 计算跨左右节点的下标对数量
        int i = l, left = mid + 1, right = mid + 1;
        while (i <= mid) {
            while (left <= r && sum[left] - sum[i] < lower)
                left ++;
            while (right <= r && sum[right] - sum[i] <= upper)
                right ++;
            res += right - left;
            i ++;
        }
        long [] sorted = new long[r - l + 1];
        int p1 = l, p2 = mid + 1;
        for (int p = 0; p < sorted.length; p ++) {
            if (p1 > mid) {
                sorted[p] = sum[p2++];
            }
            else if (p2 > r)
                sorted[p] = sum[p1++];
            else {
                if (sum[p1] > sum[p2]) {
                    sorted[p] = sum[p2++];
                } else
                    sorted[p] = sum[p1++];
            }
        }
        for (int j = 0; j < sorted.length; j++) {
            sum[l + j] = sorted[j];
        }
        return res;
    }
}
