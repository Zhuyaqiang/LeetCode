package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三角形最小路径和
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 例如，给定三角形：
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 说明：
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
public class A0120 {
    static int res = Integer.MAX_VALUE;
    static int count = 0;

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3,4));
        triangle.add(Arrays.asList(6,5,7));
        triangle.add(Arrays.asList(4,1,8,3));
//        triangle.add(Arrays.asList(-10));
        System.out.println(minimumTotal3(triangle));
    }

    // 递归, 超时
    public static int minimumTotal(List<List<Integer>> triangle) {
        backtrack(triangle, 0, 0, 0);
        return res;
    }
    public static void backtrack(List<List<Integer>> triangle, int index, int max, int nowIndex) {
        System.out.println("index: " + index + ", max: " + max + ", nowIndex: " + nowIndex);
        if (index == triangle.size()) {
            res = Math.min(res, max);
            return;
        }
        List<Integer> currRow = triangle.get(index);
        count ++;
        for(int i = nowIndex; i < currRow.size() && i <= nowIndex + 1; i++) {
            backtrack(triangle, index + 1, max + currRow.get(i), i);
        }
    }


    // 动态规划
    public static int minimumTotal2(List<List<Integer>> triangle) {
        int m = triangle.size();
        int n = triangle.get(m - 1).size();
        int[][] dp = new int[m][n];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++){
                dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-1]) + triangle.get(i).get(j);
            }
            dp[i][i] = dp[i-1][i-1] + triangle.get(i).get(i);
        }
        int res = dp[m-1][0];
        for (int i = 1; i < m; i++)
            res = Math.min(res, dp[m-1][i]);
        return res;
    }
    // 动态规划, 优化
    public static int minimumTotal3(List<List<Integer>> triangle) {
        int m = triangle.size();
        int n = triangle.get(m - 1).size();
        int[] dp = new int[m];
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < m; i++) {
            for (int j = i; j >=0; j--) {
                if (j == 0)
                    dp[j] = dp[j] + triangle.get(i).get(j);
                else if (j == i)
                    dp[j] = dp[j-1] + triangle.get(i).get(j);
                else
                    dp[j] = Math.min(dp[j-1], dp[j]) + triangle.get(i).get(j);
            }
        }
        int res = dp[0];
        for (int i : dp) {
            res = Math.min(res, i);
        }
        return res;
    }
}
