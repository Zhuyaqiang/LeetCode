package DP;

/**
 * 最小路径和
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * 示例:
 * 输入:
 * [
 *   [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 */
public class A0064 {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        if (m == 0)
            return 0;
        int n = grid[0].length;
        if (n == 0)
            return 0;
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            if (i == 0) {
                dp[0] = grid[i][0];
                for (int j = 1; j < n; j++) {
                    dp[j] = dp[j - 1] + grid[i][j];
                }
            } else {
                dp[0] = dp[0] + grid[i][0];
                for (int j = 1; j < n; j++) {
                    dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
                }
            }
        }
        return dp[n-1];
    }
}
