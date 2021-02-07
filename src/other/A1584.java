package other;

import java.util.Arrays;

/**
 * 连接所有点的最小费用
 * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
 *
 * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
 *
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * 输出：20
 * 解释：
 *
 * 我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
 * 注意到任意两个点之间只有唯一一条路径互相到达。
 * 示例 2：
 *
 * 输入：points = [[3,12],[-2,5],[-4,1]]
 * 输出：18
 * 示例 3：
 *
 * 输入：points = [[0,0],[1,1],[1,0],[-1,1]]
 * 输出：4
 * 示例 4：
 *
 * 输入：points = [[-1000000,-1000000],[1000000,1000000]]
 * 输出：4000000
 * 示例 5：
 *
 * 输入：points = [[0,0]]
 * 输出：0
 */
public class A1584 {
    public int minCostConnectPoints(int[][] points) {
        return prim(points, 0);
    }

    public int prim(int[][] points, int start) {
        int n = points.length;
        if (n == 0) {
            return 0;
        }
        int res = 0;
        int[][] neighbor = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                neighbor[i][j] = dist;
                neighbor[j][i] = dist;
            }
        }
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        int[] isIn = new int[n];
        Arrays.fill(isIn, -1);
        isIn[start] = 0;
        for (int i = 0; i < n; i++) {
            if (i == start) {
                continue;
            }
            cost[i] = neighbor[i][start];
        }
        for (int i = 1; i < n; i++) {
            int minIndex = -1;
            int minDist = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (isIn[j] == 0) {
                    continue;
                }
                if (cost[j] < minDist) {
                    minDist = cost[j];
                    minIndex = j;
                }
            }
            res += minDist;
            isIn[minIndex] = 0;
            cost[minIndex] = -1;
            for (int j = 0; j < n; j++) {
                if (isIn[j] == -1 && neighbor[j][minIndex] < cost[j]) {
                    cost[j] = neighbor[j][minIndex];
                }
            }
        }
        return res;
    }
}
