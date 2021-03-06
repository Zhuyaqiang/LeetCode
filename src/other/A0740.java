package other;

/**
 * 删除并获得点数
 * 给你一个整数数组 nums ，你可以对它进行一些操作。
 *
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。
 *
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,4,2]
 * 输出：6
 * 解释：
 * 删除 4 获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 * 示例 2：
 *
 * 输入：nums = [2,2,3,3,3,4]
 * 输出：9
 * 解释：
 * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i] <= 104
 */
public class A0740 {
    public int deleteAndEarn(int[] nums) {
        int[] count = new int[10001];
        int max = 0;
        for (int num : nums) {
            count[num]++;
            max = Math.max(max, num);
        }
        int[][] dp = new int[max + 1][2];
        // dp[i][0]表示不选i， dp[i][1]表示选i
        // dp[0][0] = 0, dp[0][1] = 0
        // dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1])
        // dp[i][1] = dp[i - 1][0] + i * count[i]
        for (int i = 1; i <= max; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + i * count[i];
        }
        return Math.max(dp[max][0], dp[max][1]);
    }
}
