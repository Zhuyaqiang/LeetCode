package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 插入区间
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * 示例 1:
 * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出: [[1,5],[6,9]]
 * 示例 2:
 * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出: [[1,2],[3,10],[12,16]]
 * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 */
public class A0057 {
    public static void main(String[] args) {
//        int[][] intevrals = {{1,5}};
//        int[][] intevrals = {};
//        int[][] intevrals = {{1,5}};
//        int[] newInterval = {1,7};
//        int[] newInterval = {4, 8};
//        int[] newInterval = {0, 3};
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = {4, 8};
        int[][] insert = rInsert2(intervals, newInterval);
        for (int[] ints : insert) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        if (intervals == null || intervals.length < 1) {
            res.add(newInterval);
            return res.toArray(new int[res.size()][]);
        }
        int newStart = newInterval[0], newEnd = newInterval[1];
        int index = 0;
        while (index < intervals.length && newStart > intervals[index][0]) {
            res.add(intervals[index]);
            index++;
        }
        int[] curr;
        if (res.size() >= 1)
            curr = res.remove(res.size()-1);
        else {
            if (intervals[index][0] > newInterval[0]) {
                curr = newInterval;
                newInterval = intervals[index];
            } else
                curr = intervals[index];
        }
        if (curr[1] >= newInterval[0]) {
            res.add(new int[]{curr[0], Math.max(curr[1], newInterval[1])});
        } else {
            res.add(curr);
            res.add(newInterval);
        }
        curr = res.remove(res.size()-1);
        while (index < intervals.length) {
            if (curr[1] >= intervals[index][0]) {
                res.add(new int[]{curr[0], Math.max(curr[1], intervals[index][1])});
            } else {
                res.add(curr);
                res.add(intervals[index]);
            }
            index ++;
            curr = res.remove(res.size()-1);
        }
        res.add(curr);
        return res.toArray(new int[res.size()][]);
    }

    public int[][] insert2(int[][] intervals, int[] newInterval) {
        // init data
        int newStart = newInterval[0], newEnd = newInterval[1];
        int idx = 0, n = intervals.length;
        LinkedList<int[]> output = new LinkedList<int[]>();

        // 将起点小于新区间的放进结果集
        while (idx < n && newStart > intervals[idx][0])
            output.add(intervals[idx++]);

        // add newInterval
        int[] interval = new int[2];
        // 如果结果集是空或者结果集最后一个和新区间不重合
        if (output.isEmpty() || output.getLast()[1] < newStart)
            output.add(newInterval);
            // 如果结果集非空且最后一个和新区间重合
        else {
            interval = output.removeLast();
            interval[1] = Math.max(interval[1], newEnd);
            output.add(interval);
        }

        // add next intervals, merge with newInterval if needed
        while (idx < n) {
            interval = intervals[idx++];
            int start = interval[0], end = interval[1];
            // 如果不重合
            if (output.getLast()[1] < start) output.add(interval);
            // 如果重合
            else {
                interval = output.removeLast();
                interval[1] = Math.max(interval[1], end);
                output.add(interval);
            }
        }
        return output.toArray(new int[output.size()][2]);
    }

    public static int[][] rInsert2(int[][] intervals, int[] newInterval) {
        LinkedList<int[]> res = new LinkedList<>();
        int start = 0;
        int newStart = newInterval [0], newEnd = newInterval[1];
        while (start < intervals.length && intervals[start][0] < newInterval[0]) {
            res.add(intervals[start]);
            start++;
        }

        if (res.isEmpty() || res.getLast()[1] < newStart)
            res.add(newInterval);
        else {
            int[] curr = res.removeLast();
            curr[1] = Math.max(curr[1], newEnd);
            res.add(curr);
        }
        boolean flag = false;
        while (start < intervals.length) {
            if (flag || res.getLast()[1] < intervals[start][0]) {
                res.add(intervals[start]);
                flag = true;
            } else {
                int[] curr = res.removeLast();
                curr[1] = Math.max(curr[1], intervals[start][1]);
                res.add(curr);
            }
            start ++;
        }
        return res.toArray(new int[res.size()][]);
    }
}
