package DP;

/**
 * 戳气球
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * 现在要求你戳破所有的气球。每当你戳破一个气球 i 时，你可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 * 求所能获得硬币的最大数量。
 * 说明:
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 示例:
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *      coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class A0312 {

    public int rMaxCoins(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return 0;
        len = len + 2;
        int[] newNums = new int[len];
        for (int i = 1; i < len-1; i++) {
            newNums[i] = nums[i-1];
        }
        newNums[0] = newNums[len-1] = 1;
        int[][] dp = new int[len][len];
        // 从左到右, 从下到上
        for (int i = len-1; i >= 0; i--) {
            for (int j = i + 2; j < len; j++) {
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + newNums[i] * newNums[j] * newNums[k]);
                }
            }
        }
        return dp[0][len-1];
    }











    public int maxCoins(int[] nums) {
        int len = nums.length;
        int[] newNums = new int[len + 2];
        newNums[0] = newNums[len + 1] = 1;
        for (int i = 1; i <= len; i++)
            newNums[i] = nums[i-1];
        len = len + 2;
        // dp[i][j]表示开区间(i,j)能获得的最大硬币数
        int[][] dp = new int[len][len];
        // 对于j <= i + 1的开区间初始化为0
        // 根据dp表, 从下往上, 从左往右
        // 状态转移
        //不就是想求戳破气球 i 和气球 j 之间的最高分数吗，如果「正向思考」，就只能写出前文的回溯算法；我们需要「反向思考」，想一想气球 i 和气球 j 之间最后一个被戳破的气球可能是哪一个？
        //其实气球 i 和气球 j 之间的所有气球都可能是最后被戳破的那一个，不防假设为 k。回顾动态规划的套路，这里其实已经找到了「状态」和「选择」：i 和 j 就是两个「状态」，最后戳破的那个气球 k 就是「选择」。
        //根据刚才对 dp 数组的定义，如果最后一个戳破气球 k，dp[i][j] 的值应该为：
        //dp[i][j] = dp[i][k] + dp[k][j]
        //         + points[i]*points[k]*points[j]
        //作者：labuladong
        //链接：https://leetcode-cn.com/problems/burst-balloons/solution/dong-tai-gui-hua-tao-lu-jie-jue-chuo-qi-qiu-wen-ti/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        for (int i = len-2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + newNums[i] * newNums[j] * newNums[k]);
                }
            }
        }
        return dp[0][len-1];
    }
}
