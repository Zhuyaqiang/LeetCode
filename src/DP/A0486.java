package DP;

/**
 * 预测赢家
 * 给定一个表示分数的非负整数数组。 玩家1从数组任意一端拿取一个分数，随后玩家2继续从剩余数组任意一端拿取分数，然后玩家1拿，……。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。
 * 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
 * 示例 1:
 * 输入: [1, 5, 2]
 * 输出: False
 * 解释: 一开始，玩家1可以从1和2中进行选择。
 * 如果他选择2（或者1），那么玩家2可以从1（或者2）和5中进行选择。如果玩家2选择了5，那么玩家1则只剩下1（或者2）可选。
 * 所以，玩家1的最终分数为 1 + 2 = 3，而玩家2为 5。
 * 因此，玩家1永远不会成为赢家，返回 False。
 * 示例 2:
 * 输入: [1, 5, 233, 7]
 * 输出: True
 * 解释: 玩家1一开始选择1。然后玩家2必须从5和7中进行选择。无论玩家2选择了哪个，玩家1都可以选择233。
 * 最终，玩家1（234分）比玩家2（12分）获得更多的分数，所以返回 True，表示玩家1可以成为赢家。
 * 注意:
 * 1 <= 给定的数组长度 <= 20.
 * 数组里所有分数都为非负数且不会大于10000000。
 * 如果最终两个玩家的分数相等，那么玩家1仍为赢家。
 */
public class A0486 {
    public static void main(String[] args) {
        System.out.println(PredictTheWinner2(new int[]{1, 5, 2}));
    }
    public static boolean PredictTheWinner(int[] nums) {
        return backtrack(nums, 0, nums.length - 1, 1) >= 0;
    }

    // 返回值为先手玩家再[s,e]范围内领先后受玩家的分数
    private static int backtrack(int[] nums, int s, int e, int turn) {
        if (s == e)
            return nums[e] * turn;
        int a = turn * nums[s] + backtrack(nums, s + 1, e, -turn);
        int b = turn * nums[e] + backtrack(nums, s, e - 1, -turn);
        return turn * Math.max(turn * a, turn * b);
    }
    // 动态规划
    // dp[i][j]表示先手玩家从nums[i]拿到nums[j]时, 比后手玩家领先的分数
    // 拿nums[i], dp[i][j] = nums[i] + (-dp[i+1][j]), 即nums[i]减去后手玩家从i+1拿到j的最大领先分数
    // 拿nums[j], dp[i][j] = nums[j] + (-dp[i][j-1])
    // 初始化: dp[i][i], 从i拿到i, 只有一个数, 先手玩家比后手玩家领先nums[i]
    // dp table 从下往上, 从左往右
    public static boolean PredictTheWinner2(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++)
            dp[i][i] = nums[i];
        for (int i = len - 1; i >= 0; i --) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i+1][j], nums[j] - dp[i][j-1]);
            }
        }
        return dp[0][len-1] >= 0;
    }
}
