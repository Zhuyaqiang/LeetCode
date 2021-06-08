package other;

import java.util.HashMap;
import java.util.Map;

/**
 * 停在原地的方案数
 *
 有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。

 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。

 给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。

 由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。



 示例 1：

 输入：steps = 3, arrLen = 2
 输出：4
 解释：3 步后，总共有 4 种不同的方法可以停在索引 0 处。
 向右，向左，不动
 不动，向右，向左
 向右，不动，向左
 不动，不动，不动
 示例  2：

 输入：steps = 2, arrLen = 4
 输出：2
 解释：2 步后，总共有 2 种不同的方法可以停在索引 0 处。
 向右，向左
 不动，不动
 示例 3：

 输入：steps = 4, arrLen = 2
 输出：8


 提示：

 1 <= steps <= 500
 1 <= arrLen <= 10^6
 */
public class A1269 {
    public static void main(String[] args) {
        System.out.println(numWays(3, 2));
        System.out.println(numWays(2, 4));
        System.out.println(numWays(4, 2));
        System.out.println(numWays(27, 7));
    }
    public static int numWays(int steps, int arrLen) {
        Map<String, Integer> map = new HashMap<>();
        recursion(steps, arrLen, 0, map);
        //2147483647
        //1000000007
        return map.getOrDefault(steps + "," + 0, 0) % 1000000007;
    }
    public static long recursion(int steps, int arrLen, int curr, Map<String, Integer> map) {
        if (steps == 0) {
            if (curr == 0) {
                return 1;
            }
            return 0;
        }
        String key = steps + "," + curr;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        int tempRes = 0;
        tempRes += recursion(steps - 1, arrLen, curr, map);
        tempRes = tempRes % 1000000007;
        if (curr - 1 >= 0) {
            tempRes += recursion(steps - 1, arrLen, curr - 1, map);
            tempRes = tempRes % 1000000007;
        }
        if (curr + 1 < arrLen) {
            tempRes += recursion(steps - 1, arrLen, curr + 1, map);
            tempRes = tempRes % 1000000007;
        }
        map.put(key, tempRes);
        return tempRes;
    }

    public static int numWays2(int steps, int arrLen) {
        final int MODULO = 1000000007;
        int maxColumn = Math.min(arrLen - 1, steps);
        int[][] dp = new int[steps + 1][maxColumn + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j <= maxColumn; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - 1 >= 0) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MODULO;
                }
                if (j + 1 <= maxColumn) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % MODULO;
                }
            }
        }
        return dp[steps][0];
    }
}
