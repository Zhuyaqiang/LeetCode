package other;

import java.util.HashMap;
import java.util.Map;

/**
 * 石子游戏
 * <p>
 * 亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。
 * <p>
 * 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
 * <p>
 * 亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
 * <p>
 * 假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：[5,3,4,5]
 * 输出：true
 * 解释：
 * 亚历克斯先开始，只能拿前 5 颗或后 5 颗石子 。
 * 假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
 * 如果李拿走前 3 颗，那么剩下的是 [4,5]，亚历克斯拿走后 5 颗赢得 10 分。
 * 如果李拿走后 5 颗，那么剩下的是 [3,4]，亚历克斯拿走后 4 颗赢得 9 分。
 * 这表明，取前 5 颗石子对亚历克斯来说是一个胜利的举动，所以我们返回 true 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= piles.length <= 500
 * piles.length 是偶数。
 * 1 <= piles[i] <= 500
 * sum(piles) 是奇数。
 */
public class A0877 {
    public static void main(String[] args) {
        System.out.println(stoneGame(new int[]{7, 7, 12, 16, 41, 48, 41, 48, 11, 9, 34, 2, 44, 30, 27, 12, 11, 39, 31, 8, 23, 11, 47, 25, 15, 23, 4, 17, 11, 50, 16, 50, 38, 34, 48, 27, 16, 24, 22, 48, 50, 10, 26, 27, 9, 43, 13, 42, 46, 24}));
    }

    public static boolean stoneGame(int[] piles) {
        return recursion(piles, 0, piles.length - 1, 1, new HashMap<>()) > 0;
    }

    public static int recursion(int[] piles, int l, int r, int flag, Map<String, Integer> map) {
        if (l == r) {
            return flag * piles[l];
        }
        String key = l + "," + r + "," + flag;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        if (flag == 1) {
            int val = Math.max(recursion(piles, l + 1, r, -flag, map) + piles[l], recursion(piles, l, r - 1, -flag, map) + piles[r]);
            map.put(key, val);
            return val;
        }
        int val = Math.min(recursion(piles, l + 1, r, -flag, map) - piles[l], recursion(piles, l, r - 1, -flag, map) - piles[r]);
        map.put(key, val);
        return val;
    }

    public static boolean stoneGame2(int[] piles) {
        int len = piles.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = piles[i];
        }
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        return dp[0][len - 1] > 0;
    }
}
