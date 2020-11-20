package DP;

/**
 * 买卖股票的最佳时机3
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 示例 1:
 * 输入: [3,3,5,0,0,3,1,4]
 * 输出: 6
 * 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 示例 2:
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 */
public class A0123 {
    public static void main(String[] args) {
        int[] price = {7,6,4,3,1};
        System.out.println(rMaxProfit(price));
    }
    public static int rMaxProfit(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        int[][] dp = new int[prices.length][5];
        // 0第一次买入, 1第一次卖出, 2第二次买入, 3第二次卖出, 4没买入没卖出过
        dp[0][0] = -prices[0];
        dp[0][1] = Integer.MIN_VALUE;
        dp[0][2] = Integer.MIN_VALUE;
        dp[0][3] = Integer.MIN_VALUE;
        dp[0][4] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][4] = 0;
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][4] == Integer.MIN_VALUE ? Integer.MIN_VALUE : dp[i-1][4] - prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + prices[i]);
            dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1] == Integer.MIN_VALUE ? Integer.MIN_VALUE : dp[i-1][1] - prices[i]);
            dp[i][3] = Math.max(dp[i-1][3], dp[i-1][2] + prices[i]);
        }
        return Math.max(0, Math.max(dp[prices.length-1][1], dp[prices.length-1][3]));
    }


    // dp[i][j]表示第i天j状态的最大利润
    // j=0:没买入没卖出过  j=1:第一次买入  j=2:第一次卖出  j=3:第二次买入  j=4:第二次卖出
    // dp[0][0] = 0,  dp[0][1] = -prices[0],  dp[0][2] = -Integer.MIN_VALUE, dp[0][3] = -Integer.MIN_VALUE, dp[0][4] = -Integer.MIN_VALUE
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2)
            return 0;
        int[][] dp = new int[len][5];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = dp[0][3] = dp[0][4] = Integer.MIN_VALUE;
        for (int i = 1; i < len; i++) {
            dp[i][0] = 0;
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] == Integer.MIN_VALUE ? Integer.MIN_VALUE : dp[i-1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i-1][3], dp[i-1][2] == Integer.MIN_VALUE ? Integer.MIN_VALUE : dp[i-1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i-1][4], dp[i-1][3] + prices[i]);
        }
        return Math.max(Math.max(dp[len-1][2], dp[len-1][4]), 0);
    }
}
