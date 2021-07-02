package other;

/**
 * 长度最小子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。
 * 示例:
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * 进阶:
 * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 */
public class A0209 {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        System.out.println(minSubArrayLen3(7, nums));
        int[] nums1 = {1, 4, 4};
        System.out.println(minSubArrayLen3(4, nums1));
        int[] nums2 = {1, 1, 1, 1, 1, 1, 1};
        System.out.println(minSubArrayLen3(11, nums2));
        int[] nums3 = {1, 2, 3, 4, 5};
        System.out.println(minSubArrayLen3(11, nums3));
    }

    public static int rMinSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        int l = 0, r = 0;
        int res = len + 1;
        int sum = 0;
        while (r < len) {
            sum += nums[r];
            if (sum < target) {
                r++;
            } else {
                while (l <= r && sum >= target) {
                    res = Math.min(res, r - l + 1);
                    sum -= nums[l];
                    l++;
                }
                if (l > r) {
                    r = l + 1;
                } else {
                    r++;
                }
            }
        }
        return res == len + 1 ? 0 : res;
    }

    public static int minSubArrayLen(int s, int[] nums) {
        int l = 0, r = 0, sum = 0;
        int res = Integer.MAX_VALUE;
        int len = nums.length;
        if (len == 0)
            return 0;
        while (r < len && l < len) {
            if (sum >= s) {
                res = Math.min(res, r - l);
                sum -= nums[l];
                l++;
            } else {
                sum += nums[r];
                r++;
            }
        }
        while (l < len) {
            if (sum >= s) {
                res = Math.min(res, r - l);
            }
            sum -= nums[l];
            l++;
        }
        if (res == Integer.MAX_VALUE)
            return 0;
        return res;
    }

    public int minSubArrayLen2(int s, int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int left = 0, right = 0, res = Integer.MAX_VALUE;
        int sum = 0, len = nums.length;
        while (right < len) {
            sum += nums[right];
            right++;
            while (sum >= s) {
                res = Math.min(res, right - left);
                sum -= nums[left];
                left++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    // 前缀和+二分
    // sum[i] - sum[j] >= target
    // sum[i] >= sum[j] + target
    public static int minSubArrayLen3(int target, int[] nums) {
        int len = nums.length;
        int[] sum = new int[len + 1];
        int min = len + 1;
        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        for (int i = 0; i <= len; i++) {
            int tempTarget = sum[i] + target;
            int index = binary_search(sum, tempTarget);
            if (index <= len && index >= 0) {
                min = Math.min(min, index - i);
            }
        }
        return min == len + 1 ? 0 : min;
    }
    public static int binary_search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int ret = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) {
                ret = ret == -1 ? mid : Math.min(ret, mid);
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ret;
    }
}
