package other;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 等价多米诺骨牌对的数量
 * 给你一个由一些多米诺骨牌组成的列表 dominoes。
 *
 * 形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。
 * 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
 * 示例：
 * 输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
 * 输出：1
 * 提示：
 * 1 <= dominoes.length <= 40000
 * 1 <= dominoes[i][j] <= 9
 */
public class A1128 {
    public int numEquivDominoPairs(int[][] dominoes) {
        int len = dominoes.length;
        Map<String, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < len; i++) {
            int[] dominoe = dominoes[i];
            String key = dominoe[0] <= dominoe[1] ? dominoe[0] * 10 + "" + dominoe[1] : dominoe[1] + "" + dominoe[0];
            if (map.containsKey(key)) {
                res += map.get(key);
            }
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        return res;
    }

    public int numEquivDominoPairs2(int[][] dominoes) {
        int res = 0;
        int[] count = new int[100];
        for (int[] dominoe : dominoes) {
            int val = dominoe[0] <= dominoe[1] ? dominoe[0] * 10 + dominoe[1] : dominoe[1] * 10 + dominoe[0];
            res += count[val];
            count[val] ++;
        }
        return res;
    }
}
