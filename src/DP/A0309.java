package DP;


/**
 * 买卖股票的最佳时机含冷冻期
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */
public class A0309 {
    public static void main(String[] args) {
        int[] prices = {1,2,3,0,2};
        System.out.println(rMaxProfit(prices));
    }
    public static int rMaxProfit(int[] prices) {
        int len = prices.length;
        if (len <= 0)
            return 0;
        // dp[i][0]: 第i+1天不持有股票
        // dp[i][1]: 第i+1天持有股票
        int preHold, preNoHold, prePreNoHold;
        preHold = - prices[0];
        preNoHold = prePreNoHold = 0;
        for (int i = 1; i < len; i++) {
            int tempNoHold = preNoHold;
            preNoHold = Math.max(preNoHold, preHold + prices[i]);
            preHold = Math.max(preHold, prePreNoHold - prices[i]);
            prePreNoHold = tempNoHold;
        }
        return preNoHold;
    }




    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2)
            return 0;
        // dp[i][j][k], 第i天, (j = 0:持有, j = 1:不持有)
        int[][] dp = new int[len][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < len; i++) {
            // 第i天持有, 可能是1之前就持有, 2之前未持有, 第i天买的(注意冷冻期)
            dp[i][0] = Math.max(dp[i-1][0], i == 1 ? -prices[i] : dp[i-2][1] - prices[i]);
            // 第i天未持有, 可能是之前就未持有, 或之前持有第i天卖的
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + prices[i]);
        }
        return dp[len-1][1];
    }
    // 空间复杂度优化
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        if (len < 2)
            return 0;
        int preHold, preNoHold, prePreNoHold;
        preHold = -prices[0];
        preNoHold = prePreNoHold = 0;
        for (int i = 1; i < len; i++) {
            int tempHold = preHold, tempNoHold = preNoHold;
            preHold = Math.max(preHold, prePreNoHold - prices[i]);
            preNoHold = Math.max(preNoHold, tempHold + prices[i]);
            prePreNoHold = tempNoHold;
        }
        return preNoHold;
    }
}
