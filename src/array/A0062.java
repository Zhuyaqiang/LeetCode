package array;

import java.util.Arrays;

/**
 * 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 问总共有多少条不同的路径？
 示例 1:
 输入: m = 3, n = 2
 输出: 3
 解释:
 从左上角开始，总共有 3 条路径可以到达右下角。
 1. 向右 -> 向右 -> 向下
 2. 向右 -> 向下 -> 向右
 3. 向下 -> 向右 -> 向右
 示例 2:
 输入: m = 7, n = 3
 输出: 28
 */
public class A0062 {
    public static void main(String[] args) {
        int i = uniquePaths3(7, 3);
        System.out.println(i);
    }
    public static int uniquePaths(int m, int n) {
        if (m == 1 || n == 1)
            return 1;
        int[][] res = new int[m][n];
        res[0][0] = 1;
        for (int i = 0; i < n; i++) {
            res[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            res[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                res[i][j] = res[i-1][j] + res[i][j-1];
            }
        }
        return res[m-1][n-1];
    }
    public static int uniquePaths2(int m, int n) {
        int[] a = new int[n];
        int[] b = new int[n];
        Arrays.fill(a, 1);
        Arrays.fill(b, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                a[j] = a[j-1] + b[j];
            }
            for (int j = 0; j < n; j++)
                b[j] = a[j];
        }
        return a[n-1];
    }
    public static int uniquePaths3(int m, int n) {
        int[] a = new int[n];
        Arrays.fill(a, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                a[j] = a[j-1] + a[j];
            }
        }
        return a[n-1];
    }
}
