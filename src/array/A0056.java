package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 * 示例 1:
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class A0056 {
    public static void main(String[] args) {
        int[][] intervals = {{1,3}, {2,6}, {8,10}, {15,18}};
        int[][] merge = merge(intervals);
        for (int[] ints : merge) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;
        // 根据开始时间排序
        Arrays.sort(intervals, Comparator.comparingInt(arr -> arr[0]));
        List<int[]> res = new ArrayList<>();
        int[] curr = intervals[0];
        for (int[] interval : intervals) {
            int curr_end = curr[1];
            int next_start = interval[0];
            int next_end = interval[1];
            // 前一个点结束时间大于等于后一个点开始时间, 表示有重叠
            if (curr_end >= next_start) {
                curr[1] = Math.max(curr_end, next_end);
            } else {
                res.add(curr);
                curr = interval;
            }
        }
        res.add(curr);
        return res.toArray(new int[res.size()][]);
    }
}
