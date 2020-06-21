package DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三角形的最小路径和
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 例如，给定三角形：
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 说明：
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
public class A0120 {
    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(new ArrayList<>(Arrays.asList(2)));
        triangle.add(new ArrayList<>(Arrays.asList(3, 4)));
        triangle.add(new ArrayList<>(Arrays.asList(6, 5, 7)));
        triangle.add(new ArrayList<>(Arrays.asList(4, 1, 8, 3)));
        int i = minimumTotal2(triangle);
        System.out.println(i);
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        int n = triangle.get(m - 1).size();
        int[][] dp = new int[m][n];
        dp[0][0] = triangle.get(0).get(0);
        int max = Integer.MAX_VALUE;
        for (int i = 1; i < m; i++) {
            List<Integer> integers = triangle.get(i);
            dp[i][0] = dp[i - 1][0] + integers.get(0);
            for (int j = 1; j < integers.size(); j++) {
                if (j == i)
                    dp[i][j] = dp[i - 1][j - 1] + integers.get(j);
                else
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + integers.get(j);
            }
        }
        for (int res : dp[m - 1]) {
            max = Math.min(max, res);
        }
        return max;
    }

    // 动态规划优化
    public static int minimumTotal2(List<List<Integer>> triangle) {
        int m = triangle.size();
        int n = triangle.get(m-1).size();
        int[] dp = new int[n];
        dp[0] = triangle.get(0).get(0);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < m; i++) {
            List<Integer> integers = triangle.get(i);
            for (int j = integers.size() - 1; j >= 0; j--) {
                if (i == j)
                    dp[j] = dp[j-1] + integers.get(j);
                else if (j == 0)
                    dp[j] = dp[j] + integers.get(j);
                else
                    dp[j] = Math.min(dp[j], dp[j-1]) + integers.get(j);
            }
        }
        for (int res : dp) {
            min = Math.min(res, min);
        }
        return min;
    }
}
