package other;

/**
 * 买卖股票的最佳时机IV
 *
 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 示例 1：
 输入：k = 2, prices = [2,4,1]
 输出：2
 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 示例 2：
 输入：k = 2, prices = [3,2,6,5,0,3]
 输出：7
 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 提示：
 0 <= k <= 109
 0 <= prices.length <= 1000
 0 <= prices[i] <= 1000
 */
public class A0188 {
    public static void main(String[] args) {
        int[] prices = {1,2};
        System.out.println(maxProfit(1, prices));
    }
    public static int maxProfit(int k, int[] prices) {
        if (k <= 0)
            return 0;
        int len = prices.length;
        if (k >= len / 2) {
            int res = 0;
            for (int i = 1; i < len; i++) {
                if (prices[i] > prices[i-1]) {
                    res += prices[i] - prices[i-1];
                }
            }
            return res;
        }
        // [交易次数][状态][天数]
        int[][][] dp = new int[k+1][2][len];
        // 第0天状态初始化
        for (int i = 0; i <= k; i++) {
            dp[i][0][0] = 0;
            dp[i][1][0] = -prices[0];
        }
        // 交易次数为0的初始化(每次买入算一次交易次数)
        for (int i = 0; i < len; i++) {
            dp[0][0][i] = 0;
            dp[0][1][i] = Integer.MIN_VALUE;
        }
        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= k; j++) {
                dp[j][0][i] = Math.max(dp[j][0][i-1], dp[j][1][i-1] + prices[i]);
                dp[j][1][i] = Math.max(dp[j][1][i-1], dp[j-1][0][i-1] - prices[i]);
            }
        }
        return dp[k][0][len-1];
    }
}
