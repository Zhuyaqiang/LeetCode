package DP;

/**
 * 最大正方形
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 * 示例:
 * 输入:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * 输出: 4
 */
public class A0221 {
    public static void main(String[] args) {
        char[][] matrix =
                {
                        {'1', '0', '1', '0', '0'},
                        {'1', '0', '1', '1', '1'},
                        {'1', '1', '1', '1', '1'},
                        {'1', '0', '0', '1', '0'}
                };
        System.out.println(maximalSquare(matrix));
    }

    // 暴力法
    // 从上到下遍历每行, 获取每行的列高度, 再根据每行的列高度遍历获得每行向上的最大正方形面积
    public static int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        if (m == 0)
            return 0;
        int n = matrix[0].length;
        if (n == 0)
            return 0;
        int max = 0;
        int[] heights = new int[n];
        for (int i = 0; i < m; i++) {
            if (i == 0) {
                for (int j = 0; j < n; j++) {
                    if (matrix[0][j] == '1')
                        heights[j] = 1;
                }
            } else {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '1')
                        heights[j] = heights[j] + 1;
                    else
                        heights[j] = 0;
                }
            }
            int ans = check(heights, n);
            max = Math.max(max, ans);
        }
        return max;
    }

    public static int check(int[] heights, int len) {
        int max = 0;
        for (int i = 0; i < len; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < len; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                if ((j - i + 1) == minHeight)
                    max = Math.max(max, (j - i + 1) * minHeight);
            }
        }
        return max;
    }
    // 动态规划
    public int maximalSquare2(char[][] matrix) {
        int m = matrix.length;
        if (m == 0)
            return 0;
        int n = matrix[0].length;
        if (n == 0)
            return 0;
        // dp[i][j]存的是以(i,j)为右下角的正方形的最大边长
        int[][] dp = new int[m][n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0)
                        dp[i][j] = 1;
                    else {
                        dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                    }
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        return ans * ans;
    }
}
