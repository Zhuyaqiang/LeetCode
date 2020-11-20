package other;

/**
 * 最佳观光组合
 *
 给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。
 一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。
 返回一对观光景点能取得的最高分。
 示例：
 输入：[8,1,5,2,6]
 输出：11
 解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 提示：
 2 <= A.length <= 50000
 1 <= A[i] <= 1000
 */
public class A1014 {
    public static void main(String[] args) {
        int[] A = {8,1,5,2,6};
        int i = maxScoreSightseeingPair3(A);
        System.out.println(i);
    }
    public int rMaxScoreSightseeingPair(int[] A) {
        int len = A.length;
        if (len <= 1)
            return 0;
        int[] dp = new int[len];
        dp[0] = A[0];
        for (int i = 0; i < len; i++)
            dp[i] = A[i] + i;
        int max = Integer.MIN_VALUE, res = Integer.MIN_VALUE;
        for (int i = 1; i < len; i++) {
            max = Math.max(dp[i-1], max); // 第i个景点前价值最高的景点(评分+距离)
            res = Math.max(res, A[i] + max - i);
        }
        return res;
    }
    // 暴力法, 超时
    public int maxScoreSightseeingPair(int[] A) {
        int len = A.length;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < len-1; i++) {
            for (int j = i + 1; j < len; j++) {
                int val = A[j] + A[i] + i - j;
                res = Math.max(res, val);
            }
        }
        return res;
    }
    // 动态规划
    public static int maxScoreSightseeingPair2(int[] A) {
        int len = A.length;
        int[] dp = new int[len];
        for (int i = 0; i < len; i++)
            dp[i] = i + A[i];
        int res = Integer.MIN_VALUE;
        int max = dp[0];
        for (int i = 1; i < len; i++) {
            max = Math.max(max, dp[i-1]);
            res = Math.max(max + A[i] - i, res);
        }
        return res;
    }
    // 动态规划优化
    public static int maxScoreSightseeingPair3(int[] A) {
        int len = A.length;
        int max = A[0];
        int res = Integer.MIN_VALUE;
        for (int i = 1; i < len; i++) {
            max = Math.max(max, A[i-1] + i - 1);
            res = Math.max(res, max + A[i] - i);
        }
        return res;
    }
}
