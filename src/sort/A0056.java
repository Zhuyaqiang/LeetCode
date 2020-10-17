package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 合并区间
 *
 给出一个区间的集合，请合并所有重叠的区间。
 示例 1:
 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 输出: [[1,6],[8,10],[15,18]]
 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 示例 2:
 输入: intervals = [[1,4],[4,5]]
 输出: [[1,5]]
 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。
 */
public class A0056 {
    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
//        int[][] intervals = {{1,4},{2,3}};
        intervals = merge(intervals);
        for (int[] interval : intervals) {
            System.out.println(Arrays.toString(interval));
        }
    }
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> list = new ArrayList<>();
        int len = intervals.length;
        if (len <= 1)
            return intervals;
        int preStart = intervals[0][0], preEnd = intervals[0][1];
        for (int i = 1; i < len; i++) {
            if (intervals[i][0] <= preEnd) {
                if (intervals[i][1] <= preEnd)
                    continue;
                preEnd = intervals[i][1];
            }
            else {
                list.add(new int[] {preStart, preEnd});
                preStart = intervals[i][0];
                preEnd = intervals[i][1];
            }
        }
        list.add(new int[] {preStart, preEnd});
        int[][] res = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
