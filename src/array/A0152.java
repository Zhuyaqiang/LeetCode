package array;

/**
 * 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字）。
 * 示例 1:
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
public class A0152 {
    // 为了应对负值, 同时维护当前最大值和当前最小值
    // 当出现负值时, 交换当前最大值和当前最小值, 能保证最大值永远是最大值, 最小值永远是从上一个负值开始算的最小值
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int len = nums.length;
        int currMax = 1, currMin = 1, max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num < 0) {
                int temp = currMax;
                currMax = currMin;
                currMin = temp;
            }
            currMax = Math.max(currMax * num, num);
            currMin = Math.min(currMin * num, num);
            max = Math.max(max, currMax);
        }
        return max;
    }

    // 维护当前最大数组和当前最小数组以及结果数组
    public int maxProduct2(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return 0;
        int dp = nums[0];
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < len; i++) {
            int t = max;
            max = Math.max(nums[i], Math.max(max * nums[i], min * nums[i]));
            min = Math.min(nums[i], Math.min(min * nums[i], t * nums[i]));
            dp = Math.max(dp, max);
        }
        return dp;
    }
}
