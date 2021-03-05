package other;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 会议室2
 * 给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi]
 * ，为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
 *
 * 示例 1：
 * 输入：intervals = [[0,30],[5,10],[15,20]]
 * 输出：2
 * 示例 2：
 * 输入：intervals = [[7,10],[2,4]]
 * 输出：1
 * 提示：
 * 1 <= intervals.length <= 104
 * 0 <= starti < endi <= 106
 */
public class A0253 {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        int len = intervals.length;
        // 根据会议开始时间排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        // 小顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o));
        int res = 1;
        for (int i = 0; i < intervals.length; i++) {
            // 如果当前会议和最早结束的会议不重叠
            while (!queue.isEmpty() && queue.peek() <= intervals[i][0]) {
                queue.poll();
            }
            queue.offer(intervals[i][1]);
            res = Math.max(res, queue.size());
        }
        return res;
    }
}
