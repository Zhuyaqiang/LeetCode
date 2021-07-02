package other;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 直线上最多的点数
 * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 *
 *
 *
 * 示例 1：
 *
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：3
 *
 * 示例 2：
 *
 * 输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 输出：4
 *
 *
 *
 * 提示：
 *
 *     1 <= points.length <= 300
 *     points[i].length == 2
 *     -104 <= xi, yi <= 104
 *     points 中的所有点 互不相同
 */
public class A0149 {
    public static void main(String[] args) {
//        int[][] points = {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
        int[][] points = {{3, 3}, {1, 4}, {1, 1}, {2, 1}, {2, 2}};
        System.out.println(maxPoints(points));
    }
    public static int maxPoints(int[][] points) {
        int len = points.length, res = 1;
        Map<String, Set<int[]>> map = new HashMap<>();
        Map<Double, Set<int[]>> zeroX = new HashMap<>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (points[i][0] == points[j][0]) {
                    double key = points[i][0];
                    Set<int[]> set = zeroX.getOrDefault(key, new HashSet<>());
                    set.add(points[i]);
                    set.add(points[j]);
                    zeroX.put(key, set);

                } else {
                    double[] temp = getLine(points[i], points[j]);
                    String key = temp[0] + "," + temp[1];
                    Set<int[]> set = map.getOrDefault(key, new HashSet<>());
                    set.add(points[i]);
                    set.add(points[j]);
                    map.put(key, set);
                }
            }
        }
        for (Map.Entry<String, Set<int[]>> entry : map.entrySet()) {
            Set<int[]> set = entry.getValue();
            res = Math.max(res, set.size());
        }
        for (Map.Entry<Double, Set<int[]>> entry : zeroX.entrySet()) {
            Set<int[]> set = entry.getValue();
            res = Math.max(res, set.size());
        }
        return res;
    }
    public static double[] getLine(int[] one, int[] two) {
        double k = (double)(two[1] - one[1]) / (two[0] - one[0]);
        double b = one[1] - k * one[0];
        return new double[]{k, b};
    }
}
