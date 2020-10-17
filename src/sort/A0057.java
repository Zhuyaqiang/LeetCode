package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 插入区间
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * 示例 1：
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例 2：
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 */
public class A0057 {
    public static void main(String[] args) {
        int[][] intervals = {{1,3},{6,9}};
        intervals = insert(intervals, new int[]{2,5});
        for (int[] interval : intervals) {
            System.out.print(Arrays.toString(interval) + " ");
        }
        System.out.println();
        int[][] intervals2 = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        intervals2 = insert(intervals2, new int[]{4,8});
        for (int[] interval : intervals2) {
            System.out.print(Arrays.toString(interval) + " ");
        }
        System.out.println();
    }
    // 三步:
    // 1. 将起点比新插入区间的起点小的数组先插入结果集
    // 2. 将新插入区间和结果集最后一次插入的区间进行比较合并加入
    // 3. 将剩余数组分别和结果集最后一次插入的区间进行比较合并加入
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        int len = intervals.length;
        int index = 0;
        for (;index < len; index ++) {
            if (intervals[index][0] < newInterval[0])
                list.add(intervals[index]);
            else
                break;
        }
        add(list, newInterval);
        for (; index < len; index ++) {
            add(list, intervals[index]);
        }
        int[][] res = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
    public static void add(List<int[]> list, int[] add) {
        int len = list.size();
        if (len == 0)
            list.add(add);
        else {
            int[] ints = list.get(len - 1);
            // 区间有重合
            // 区间没有重合: add[0] > ints[1] + 1 || add[1] < ints[0] - 1
            if (add[0] > ints[1] || add[1] < ints[0]) {
                list.add(add);
            } else {
                list.remove(len - 1);
                list.add(new int[] {Math.min(add[0], ints[0]), Math.max(add[1], ints[1])});
            }
        }
    }
}
