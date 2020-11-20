package other;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 最接近原点的 K 个点
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 * （这里，平面上两点之间的距离是欧几里德距离。）
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 * 示例 1：
 * 输入：points = [[1,3],[-2,2]], K = 1
 * 输出：[[-2,2]]
 * 解释：
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 * 示例 2：
 * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
 * 输出：[[3,3],[-2,4]]
 * （答案 [[-2,4],[3,3]] 也会被接受。）
 * 提示：
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 */
public class A0973 {
    public static void main(String[] args) {
//        int[][] nums = {{3,3},{5,-1},{-2,4}};
//        int[][] nums = {{2,2},{2,2},{2,2},{2,2},{2,2},{2,2},{1,1}};
        int[][] nums = {{68,97},{34,-84},{60,100},{2,31},{-27,-38},{-73,-74},{-55,-39},{62,91},{62,92},{-57,-67}};
    }
    // 排序
    public int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][];
        int len = points.length;
        if (len == 0)
            return res;
        int[][] temp = new int[len][2];
        for (int i = 0; i < len; i++) {
            temp[i][0] = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            temp[i][1] = i;
        }
        Arrays.sort(temp, Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < K; i++) {
            res[i] = points[temp[i][1]];
        }
        return res;
    }
    public int[][] kClosest2(int[][] points, int K) {
        Arrays.sort(points, (o1, o2) -> o1[0] * o1[0] + o1[1] * o1[1] - o2[0] * o2[0] - o2[1] * o2[1]);
        return Arrays.copyOfRange(points, 0, K);
    }

    // 优先队列, 大顶堆, 如果新点的欧几里得距离小于堆中最大的欧几里得距离, 弹出最大的
    public static int[][] kClosest3(int[][] points, int K) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        for (int i = 0; i < K; i++) {
            queue.offer(new int[]{points[i][0] * points[i][0] + points[i][1] * points[i][1], i});
        }
        for (int i = K; i < points.length; i++) {
            int val = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            int a = queue.peek()[0];
            if (val < queue.peek()[0]) {
                queue.poll();
                queue.offer(new int[]{val, i});
            }
        }
        int[][] ans = new int[K][2];
        for (int i = 0; i < K; i++)
            ans[i] = points[queue.poll()[1]];
        return ans;
    }
}
