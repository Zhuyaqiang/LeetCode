package other;

/**
 * 分割等和子集
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 注意:
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 * 示例 2:
 * 输入: [1, 2, 3, 5]
 * 输出: false
 * 解释: 数组不能分割成两个元素和相等的子集.
 */
public class A0416 {
    public static void main(String[] args) {
        int[] nums = {1,5,11,5};
//        int[] nums = {1,2,3,5};
        System.out.println(canPartition(nums));
    }
    // 递归, 超时
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0)
            return false;
        return backtrack(nums, sum / 2, 0, 0);
    }
    public static boolean backtrack(int[] nums, int target, int curr, int index) {
        if (target == curr)
            return true;
        if (target < curr)
            return false;
        for (int i = index; i < nums.length; i++) {
            if (backtrack(nums, target, curr + nums[index], i + 1))
                return true;
        }
        return false;
    }
    // 0-1背包
    // dp[i][j]表示[0,i]区间挑选若干个数和为j
    // dp[i][j]有两种情况, 取第i个或不取第i个
    // 取第i个, dp[i][j] = dp[i-1][j-nums[i]]
    // 不取第i个, dp[i][j] = dp[i-1][j]
    // 初始化: dp[0][0] = true, dp[0][1到j] = false
    //        dp[i][0] = true
    public static boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0)
            return false;
        int len = nums.length;
        sum  = sum / 2;
        boolean[][] dp = new boolean[len][sum + 1];
        for (int i = 0; i < len; i++)
            dp[i][0] = true;
        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i-1][j];
                if (j - nums[i] >= 0)
                    dp[i][j] |= dp[i-1][j-nums[i]];
            }
        }
        return dp[len-1][sum];
    }
}
