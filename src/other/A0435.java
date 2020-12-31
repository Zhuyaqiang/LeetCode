package other;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 无重叠区间
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * 注意:
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * 示例 1:
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 * 输入: [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 * 输入: [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 */
public class A0435 {
    // 动态规划
    public int eraseOverlapIntervals(int[][] intervals) {
        int len = intervals.length;
        if (len <= 1)
            return 0;
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        // dp[i]表示以第i个区间为最后一个区间的可选择不重叠区间的最大数量
        // 最终结果为区间长度-不重叠区间的最大数量
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (intervals[j][1] <= intervals[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return len - max;
    }

    // 贪心
    // https://leetcode-cn.com/problems/non-overlapping-intervals/solution/tan-xin-suan-fa-zhi-qu-jian-diao-du-wen-ti-by-labu/
    public int eraseOverlapIntervals2(int[][] intervals) {
        int len = intervals.length;
        if (len <= 1)
            return 0;
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        int res = 1;
        int end = intervals[0][1];
        for (int[] interval : intervals) {
            int start = interval[0];
            if (start >= end) {
                res ++;
                end = interval[1];
            }
        }
        return len - res;
    }
}
