package DP;

/**
 * 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 示例 1:
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
public class A0152 {
    public static void main(String[] args) {
        int[] nums = {-2,0,-1};
        System.out.println(rMaxProduct(nums));
    }
    public static int rMaxProduct(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return 0;
        int[] min = new int[len];
        int[] max = new int[len];
        int res;
        min[0] = max[0] = res = nums[0];
        for (int i = 1; i < len; i++) {
            min[i] = Math.min(nums[i], Math.min(nums[i] * min[i-1], nums[i] * max[i-1]));
            max[i] = Math.max(nums[i], Math.max(nums[i] * min[i-1], nums[i] * max[i-1]));
            res = Math.max(res, max[i]);
        }
        return res;
    }


    public int maxProduct(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return 0;
        int[] max = new int[len], min = new int[len];
        int res;
        res = max[0] = min[0] = nums[0];
        for (int i = 1; i < len; i++) {
            max[i] = Math.max(nums[i], Math.max(nums[i] * max[i-1], nums[i] * min[i-1]));
            min[i] = Math.min(nums[i], Math.min(nums[i] * max[i-1], nums[i] * min[i-1]));
            res = Math.max(res, max[i]);
        }
        return res;
    }
}
