package other;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 跳水板
 * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
 * 返回的长度需要从小到大排列。
 * 示例：
 * 输入：
 * shorter = 1
 * longer = 2
 * k = 3
 * 输出： {3,4,5,6}
 * 提示：
 * 0 < shorter <= longer
 * 0 <= k <= 100000
 */
public class M1611 {
    public static void main(String[] args) {
        int[] ints = divingBoard(1, 1, 0);
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
    }
    // 暴力法
    public static int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0)
            return new int[]{};
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <= k; i++) {
            int val = i * shorter + (k - i) * longer;
            set.add(val);
        }
        int[] res = new int[set.size()];
        int index = 0;
        for (Integer integer : set) {
            res[index] = integer;
            index++;
        }
        Arrays.sort(res);
        return res;
    }

    // 排除两种极端情况
    public static int[] divingBoard2(int shorter, int longer, int k) {
        if (k == 0)
            return new int[]{};
        if (longer == shorter)
            return new int[]{k * longer};
        int[] res = new int[k+1];
        for (int i = 0; i <= k; i++) {
            res[i] = i * longer + (k - i) * shorter;
        }
        return res;
    }
}
