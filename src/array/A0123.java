package array;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

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
        int[] prices = {3,3,5,0,0,3,1,4};
        System.out.println(maxProift(prices));
    }
    /*
        dp[i][j]表示[0,i]天 状态j 的利润
        j = 0: 没有开始交易
        j = 1: 第一次买入
        j = 2: 第一次卖出
        j = 3: 第二次买入
        j = 4: 第二次卖出
        最大利润只可能是dp[len-1][2] 或 dp[len-1][4]
     */
    public static int maxProift(int[] prices) {
        int len = prices.length;
        if (len < 2)
            return 0;
        int[][] dp = new int[len][5];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = Integer.MIN_VALUE;
        dp[0][3] = Integer.MIN_VALUE;
        dp[0][4] = Integer.MIN_VALUE;
        for (int i = 1; i < len; i++) {
            dp[i][0] = 0;  // 没有卖出过的收益永远是0
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] == Integer.MIN_VALUE ? Integer.MIN_VALUE : dp[i-1][0] - prices[i]); // 第一次买入的情况可能是  之前就买入过  或者  在第i天才买入(这种情况要防止相减溢出)
            dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1] + prices[i]); // 第一次卖出的情况可能是  之前就卖出过  或者在  第i天才卖出
            dp[i][3] = Math.max(dp[i-1][3], dp[i-1][2] == Integer.MIN_VALUE ? Integer.MIN_VALUE : dp[i-1][2] - prices[i]); // 第二次买入的情况可能是  之前就买入两次  或者  在之前卖出一次, 第i天第二次买入(这种情况要防止相减溢出)
            dp[i][4] = Math.max(dp[i-1][4], dp[i-1][3] + prices[i]); // 第二次卖出的情况可能是  之前就卖出过两次  或者  在之前买入过两次, 第i天第二次卖出
        }
        return Math.max(0, Math.max(dp[len-1][2], dp[len-1][4]));
    }
}
