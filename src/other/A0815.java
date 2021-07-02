package other;

import java.util.*;

/**
 * 公交路线
 * 给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
 *
 *     例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
 *
 * 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
 *
 * 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * 输出：2
 * 解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
 *
 * 示例 2：
 *
 * 输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 * 输出：-1
 *
 *
 *
 * 提示：
 *
 *     1 <= routes.length <= 500.
 *     1 <= routes[i].length <= 105
 *     routes[i] 中的所有值 互不相同
 *     sum(routes[i].length) <= 105
 *     0 <= routes[i][j] < 106
 *     0 <= source, target < 106
 */
public class A0815 {
    // https://leetcode-cn.com/problems/bus-routes/solution/java815gong-jiao-lu-xian-yi-li-jie-ba-ch-0kfv/
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        // 每个车站都有哪些车经过
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int station : routes[i]) {
                List<Integer> buses  =map.getOrDefault(station, new ArrayList<>());
                buses.add(i);
                map.put(station, buses);
            }
        }
        // bus是否坐过，判断是否往队列里加站点
        boolean[] visited = new boolean[routes.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;
            for (int i = 0; i < size; i++) {
                int poll = queue.poll();  // 站点
                List<Integer> cars = map.get(poll);
                for (int car : cars) {
                    if (visited[car]) {
                        continue;
                    }
                    visited[car] = true;
                    for (int sta : routes[car]) {
                        if (sta == target) {
                            return count;
                        }
                        if (sta == poll) {
                            continue;
                        }
                        queue.offer(sta);
                    }
                }
            }
        }
        return -1;
    }
}
