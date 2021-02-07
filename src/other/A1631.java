package other;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 最小体力消耗路径
 * 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0)
 *  ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 *
 * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
 *
 * 请你返回从左上角走到右下角的最小 体力消耗值 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
 * 输出：2
 * 解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
 * 这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
 * 示例 2：
 *
 *
 *
 * 输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
 * 输出：1
 * 解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
 * 示例 3：
 *
 *
 * 输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * 输出：0
 * 解释：上图所示路径不需要消耗任何体力。
 *  
 *
 * 提示：
 *
 * rows == heights.length
 * columns == heights[i].length
 * 1 <= rows, columns <= 100
 * 1 <= heights[i][j] <= 106
 */
public class A1631 {
    public static void main(String[] args) {
        int[][] heights = {{1, 10, 6, 7, 9, 10, 4, 9}};
        System.out.println(minimumEffortPath2(heights));
    }
    // 并查集(克鲁斯卡尔, 每次选择最短的边进行连通)
    public static int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        if (m == 0) {
            return 0;
        }
        int n = heights[0].length;
        if (n == 0) {
            return 0;
        }
        List<int[]> edge = new LinkedList<>();
        // 构造带权边
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index = i * n + j;
                if (i < m - 1) {
                    edge.add(new int[]{index, index + n, Math.abs(heights[i][j] - heights[i + 1][j])});
                }
                if (j < n - 1) {
                    edge.add(new int[]{index, index + 1, Math.abs(heights[i][j] - heights[i][j + 1])});
                }
            }
        }
        Collections.sort(edge, Comparator.comparingInt(o -> o[2]));
        int res = 0;
        Union union = new Union(m * n);
        for (int i = 0; i < edge.size(); i++) {
            int[] temp = edge.get(i);
            int a = temp[0];
            int b = temp[1];
            int val = temp[2];
            res = val;
            if (union.find(a) != union.find(b)) {
                union.merge(a, b);
            }
            if (union.find(0) == union.find(m * n - 1)) {
                break;
            }
        }
        return res;
    }
    public static class Union {
        private int[] parent;
        public Union(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        public int find(int val) {
            if (parent[val] != val) {
                parent[val] = find(parent[val]);
            }
            return parent[val];
        }
        public void merge(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
            }
        }
    }
    // 二分查找, height取值为[1, 1000000], 因此体力消耗值取值范围为[0, 999999], 采用二分查找判断给定体力值是否能走到终点
    // 广度优先搜索
    public static int minimumEffortPath2(int[][] heights) {
        int m = heights.length;
        if (m == 0) {
            return 0;
        }
        int n = heights[0].length;
        if (n == 0) {
            return 0;
        }
        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int left = 0, right = 999999, ans = 0;
        while (left <= right) {
            int mid = (right + left) / 2;
            boolean[] seen = new boolean[m * n];
            Queue<int[]> queue = new LinkedList<>();
            seen[0] = true;
            queue.offer(new int[]{0, 0});
            while (!queue.isEmpty()) {
                int[] poll = queue.poll();
                int x = poll[0], y = poll[1];
                for (int j = 0; j < 4; j++) {
                    int newX = x + dir[j][0];
                    int newY = y + dir[j][1];
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n && !seen[newX * n + newY] && Math.abs(heights[x][y] - heights[newX][newY]) <= mid) {
                        queue.offer(new int[]{newX, newY});
                        seen[newX * n + newY] = true;
                    }
                }
            }
            if (seen[m * n - 1]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
