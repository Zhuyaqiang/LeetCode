package other;

import java.util.*;

/**
 * 插入区间
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *  
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
//        int[][] intervals = {{1,3},{6,9}};
//        int[] newInterval = {2,5};
        int[][] intervals = {};
        int[] newInterval = {5,7};
//        int[][] intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
//        int[] newInterval = {4,8};
        insert(intervals, newInterval);
    }
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int index = 0;
        Deque<int[]> deque = new ArrayDeque<>();
        while (index < intervals.length && intervals[index][0] < newInterval[0]) {
            deque.addLast(intervals[index]);
            index++;
        }
        int[] last;
        if (deque.isEmpty()) {
            deque.addLast(newInterval);
        } else {
            last = deque.getLast();
            if (last[1] >= newInterval[0]) {
                last[0] = Math.min(last[0], newInterval[0]);
                last[1] = Math.max(last[1], newInterval[1]);
                deque.removeLast();
                deque.addLast(last);
            } else {
                deque.addLast(newInterval);
            }
        }
        while (index < intervals.length) {
            last = deque.getLast();
            if (last[1] >= intervals[index][0]) {
                last[0] = Math.min(last[0], intervals[index][0]);
                last[1] = Math.max(last[1], intervals[index][1]);
                deque.removeLast();
                deque.addLast(last);
            } else {
                deque.addLast(intervals[index]);
            }
            index ++;
        }
        int[][] res = new int[deque.size()][];
        int n = deque.size();
        for (int i = 0; i < n; i++) {
            res[i] = deque.getFirst();
            deque.removeFirst();
        }
        return res;
    }
}
