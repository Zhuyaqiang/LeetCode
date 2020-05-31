package other;

/**
 * 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * 示例 1:
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2:
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 */
public class A0198 {
    public static void main(String[] args) {
        int[] nums = {2,1,1,2};
        System.out.println(rob2(nums));
    }
    // 递归, 超时
    public static int rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        backtrack(nums, 0, 0);
        if (nums.length >= 2)
            backtrack(nums, 1, 0);
        return res;
    }
    public static int res = 0;
    public static void backtrack(int[] nums, int start, int max) {
        max += nums[start];
        if (start >= nums.length - 2) {
            res = Math.max(res, max);
            return;
        }
        for (int i = start + 2; i < nums.length; i++) {
            backtrack(nums, i, max);
        }

    }

    // 动态规划
    public static int rob2(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        if (len == 0)
            return 0;
        if (len == 1)
            return nums[0];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        int max = Math.max(dp[0], dp[1]);
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }
        return Math.max(dp[len-1], dp[len-2]);
    }
}
