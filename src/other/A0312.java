package other;

/**
 * 戳气球
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * 现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 * 求所能获得硬币的最大数量。
 * 说明:
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 示例:
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *      coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class A0312 {
    public int maxCoins(int[] nums) {
        int len = nums.length;
        int[] newNums = new int[len+2];
        for (int i = 1; i <= len; i++) {
            newNums[i] = nums[i-1];
        }
        len += 2;
        newNums[0] = newNums[len-1] = 1;
        int[][] dp = new int[len][len]; // dp[i][j]表示(i,j)能获得的最大硬币数
        for (int i = len-2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + newNums[i] * newNums[j] * newNums[k]);
                }
            }
        }
        return dp[0][len-1];
    }
}
