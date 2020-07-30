package DP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 我能赢吗
 * 在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，先使得累计整数和达到 100 的玩家，即为胜者。
 * 如果我们将游戏规则改为 “玩家不能重复使用整数” 呢？
 * 例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。
 * 给定一个整数 maxChoosableInteger （整数池中可选择的最大数）和另一个整数 desiredTotal（累计和），判断先出手的玩家是否能稳赢（假设两位玩家游戏时都表现最佳）？
 * 你可以假设 maxChoosableInteger 不会大于 20， desiredTotal 不会大于 300。
 * 示例：
 * 输入：
 * maxChoosableInteger = 10
 * desiredTotal = 11
 * 输出：
 * false
 * 解释：
 * 无论第一个玩家选择哪个整数，他都会失败。
 * 第一个玩家可以选择从 1 到 10 的整数。
 * 如果第一个玩家选择 1，那么第二个玩家只能选择从 2 到 10 的整数。
 * 第二个玩家可以通过选择整数 10（那么累积和为 11 >= desiredTotal），从而取得胜利.
 * 同样地，第一个玩家选择任意其他整数，第二个玩家都会赢。
 */
public class A0464 {
    public static void main(String[] args) {
    }
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal)
            return true;
        // 如果所有可选择的数的和小于希望的, 则必输
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
        int[] memo = new int[maxChoosableInteger + 1];

        return backtrack(desiredTotal, memo, new HashMap<>());
    }
    public boolean backtrack(int remainTotal, int[] memo, Map<String, Boolean> map) {
        // 记忆矩阵
        // 转换成string后则可以判断(A选了3, B选了2)和(A选了2, B选了3)是相同的情况
        String key = Arrays.toString(memo);
        if (map.containsKey(key)) return map.get(key);
        for (int i = 1; i < memo.length; i++) {
            if (memo[i] == 0) {
                memo[i] = 1;
                if (remainTotal - i <= 0 || !backtrack(remainTotal - i, memo, map)) {
                    map.put(key, true);
                    memo[i] = 0;
                    return true;
                }
                // 回溯
                memo[i] = 0;
            }
        }
        map.put(key, false);
        return false;
    }
}
