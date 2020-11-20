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
        int[] nums = {2,3,1,2,4,3};
        System.out.println(minSubArrayLen(7, nums));
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
            }
            else {
                sum += nums[r];
                r ++;
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
                res = Math.min(res, right-left);
                sum -= nums[left];
                left++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
