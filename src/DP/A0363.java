package DP;

/**
 * 矩形区域不超过K的最大数值和
 * 给定一个非空二维矩阵 matrix 和一个整数 k，找到这个矩阵内部不大于 k 的最大矩形和。
 * 示例:
 * 输入: matrix = [[1,0,1],[0,-2,3]], k = 2
 * 输出: 2
 * 解释: 矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
 * 说明：
 * 矩阵内的矩形区域面积必须大于 0。
 * 如果行数远大于列数，你将如何解答呢？
 */
public class A0363 {
    public static void main(String[] args) {
//        int[][] matrix = {{2,2,-1}};
        int[][] matrix = {{2,2,-1}};
        System.out.println(rMaxSumSubmatrix(matrix,0));
    }

    public static int rMaxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        if (m == 0)
            return 0;
        int n = matrix[0].length;
        if (n == 0)
            return 0;
        int ans = Integer.MIN_VALUE;
        for (int l = 0; l < n; l++) {
            int[] rowSum = new int[m];
            for (int r = l; r < n; r++) {
                for (int i = 0; i < m; i++) {
                    rowSum[i] += matrix[i][r];
                }
                for (int i = 0; i < m; i++) {
                    int sum = 0;
                    for (int j = i; j < m; j++) {
                        sum += rowSum[j];
                        if (sum < k) {
                            ans = Math.max(ans, sum);
                        }
                        if (sum == k) {
                            return k;
                        }
                    }
                }
            }
        }
        return ans;
    }
        // 动态规划, 超出内存限制,记录左上角和右下角
    public static int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        if (m == 0)
            return 0;
        int n = matrix[0].length;
        if (n == 0)
            return 0;
        int ans = Integer.MIN_VALUE;
        int[][][][] dp = new int[m+1][n+1][m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j][i][j] = matrix[i-1][j-1];
                for (int i2 = i; i2 <= m; i2++) {
                    for (int j2 = j; j2 <= n; j2++) {
                        dp[i][j][i2][j2] = dp[i][j][i2-1][j2] + dp[i][j][i2][j2-1] - dp[i][j][i2-1][j2-1] + matrix[i2-1][j2-1];
                        if (dp[i][j][i2][j2] <= k)
                            ans = Math.max(ans, dp[i][j][i2][j2]);
                    }
                }
            }
        }
        return ans;
    }

    // 动态规划, 只记录右下角
    // dp[i][j]表示以(i,j)为右下角的矩形最大数值
    public static int maxSumSubmatrix2(int[][] matrix, int k) {
        int m = matrix.length;
        if (m == 0)
            return 0;
        int n = matrix[0].length;
        if (n == 0)
            return 0;
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int[][] dp = new int[m+1][n+1];
                dp[i][j] = matrix[i-1][j-1];
                for (int i2 = i; i2 <= m; i2++) {
                    for (int j2 = j; j2 <= n; j2++) {
                        dp[i2][j2] = dp[i2-1][j2] + dp[i2][j2-1] - dp[i2-1][j2-1] + matrix[i2-1][j2-1];
                        if (dp[i2][j2] <= k)
                            ans = Math.max(ans, dp[i2][j2]);
                    }
                }
            }
        }
        return ans;
    }

    // 固定左右边界, 计算每行累加的和
    // 通过每行累加和计算符合的值
    public static int maxSumSubmatrix3(int[][] matrix, int k) {
        int m = matrix.length;
        if (m == 0)
            return 0;
        int n = matrix[0].length;
        if (n == 0)
            return 0;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int[] rowSum = new int[m];
            for (int j = i; j < n; j++) {
                for (int q = 0; q < m; q++)
                    rowSum[q] += matrix[q][j];

                ans = Math.max(ans, findMax(rowSum, k));
            }
        }
        return ans;
    }
    public static int findMax(int[] rowSum, int k) {
        int sum = rowSum[0], rowMax = sum;
        for (int i = 1; i < rowSum.length; i++) {
            if (sum > 0)
                sum += rowSum[i];
            else {
                sum = rowSum[i];
            }
            rowMax = Math.max(rowMax, sum);
        }
        if (rowMax <= k)
            return rowMax;
        rowMax = Integer.MIN_VALUE;
        for (int i = 0; i < rowSum.length; i++) {
            sum = 0;
            for (int j = i; j < rowSum.length; j++) {
                sum += rowSum[j];
                if (sum <= k) rowMax = Math.max(rowMax, sum);
                if (rowMax == k)
                    return k;
            }
        }
        return rowMax;
    }
}
