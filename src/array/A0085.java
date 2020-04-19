package array;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 最大矩形
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * 示例:
 * 输入:
 * [
 * ["1","0","1","0","0"],
 * ["1","0","1","1","1"],
 * ["1","1","1","1","1"],
 * ["1","0","0","1","0"]
 * ]
 * 输出: 6
 */
public class A0085 {
    public static void main(String[] args) {
        char[][] area = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println(maximalRectangle2(area));
    }
    // 把每一行当做柱状图处理, 动态规划
    // dp[i][j]表示第i行第j列的数字是改行连续的第几个1
    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;
        int m = matrix.length;
        if (matrix[0] == null || matrix[0].length == 0)
            return 0;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = j == 0 ? 1 : dp[i][j - 1] + 1;
                    int width = dp[i][j];
                    for (int k = i; k >= 0; k--) {
                        width = Math.min(width, dp[k][j]);
                        res = Math.max(res, width * (i - k + 1));
                    }
                }
            }
        }
        return res;
    }
    // 当做柱状图处理, 单调栈
    public static int maximalRectangle2(char[][] matrix) {
        int res = 0;
        if (matrix == null || matrix.length == 0)
            return 0;
        int m = matrix.length;
        if (matrix[0] == null || matrix[0].length == 0)
            return 0;
        int n = matrix[0].length;
        int[] dp = new int[n]; // 对应每一列连续的1
        /**
         * 如
         * ["1","0","1","0","0"],
         * ["1","0","1","1","1"],
         * ["1","1","1","1","1"],
         * ["1","0","0","1","0"]
         * 每一行对应的dp分别为
         * [4,0,3,0,0],
         * [3,0,2,3,2],
         * [2,1,1,2,1],
         * [1,0,0,1,0]
         */

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[j] = matrix[i][j] == '1' ? dp[j] + 1 : 0;
            }
            res = Math.max(res, largestRectangleArea4(dp));
        }

        for (int i = 0; i < m; i++) {

        }

        return res;
    }

    public static int largestRectangleArea4(int[] heights) {
        if (heights == null || heights.length == 0)
            return 0;
        int len = heights.length;
        if (len == 1)
            return heights[0];
        int [] newHeight = new int[len+2];
        newHeight[0] = 0;
        for (int i = 0; i < len; i++) {
            newHeight[i + 1] = heights[i];
        }
        len += 2;
        heights = newHeight;
        int res = 0;
        // 先在数组头尾加0, 作为边界条件
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(0);
        for (int i = 1; i < len; i++) {
            // 指针指向严格小于栈顶指向时, 可以计算栈顶高度的最大面积
            while (heights[i] < heights[stack.peekLast()]) {
                int currHeight = heights[stack.pollLast()];
                int currWidth = i - stack.peekLast() - 1;
                res = Math.max(res, currHeight * currWidth);
            }
            stack.addLast(i);
        }
        return res;
    }
}
