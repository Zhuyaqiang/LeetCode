package DP;

import java.util.*;

/**
 * 青蛙过河
 * 一只青蛙想要过河。 假定河流被等分为 x 个单元格，并且在每一个单元格内都有可能放有一石子（也有可能没有）。
 * 青蛙可以跳上石头，但是不可以跳入水中。
 * 给定石子的位置列表（用单元格序号升序表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一个石子上）。 
 * 开始时， 青蛙默认已站在第一个石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格1跳至单元格2）。
 * 如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1个单位。 另请注意，
 * 青蛙只能向前方（终点的方向）跳跃。
 * 请注意：
 * 石子的数量 ≥ 2 且 < 1100；
 * 每一个石子的位置序号都是一个非负整数，且其 < 231；
 * 第一个石子的位置永远是0。
 * 示例 1:
 * [0,1,3,5,6,8,12,17]
 * 总共有8个石子。
 * 第一个石子处于序号为0的单元格的位置, 第二个石子处于序号为1的单元格的位置,
 * 第三个石子在序号为3的单元格的位置， 以此定义整个数组...
 * 最后一个石子处于序号为17的单元格的位置。
 * 返回 true。即青蛙可以成功过河，按照如下方案跳跃：
 * 跳1个单位到第2块石子, 然后跳2个单位到第3块石子, 接着
 * 跳2个单位到第4块石子, 然后跳3个单位到第6块石子,
 * 跳4个单位到第7块石子, 最后，跳5个单位到第8个石子（即最后一块石子）。
 * 示例 2:
 * [0,1,2,3,4,8,9,11]
 * 返回 false。青蛙没有办法过河。
 * 这是因为第5和第6个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。
 */
public class A0403 {
    public static void main(String[] args) {
        int[] stones = {0,1,2,3,4,8,9,11};
        System.out.println(canCross(stones));
    }


    public static boolean canCross(int[] stones) {
        int len = stones.length;
        if (len == 0)
            return true;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Set<Integer> temp = new HashSet<>();
        temp.add(0);
        map.put(stones[0], temp);
        for (int i = 0; i < len; i++) {
            int val = stones[i];
            if (!map.containsKey(val))
                continue;
            Set<Integer> set = map.get(val);
            for (Integer dis : set) {
                if (dis > 0) {
                    if (!map.containsKey(stones[i] + dis)) {
                        map.put(stones[i] + dis, new HashSet<>());
                    }
                    Set<Integer> set1 = map.get(stones[i] + dis);
                    set1.add(dis);
                    map.put(stones[i] + dis, set1);
                }

                if (dis + 1 > 0) {
                    if (!map.containsKey(stones[i] + dis + 1)) {
                        map.put(stones[i] + dis + 1, new HashSet<>());
                    }
                    Set<Integer> set2 = map.get(stones[i] + dis + 1);
                    set2.add(dis + 1);
                    map.put(stones[i] + dis + 1, set2);
                }

                if (dis - 1 > 0) {
                    if (!map.containsKey(stones[i] + dis - 1)) {
                        map.put(stones[i] + dis - 1, new HashSet<>());
                    }
                    Set<Integer> set3 = map.get(stones[i] + dis - 1);
                    set3.add(dis - 1);
                    map.put(stones[i] + dis - 1, set3);
                }
            }
        }
        return map.containsKey(stones[len - 1]);
    }

    // 回溯, 记忆化搜索
    public static boolean canCross2(int[] stones) {
        int len = stones.length;
        int[][] memo = new int[len][len];
        for (int i = 0; i < len; i++)
            Arrays.fill(memo[i], -1);
        int backtrack = backtrack(stones, memo, 0, 1);
        return backtrack == 1;
    }
    public static int backtrack(int[] stones, int[][] memo, int pos, int jumpsize) {
        if (memo[pos][jumpsize] > 0) {
            return memo[pos][jumpsize];
        }
        for (int i = pos + 1; i < stones.length; i++) {
            int gap = stones[i] - stones[pos];
            if (gap >= jumpsize - 1 && gap <= jumpsize + 1) {
                if (backtrack(stones, memo, i, gap) == 1) {
                    memo[pos][jumpsize] = 1;
                    return 1;
                }
            }
        }
        memo[pos][jumpsize] = (pos == stones.length - 1) ? 1 : 0;
        return memo[pos][jumpsize];
    }
}
