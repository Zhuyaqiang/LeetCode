package DP;

/**
 * 最大矩形
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * 示例:
 * 输入:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * 输出: 6
 */
public class A0085 {
    // 暴力法
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0)
            return 0;
        int n = matrix[0].length;
        if (n == 0)
            return 0;
        int max = 0;
        int[] heights = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1')
                    heights[j] ++;
                else
                    heights[j] = 0;
            }
            max = Math.max(max, findMax(heights));
        }
        return max;
    }
    public int findMax(int[] heights) {
        int max = 0, currMin;
        int len = heights.length;
        for (int i = 0; i < len; i++) {
            currMin = Integer.MAX_VALUE;
            for (int j = i; j < len; j++) {
                currMin = Math.min(currMin, heights[j]);
                max = Math.max(max, currMin * (j - i + 1));
            }
        }
        return max;
    }

    // 动态规划
    // dp[i][j]表示第i行第j个数字是该行连续的第几个1
    public int maximalRectangle2(char[][] matrix) {
        int m = matrix.length;
        if (m == 0)
            return 0;
        int n = matrix[0].length;
        if (n == 0)
            return 0;
        int[][] dp = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = j == 0 ? 1 : dp[i][j-1] + 1;
                    int width = dp[i][j];
                    for (int k = i; k >= 0; k--) {
                        width = Math.min(width, dp[k][j]);
                        max = Math.max(max, width * (i - k + 1));
                    }
                }
            }
        }
        return max;
    }
}
